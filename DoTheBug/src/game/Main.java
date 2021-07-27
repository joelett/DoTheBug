package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import de.zigames.window.KeyBoard;
import de.zigames.window.WindowInit;
import de.zigames.window.WindowMath;

public class Main {
	public static void main(String[] args) throws Exception {
		ImageIcon img = new ImageIcon("gfx\\player.png");
		Player player = new Player();
		EnemyHandler elist = new EnemyHandler(player);
		Menu menuHandler = new Menu(player,elist);
		VFX vfx = new VFX(elist,player);
		Rectangle window = new Rectangle(500,100,800,800);
		player.setWindowSize(window.width, window.height);
		WindowInit wini = new WindowInit("Do The Bug",window.x,window.y,window.width,window.height,player,elist,vfx,menuHandler);
		wini.getFrame().setUndecorated(true);
		wini.getFrame().setResizable(false);
		wini.getFrame().setIconImage(img.getImage());
		//SHOW WINDOW
		wini.getFrame().setVisible(true);
		int mx=0;
		int my=0;
		
		boolean holdPause=false;
		
		//SOUND(INTRO)
		Sound.playSound("gameIntro.wav",0);
		boolean startGameTheme=true;
		////////////
		
		while(true) {
			//START GAME THEME
			if(startGameTheme) {
				if(Sound.ready) {
					Sound.playSound("gamethemeMenu.wav",Clip.LOOP_CONTINUOUSLY);
					startGameTheme=false;
				}
			}
			//////////////////
			
			//timeScinceLastFrame
			float timeScinceLastFrame = WindowMath.timeScinceLastFrame();
			/////////////////////
			
			//SET GAME PAUSED
			if(!startGameTheme&&KeyBoard.isKeyDown(KeyEvent.VK_ESCAPE)&&!holdPause&&!menu){
				int time = Sound.clip.getFramePosition();
				holdPause=true;
				Sound.clip.stop();
				if(paused) {
					Sound.playSound("gameTheme.wav", Clip.LOOP_CONTINUOUSLY,(int) time);
				}else {
					Sound.playSound("gameThemeMenu.wav", Clip.LOOP_CONTINUOUSLY,(int) time);
				}
				paused=!paused;
			}else if(!KeyBoard.isKeyDown(KeyEvent.VK_ESCAPE)){
				holdPause=false;
			}
			///////////////////
			
			//UPDATE VFX
			vfx.update(paused, timeScinceLastFrame);
			////////////
			
			//GET MOUSEPOINTER
			try{
				mx=wini.getFrame().getMousePosition().x;
				my=wini.getFrame().getMousePosition().y;
			}catch(NullPointerException e) {}
			///////////////////
			
			//DEATH SOUND
			if(!player.alive&&!registeredDead) {
				Sound.clip.stop();
				Sound.playSound("dead.wav", 0);
			}
			registeredDead=!player.alive;
			/////////////
			
			//UPDATE PLAYER AND ENEMIES
			if(!paused&&!menu){
				player.update(timeScinceLastFrame,mx, my);
				elist.update(timeScinceLastFrame);
			}
			///////////////////////////
			
			//SET MENU ON
			if(!menu&&!player.alive) {
				menu=true;
				paused=false;
			}
			/////////////
			
			//MENU AND PAUSEMENU
			if(!startGameTheme){
			if(menu) {
				int m=menuHandler.updateMenu(timeScinceLastFrame,mx,my);
				if(m==1){
					menu=false;
					paused=false;
					
					{
						elist.reset();
						player.reset();
					}

					int time = Sound.clip.getFramePosition();
					Sound.clip.close();
					Sound.playSound("gameTheme.wav", Clip.LOOP_CONTINUOUSLY,(int) time);
				}
			}else if(paused) {
				int m = menuHandler.updatePause(timeScinceLastFrame,mx,my);
				if(m==1){
					paused=false;
					int time = Sound.clip.getFramePosition();
					Sound.clip.close();
					Sound.playSound("gameTheme.wav", Clip.LOOP_CONTINUOUSLY,(int) time);
				}else if(m==2) {
					menu=true;
					paused=false;
				}
			}else {
				menuHandler.updateGame();
			}
			}else{
				menuHandler.updateSplash();
			}
			/////////////////////
			
			//DRAW
			wini.getFrame().repaintScreen();
			//////
			
			//WAIT
			Thread.sleep(1);
			//////
		}
	}
	
	static boolean registeredDead=false;
	static boolean paused=false;
	static boolean menu=true;

}
