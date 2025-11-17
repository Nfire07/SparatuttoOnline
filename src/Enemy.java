import java.awt.Image;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	boolean isVisible = false;
	
	public Enemy(String ObjectName, Rectangle[] hitbox, Image[] sprite, int x, int y) {
		super(ObjectName, hitbox, sprite, x, y);
	}

	@Override
	public void UpdatePosition(float deltaTime) {
		
	}

	

}
