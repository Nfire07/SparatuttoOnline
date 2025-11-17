import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameGraphics extends JPanel{
	public ArrayList<GameObject> gameObjects;
	public Point mousePosition;
	
	public GameGraphics(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		gameObjects.forEach(o -> {
			if(o.isVisible())
				o.drawSprite(g2d);
		});
		Player p = (Player)gameObjects.get(0);
		p.checkRayIntersections(gameObjects);
		
		if(mousePosition!=null) {
			p.drawRay(g2d, mousePosition);
		}
		if(!p.bullets.isEmpty()) {
			p.bullets.forEach(o -> o.draw(g2d));
		}
		
	}
	
	
}
