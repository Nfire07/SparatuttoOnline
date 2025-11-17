import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.EventListener;

public class Window extends JFrame {
	public static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	public GameGraphics g;
	public ArrayList<GameObject> gameObjects;
	public ArrayList<Image> tempImage;
	public ArrayList<Rectangle> tempHitbox;

	public Window() {
		
		gameObjects = new ArrayList<GameObject>();
		tempImage = new ArrayList<Image>();
		tempHitbox = new ArrayList<Rectangle>();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screen);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		tempImage.add(Main.imageLoader("./assets/Player.png").getScaledInstance(100,100,Image.SCALE_FAST));
		tempHitbox.add(new Rectangle(100,100,100,100));
		

		gameObjects.add(new Player("Player", (Rectangle[]) tempHitbox.toArray(new Rectangle[0]),
				(Image[]) tempImage.toArray(new Image[0]), 100, 100));
		gameObjects.get(0).setVisible(true);

		tempImage.clear();
		tempHitbox.clear();
		
		tempImage.add(Main.imageLoader("./assets/Player.png").getScaledInstance(100,100,Image.SCALE_FAST));
		tempHitbox.add(new Rectangle(100,100,100,100));
		

		gameObjects.add(new Enemy("Enemy", (Rectangle[]) tempHitbox.toArray(new Rectangle[0]),
				(Image[]) tempImage.toArray(new Image[0]), 500, 500));
		
		tempImage.clear();
		tempHitbox.clear();
		
		this.g = new GameGraphics(gameObjects);
		g.setLocation(0, 0);
		g.setSize(screen);
		g.setBackground(Color.DARK_GRAY);
		this.addKeyListener(new WindowKeys(this, gameObjects));
		g.addMouseListener(new WindowKeys(this, gameObjects));
		g.addMouseMotionListener(new WindowKeys(this, gameObjects));
		this.add(g);
		
		this.setVisible(true);
	}

	

}
