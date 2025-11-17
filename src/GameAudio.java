
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameAudio {
	public static ArrayList<Sound> soundList = new ArrayList<Sound>();
	public static Clip audioClip;
	
	
	public static void addSound(Sound s) {
		if(soundList.size()==0 && s!=null) {
			try {
				audioClip=AudioSystem.getClip();
			}catch(LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		if(s!=null)
			soundList.add(s);
	}
	
	public static void playSound(int index) {
		if(audioClip.isRunning()==false) {
			audioClip.close();
			audioClip.flush();
		}
		if(soundList.get(index)!=null && audioClip.isOpen()==false) {
			try {
				audioClip.open(AudioSystem.getAudioInputStream(soundList.get(index).soundFile));
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			audioClip.start();
		}
	}
}