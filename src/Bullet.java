import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Bullet {
    Rectangle hitbox;
    float duration = 1.8f;
    int bulletSpeed = 10;
    double angle;

    public Bullet(Rectangle hitbox, Point target) {
        this.hitbox = hitbox;

        double dx = target.x - hitbox.x;
        double dy = target.y - hitbox.y;

        this.angle = Math.atan2(dy, dx);
    }

    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();

        g2d.translate(hitbox.x, hitbox.y);
        g2d.rotate(angle);

        g2d.setColor(Color.RED);
        g2d.fillRect(-hitbox.width/2, -hitbox.height/2, hitbox.width, hitbox.height);

        g2d.setTransform(old);
    }

    public void updateBullet() {
        hitbox.x += (int)(Math.cos(angle) * bulletSpeed);
        hitbox.y += (int)(Math.sin(angle) * bulletSpeed);

        duration -= 0.016f;
    }

    public boolean bulletExploded() {
        return duration <= 0;
    }
}
