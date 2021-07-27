package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.zigames.window.Drawable;

public class Player extends Drawable{
	
	float width=5;
	float height=5;
	
	final int standardWidth=32;
	final int standardHeight=32;
	
	float winWidth=1f;// 32/32
	float winHeight=1f;// 32/32
	float x,y;
	
	static BufferedImage img;
	
	public Player() {
		try {
			img = ImageIO.read(new File("src\\gfx\\player.png"));
		} catch (IOException e) {
			try {
				img = ImageIO.read(new File("gfx\\player.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		size=0.5f;
		x=16;
		y=16;
		bounds = new Rectangle(x,y,width,height);
		nbounds = new Rectangle(x,y,width,height);
	}
	
	float dx,dy,mx,my;
	
	float speed;
	float size;
	
	int ammo=0;
	
	int fps;
	
	Rectangle bounds;
	Rectangle nbounds;
	
	public void update(float timeScinceLastFrame,int mx,int my) {
		if(alive&&ready) {
		fps = (int) (1000/(timeScinceLastFrame*1000));
		
		//CALCULATING DIRECTION
		this.mx=mx;
		this.my=my;
		
		float destinationX=(mx-(img.getWidth()*winWidth*size)/2)/winWidth;
		float destinationY=(my-(img.getHeight()*winHeight*size)/2)/winHeight;
		
		float tdx = x-destinationX;
		float tdy = y-destinationY;
		float ttdx = (tdx*tdx);
		float ttdy = (tdy*tdy);
		float hypPow = (ttdx+ttdy);
		float hyp = (float)(Math.sqrt(hypPow));
		dx = (float)((1f/hyp)*tdx);
		dy = (float)((1f/hyp)*tdy);
		/////////////////////////
		//MOVE IN DIRECTION
		if(((destinationX-x)>0.01f||(destinationX-x)<-0.01f)||((destinationY-y)>0.01f||(destinationY-y)<-0.01f)) {
				x+=(dx*speed*timeScinceLastFrame);
				y+=(dy*speed*timeScinceLastFrame);
		}
		////////////////////
		//UPDATING BOUNDINGBOX
		bounds.x=x;
		bounds.y=y;
		bounds.width=nbounds.width*size;
		bounds.height=nbounds.height*size;
		//////////////////////
		}
		//IDK IF IT HELPED BUT IT LOOKS LIKE IT DOES
		if(!ready) {
			if(frames!=0) {
				ready=true;
			}
		}
		/////////////////////////////////////////////
	}
	
	int frames;
	
	public void setWindowSize(int width,int height) {
		winWidth=width/standardWidth;
		winHeight=height/standardHeight;
	}
	
	boolean alive=true;
	boolean ready=false;
	
	boolean godmode=false;
	
	int level=0;
	public int coins=0;

	public void draw(Graphics g) {
		//BACKGROUND
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int)(50*winWidth), (int)(50*winHeight));
		////////////
		if(godmode) {
			alive=true;
		}
		
		if(alive) {
		frames++;
		//DRAWING PLAYER
		g.drawImage(img, (int)(((x*winWidth))),(int)(((y*winHeight))),(int)(img.getWidth()*winWidth*size),(int)(img.getHeight()*winHeight*size),null);
		////////////////
		//DEBUGGING PURPOSES
		/**g.setColor(Color.BLUE);
		g.drawLine((int)((winWidth*x)), (int)((winHeight*y)), (int)(winWidth*x)+(int)((dx)*winWidth),(int)(winHeight*y)+(int)((dy)*winHeight));
		g.setColor(Color.GREEN);
		g.drawLine((int)((winWidth*x)), (int)((winHeight*y)), (int)(mx), (int)(my));*/
		//////////////////////
		}
		//g.setColor(Color.BLACK);
		//g.drawRect((int)(bounds.x*winWidth), (int)(bounds.y*winHeight), (int)(bounds.width*winWidth), (int)(bounds.height*winHeight));
	}

	public void reset() {
		alive=true;
		ready=false;
		level=0;
		size=0.5f;
		x=16;
		y=16;
		dx=16;
		dy=16;
		speed=-30f;
		bounds = new Rectangle(x,y,width,height);
		nbounds = new Rectangle(x,y,width,height);
		
		bounds.x=x;
		bounds.y=y;
		bounds.width=nbounds.width*size;
		bounds.height=nbounds.height*size;
		
		frames=0;
		System.out.println("RESETTED!");
	}

}
