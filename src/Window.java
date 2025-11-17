import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Window extends JFrame {
    public static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public GameGraphics g;
    public ArrayList<GameObject> gameObjects;
    public ArrayList<Image> tempImage;
    public ArrayList<Rectangle> tempHitbox;
    public InputManager inputManager;
    
    public Window() {
        gameObjects = new ArrayList<GameObject>();
        tempImage = new ArrayList<Image>();
        tempHitbox = new ArrayList<Rectangle>();
        inputManager = new InputManager();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screen);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        
        GameAudio.addSound(new Sound("./assets/shoot.wav"));
        
        // Player
        tempImage.add(Main.imageLoader("./assets/Player.png").getScaledInstance(100, 100, Image.SCALE_FAST));
        tempHitbox.add(new Rectangle(0, 0, 100, 100));
        
        gameObjects.add(new Player("Player", 
            (Rectangle[]) tempHitbox.toArray(new Rectangle[0]),
            (Image[]) tempImage.toArray(new Image[0]), 
            100, 100, inputManager));
        gameObjects.get(0).setVisible(true);
        
        tempImage.clear();
        tempHitbox.clear();
        
        // Enemy
        tempImage.add(Main.imageLoader("./assets/Enemy.png").getScaledInstance(100, 100, Image.SCALE_FAST));
        tempHitbox.add(new Rectangle(500,500,100,100)); 
        
        gameObjects.add(new Enemy("Enemy", 
            (Rectangle[]) tempHitbox.toArray(new Rectangle[0]),
            (Image[]) tempImage.toArray(new Image[0]), 
            500, 500));
        
        tempImage.clear();
        tempHitbox.clear();
        
        this.g = new GameGraphics(gameObjects);
        g.setLocation(0, 0);
        g.setSize(screen);
        g.setBackground(Color.DARK_GRAY);
        
        WindowKeys keys = new WindowKeys(this, gameObjects, inputManager);
        this.addKeyListener(keys);
        g.addMouseListener(keys);
        g.addMouseMotionListener(keys);
        
        this.add(g);
        this.setVisible(true);
    }
}