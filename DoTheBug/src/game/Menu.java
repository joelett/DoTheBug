package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.zigames.window.Drawable;
import de.zigames.window.Mouse;

public class Menu extends Drawable{
	
	Player player;
	EnemyHandler elist;
	
	public Menu(Player p,EnemyHandler eh) {
		player = p;elist=eh;
	}
	
	Rectangle menuButtonBounds[][] = {{new Rectangle(12f,12f,10,2.5f),new Rectangle(12f,15f,10,2.5f),new Rectangle(12f,18f,10,2.5f)}
									,{new Rectangle(12f,12f,10,2.5f),new Rectangle(12f,15f,10,2.5f)}};
	//Rectangle menuButtonBounds[] = new Rectangle[4];
	Rectangle pauseButtonBounds[] = {new Rectangle(12f,12f,10,2.5f),new Rectangle(12f,18f,10,2.5f)};
	
	static BufferedImage buttons[][];
	
	static {
		try {
			buttons = new BufferedImage[1][3];
			buttons[0][0] = ImageIO.read(new File("src\\gfx\\startbtn.png"));
			buttons[0][1] = ImageIO.read(new File("src\\gfx\\shopbtn.png"));
			buttons[0][2] = ImageIO.read(new File("src\\gfx\\exitbtn.png"));
		} catch (IOException e) {
			try {
				buttons[0][0] = ImageIO.read(new File("gfx\\startbtn.png"));
				buttons[0][1] = ImageIO.read(new File("gfx\\shopbtn.png"));
				buttons[0][2] = ImageIO.read(new File("gfx\\exitbtn.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	boolean lastMouse=false;
	
	boolean shop=false;
	
	public int updateMenu(float timeScinceLastFrame,int mx,int my) {
		pause=false;
		menu=true;
		splash=false;
		
		float x=mx/player.winWidth;
		float y=my/player.winHeight;
		
		Rectangle cursor = new Rectangle(x,y,1,1);
		
		if(!shop){
		for(int i=0;i<menuButtonBounds[0].length;i++) {
			if(cursor.intersect(menuButtonBounds[0][i])) {
				if(lastMouse&&!Mouse.isKeyDown(MouseEvent.BUTTON1)){
					if(i==0) {
						return 1;
					}else if(i==1){
						shop=true;
					}else if(i==menuButtonBounds[0].length-1){
						System.exit(0);
					}
				}
			}
		}
		}else{
			//SHOP///////////////////////////////////////
			for(int i=0;i<menuButtonBounds[1].length;i++) {
				if(cursor.intersect(menuButtonBounds[1][i])) {
					if(lastMouse&&!Mouse.isKeyDown(MouseEvent.BUTTON1)){
						if(i==0){
							if(player.coins>=5){
								player.ammo+=5;
								player.coins-=5;
							}
						}else if(i==menuButtonBounds[1].length-1){
							shop=false;
						}
					}
				}
			}	
		}
		
		lastMouse=Mouse.isKeyDown(MouseEvent.BUTTON1);
		
		return 0;
	}
	
	public int updatePause(float timeScinceLastFrame,int mx,int my) {
		//UPDATE STATE
		pause=true;
		menu=false;
		splash=false;
		//////////////
		
		float x=mx/player.winWidth;
		float y=my/player.winHeight;
		
		Rectangle cursor = new Rectangle(x,y,1,1);
		
		for(int i=0;i<pauseButtonBounds.length;i++) {
			if(cursor.intersect(pauseButtonBounds[i])) {
				if(lastMouse&&!Mouse.isKeyDown(MouseEvent.BUTTON1)){
					if(i==0){
						lastMouse=Mouse.isKeyDown(MouseEvent.BUTTON1);
						return 1;
					}else if(i==1) {
						lastMouse=Mouse.isKeyDown(MouseEvent.BUTTON1);
						return 2;
					}
				}
			}
		}
		lastMouse=Mouse.isKeyDown(MouseEvent.BUTTON1);
		return 0;
	}
	
	public void updateGame() {
		lastMouse=false;
		pause=false;
		menu=false;
		splash=false;
	}
	
	boolean menu=true;
	boolean pause=false;
	boolean splash=false;
	
	public void draw(Graphics g) {
		if(!splash){
		if(menu&&!shop) {
			for(int i=0;i<menuButtonBounds[0].length;i++) {
				if(i<buttons[0].length) {
					g.drawImage(buttons[0][i],(int)(menuButtonBounds[0][i].x*player.winWidth), (int)(menuButtonBounds[0][i].y*player.winHeight),(int)(menuButtonBounds[0][i].width*player.winWidth), (int)(menuButtonBounds[0][i].height*player.winHeight), null);
				}else {
					g.fillRect((int)(menuButtonBounds[0][i].x*player.winWidth), (int)(menuButtonBounds[0][i].y*player.winHeight), (int)(menuButtonBounds[0][i].width*player.winWidth), (int)(menuButtonBounds[0][i].height*player.winHeight));
				}
			}
		}else if(shop){
			for(int i=0;i<menuButtonBounds[1].length;i++){
				g.fillRect((int)(menuButtonBounds[1][i].x*player.winWidth), (int)(menuButtonBounds[1][i].y*player.winHeight), (int)(menuButtonBounds[1][i].width*player.winWidth), (int)(menuButtonBounds[1][i].height*player.winHeight));
			}
		}
		if(pause) {
			for(int i=0;i<pauseButtonBounds.length;i++) {
				g.fillRect((int)(pauseButtonBounds[i].x*player.winWidth), (int)(pauseButtonBounds[i].y*player.winHeight), (int)(pauseButtonBounds[i].width*player.winWidth), (int)(pauseButtonBounds[i].height*player.winHeight));
			}
		}
		}else{
			g.fillRect(0, 0, 32*(int)player.winWidth, 32*(int)player.winHeight);
			//g.drawImage(splashImage, 0, 0,(int)((player.winWidth+1.5)*((splashImage.getWidth())/(player.standardWidth))),(int)((player.winHeight+1.5)*((splashImage.getHeight())/(player.standardHeight))), null);
		}
	}
	
//	TODO
//	static BufferedImage splashImage;
//	static{
//		try {
//			splashImage=ImageIO.read(new File("src\\gfx\\splash.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			try {
//				splashImage=ImageIO.read(new File("gfx\\splash.jpg"));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//	TODO
	
	public void updateSplash() {
		splash=true;
	}
	
}
