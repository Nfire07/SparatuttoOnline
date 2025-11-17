import java.awt.*;


// class used for every object in a game
// is an abstract class because you have to create a specific class for all of your game objects

public abstract class GameObject {
	public String ObjectName;
	public Rectangle[] hitbox;
	public Image[] sprite;
	public static boolean debugMode = false;
	public int currentSprite=0;
	public int x,y;
	public boolean isVisible;
	
	// basic constructor
	public GameObject(String ObjectName , Rectangle[] hitbox,Image[] sprite, int x, int y){
		this.ObjectName = ObjectName;
		this.hitbox = hitbox;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	// used for debug
	public static void setDebugMode(boolean debugMode) {
		GameObject.debugMode = debugMode;
	}
	
	
	public Rectangle[] updateHitbox() {
		Rectangle[]  newHitbox = new Rectangle[hitbox.length];
		
		for(int i=0;i<newHitbox.length;i++) {
			int vectorX = x-hitbox[i].x;
			int vectorY = y-hitbox[i].y;
			newHitbox[i] = new Rectangle(hitbox[i].x+vectorX,hitbox[i].y + vectorY,hitbox[i].width,hitbox[i].height);
		}
		
		return newHitbox;
	}
	
	
	public void updateSprite() {
		currentSprite = (currentSprite + 1)%sprite.length;
	}
	
	public boolean checkCollisionWith(GameObject g){
		for(int i=0;i<hitbox.length;i++) {
			for(int j=0;j<g.hitbox.length;j++) {
				if(hitbox[i].intersects(g.hitbox[j]))
					return true;
			}
		}
		return false;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	// used for debug
	public void drawHitbox(Graphics g , Color c) {
		if(debugMode==true) {
			g.setColor(c);
			for(int i=0;i<hitbox.length;i++) {
				g.drawRect(hitbox[i].x,hitbox[i].y,hitbox[i].width,hitbox[i].height);
			}
		}
	}
	
	public void drawSprite(Graphics g) {
		g.drawImage(sprite[currentSprite],x,y,null);
	}
	
	// used for debug
	public String toString() {
		if(debugMode==true) {
			String s = ObjectName + "	";
			
			s +=  "XY:	(" + x + ";" + y + ")\nHITBOX COUNT:	" + hitbox.length +"\n";
			
			for(int i=0;i<hitbox.length;i++) {
				s += "[" + (i+1) +"]:	"+ hitbox[i].toString() + "\n";
			}
			
			s += "SPRITE COUNT:	" + sprite.length + "\n";
			
			for(int i=0;i<sprite.length;i++) {
				s += "Image[" + (i+1) +"]:	" + "width=" + sprite[i].getWidth(null) + ",height=" + sprite[i].getHeight(null) + "\n";
			}
			
			s += "\n";
			
			return s;
		}
		
		return "";
	}
	
	// metodo usato per spostare e aggiornare il game object
	public abstract void UpdatePosition();
}	
