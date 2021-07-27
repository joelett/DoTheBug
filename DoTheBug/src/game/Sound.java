package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;

import de.zigames.window.WindowMath;

public class Sound {

	public Sound() {
	}
	
	static Clip clip;
	static boolean ready=false;
	
	static long timer=0;
	
	public static void playSound(String file,int loop) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		        		new File("src\\sfx\\"+file)//TODO SET FILE TO "sfx\\" INSTEAD OF "src\\sfx\\" FOR EXPORT
		        		);
		          //Main.class.getResourceAsStream("C:\\game\\NGame\\src\\sfx\\gameIntro.wav"));
		        clip.open(inputStream);
		        clip.loop(loop);
		        
		        clip.start();

		        FloatControl fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		        fc.setValue(-15);
		        
		        ready=false;
		        if(loop!=Clip.LOOP_CONTINUOUSLY) {
		        while((loop+1)*clip.getMicrosecondLength()>timer) {
		        	timer+=WindowMath.timeScinceLastFrame()*1000000;
		        	//System.out.println(timer+" "+clip.getMicrosecondLength());
		      	}
		        clip.close();
		        }
		        ready=true;
		        timer=0;
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		  }).start();
		}

	public static void playSound(String file,int loop,int sound) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		        		new File("src\\sfx\\"+file)//TODO SET FILE TO "sfx\\" INSTEAD OF "src\\sfx\\" FOR EXPORT
		        		);
		          //Main.class.getResourceAsStream("C:\\game\\NGame\\src\\sfx\\gameIntro.wav"));
		        clip.open(inputStream);
		        clip.setFramePosition(sound);
		        clip.loop(loop);
		        
		        clip.start();

		        FloatControl fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		        fc.setValue(-15);
		        
		        if(loop!=Clip.LOOP_CONTINUOUSLY) {
		        ready=false;
		        while((loop+1)*clip.getMicrosecondLength()>timer) {
		        	timer+=WindowMath.timeScinceLastFrame()*1000000;
		        	//System.out.println(timer+" "+clip.getMicrosecondLength());
		      	}
		        clip.close();
		        }
		        ready=true;
		        timer=0;
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		  }).start();
		}

}
