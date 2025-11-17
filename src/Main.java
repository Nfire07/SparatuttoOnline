import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main{
	
	public static Image imageLoader(String filePath) {
		Image i = null;
		if(GameObject.debugMode==true)
			System.out.println("[imageLoader]Loading:	" + filePath + "\n");
		try {
			i=ImageIO.read(new File(filePath));
		}catch(IOException e){
			e.printStackTrace();
		}
		return i;
	}
	
	
    public static void main(String[] args) {
    	Window w = new Window();
    	
    	while(true) {
    		w.g.repaint();
    		w.gameObjects.forEach(e -> e.UpdatePosition());
    		try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
	}
	 
}
