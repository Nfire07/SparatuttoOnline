import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class WindowKeys implements KeyListener,MouseListener,MouseMotionListener{
	ArrayList<GameObject> gameObjects;
	Window window;
	public WindowKeys(Window window,ArrayList<GameObject> gameObjects){
		this.window = window;
		this.gameObjects = gameObjects;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Player p = (Player) gameObjects.get(0);
		p.shootBullet(e.getPoint());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Player p = (Player) gameObjects.get(0);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			window.dispose();
			System.exit(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p.y -= p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			p.x -= p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			p.y += p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			p.x +=p.speed;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Player p = (Player) gameObjects.get(0);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			window.dispose();
			System.exit(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p.y -= p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			p.x -= p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			p.y += p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			p.x +=p.speed;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Player p = (Player) gameObjects.get(0);
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			window.dispose();
			System.exit(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p.y -= p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			p.x -= p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			p.y += p.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			p.x +=p.speed;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		window.g.mousePosition = new Point(x,y);
		
	}
	
}
