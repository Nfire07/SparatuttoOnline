import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Player extends GameObject{
	int speed = 7;
	double fov = 60;
	int rayLength = 1000;
	int ammo = 5;
	public Point leftRayEnd;
	public Point rightRayEnd;
	public double angleToMouse;
	
	ArrayList<Bullet> bullets;
	public Player(String ObjectName, Rectangle[] hitbox, Image[] sprite, int x, int y) {
		super(ObjectName, hitbox, sprite, x, y);
		bullets = new ArrayList<Bullet>();
	}
	
	public int getCenterX() {
	    return x + sprite[currentSprite].getWidth(null) / 2; 
	}

	public int getCenterY() {
	    return y + sprite[currentSprite].getHeight(null) / 2;
	}
	
	public void shootBullet(Point p) {
		if(ammo - bullets.size() > 0)
			bullets.add(new Bullet(new Rectangle(getCenterX(),getCenterY(),5,5),p));
	}
	
	public void drawRay(Graphics2D g2d, Point mouse) {
	    double dx = mouse.x - this.getCenterX();
	    double dy = mouse.y - this.getCenterY();

	    angleToMouse = Math.atan2(dy, dx);
	    double fovRadians = Math.toRadians(fov);

	    double leftAngle = angleToMouse - fovRadians / 2;
	    double rightAngle = angleToMouse + fovRadians / 2;

	    leftRayEnd = new Point(
	        this.getCenterX() + (int)(Math.cos(leftAngle) * rayLength),
	        this.getCenterY() + (int)(Math.sin(leftAngle) * rayLength)
	    );

	    rightRayEnd = new Point(
	        this.getCenterX() + (int)(Math.cos(rightAngle) * rayLength),
	        this.getCenterY() + (int)(Math.sin(rightAngle) * rayLength)
	    );

	    g2d.setColor(Color.WHITE);
	    g2d.drawLine(getCenterX(), getCenterY(), leftRayEnd.x, leftRayEnd.y);
	    g2d.drawLine(getCenterX(), getCenterY(), rightRayEnd.x, rightRayEnd.y);

	    int[] xPoints = { getCenterX(), leftRayEnd.x, rightRayEnd.x };
	    int[] yPoints = { getCenterY(), leftRayEnd.y, rightRayEnd.y };

	    g2d.setColor(new Color(255, 255, 0, 50));
	    g2d.fillPolygon(xPoints, yPoints, 3);
	}
	
	public Polygon getFOVTriangle() {
	    int[] xPoints = { 
	        getCenterX(), 
	        leftRayEnd.x, 
	        rightRayEnd.x 
	    };

	    int[] yPoints = { 
	        getCenterY(), 
	        leftRayEnd.y, 
	        rightRayEnd.y 
	    };

	    return new Polygon(xPoints, yPoints, 3);
	}
	
	public boolean triangleIntersectsRectangle(Polygon triangle, Rectangle rect) {

	    for (int i = 0; i < 3; i++) {
	        if (rect.contains(triangle.xpoints[i], triangle.ypoints[i])) {
	            return true;
	        }
	    }

	    Point[] rectPoints = {
	        new Point(rect.x, rect.y),
	        new Point(rect.x + rect.width, rect.y),
	        new Point(rect.x, rect.y + rect.height),
	        new Point(rect.x + rect.width, rect.y + rect.height)
	    };

	    for (Point p : rectPoints) {
	        if (triangle.contains(p)) {
	            return true;
	        }
	    }

	    for (int i = 0; i < 3; i++) {
	        int next = (i + 1) % 3;

	        Line2D triEdge = new Line2D.Float(
	            triangle.xpoints[i], triangle.ypoints[i],
	            triangle.xpoints[next], triangle.ypoints[next]
	        );

	        if (triEdge.intersects(rect)) {
	            return true;
	        }
	    }

	    return false;
	}


	public void checkRayIntersections(ArrayList<GameObject> gameObjects){
	    if(leftRayEnd == null || rightRayEnd == null) return;

	    Polygon fovTriangle = getFOVTriangle();

	    for(int i = 1; i < gameObjects.size(); i++) {  
	        Enemy enemy = (Enemy)gameObjects.get(i);
	        enemy.setVisible(false);
	        if (triangleIntersectsRectangle(fovTriangle, enemy.hitbox[0])) {
	        	enemy.setVisible(true);
	        }
	    }
	}

	
	@Override
	public void UpdatePosition() {
	    this.hitbox = updateHitbox();

	    ArrayList<Bullet> toRemove = new ArrayList<>();

	    for (Bullet bullet : bullets) {
	        bullet.updateBullet();
	        if (bullet.bulletExploded()) {
	            toRemove.add(bullet);
	        }
	    }

	    bullets.removeAll(toRemove);
	}

	


}
