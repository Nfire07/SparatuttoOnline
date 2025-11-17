import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    
    public static Image imageLoader(String filePath) {
        Image i = null;
        if(GameObject.debugMode == true)
            System.out.println("[imageLoader]Loading: " + filePath + "\n");
        try {
            i = ImageIO.read(new File(filePath));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public static void main(String[] args) {
        Window w = new Window();
        GameTimer timer = new GameTimer();

        final double TARGET_FPS = 60.0;
        final double TARGET_FRAME_TIME = 1000.0 / TARGET_FPS; // millisecondi per frame
        
        while(true) {
            long frameStart = System.currentTimeMillis();
            
            float deltaTime = timer.update();
            
            w.inputManager.update();
            
            w.gameObjects.forEach(e -> e.UpdatePosition(deltaTime));
            
            w.g.repaint();
            
            long frameTime = System.currentTimeMillis() - frameStart;
            long sleepTime = (long)(TARGET_FRAME_TIME - frameTime);
            
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if(GameObject.debugMode) {
                System.out.println("FPS: " + timer.getFPS() + " | DeltaTime: " + 
                                   String.format("%.4f", deltaTime) + "s");
            }
        }
    }
}