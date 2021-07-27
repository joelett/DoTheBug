package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy03 extends EnemyEntitie{
	
	Player player;
	float speed;
	
	float x,y;
	float width,height;
	
	Rectangle bounds;
	
	public Enemy03(Player player,float x,float y) {
		this.player=player;
		speed=-12f;
		this.x=x;
		this.y=y;
		width=5;height=5;
		destinationX=player.x;
		destinationY=player.y;
		bounds = new Rectangle(x,y,width,height);
		child = new NoEnemy(player,x,y);
	}
	
	float dx,dy;
	
	static BufferedImage img;
	static {
		try {
			img = ImageIO.read(new File("src\\gfx\\enemy01.png"));
		} catch (IOException e) {
			try {
				img = ImageIO.read(new File("gfx\\enemy01.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	float destinationX;
	float destinationY;
	
	public void update(float timeScinceLastFrame) {
		if(player.alive&&alive) {
		//CALCULATING DIRECTION				
				
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
		}else {
			//MOVING PATTERN
			destinationX=player.x;
			destinationY=player.y;
			/////////////////
		}
		///////////////////
		//UPDATING BOUNDINGBOX
		bounds.x=x+1*size;
		bounds.y=y+1*size;
		if(bounds.intersect(player.bounds)) {
			player.alive=false;
		}
		//////////////////////
		}
		
		if(alive){
			size-=0.05f*timeScinceLastFrame;
			bounds.width=(img.getWidth()-2)*size;
			bounds.height=(img.getHeight()-2)*size;
			if(size<0f){
				alive=false;
			}else if(size<1&&!child.isAlive()){
				child=new Enemy00(player,x,y);
			}
		}
		
		child.update(timeScinceLastFrame);
	}
	
	EnemyEntitie child;
	
	boolean alive=true;
	float size=2f;
	
	public void draw(Graphics g) {		
		//DRAWING ENEMY
		if(alive){
		g.drawImage(img, (int)(((x*player.winWidth))),(int)(((y*player.winHeight))),(int)(img.getWidth()*player.winWidth*size),(int)(img.getHeight()*player.winHeight*size),null);
		}
		child.draw(g);
		////////////////
		//g.drawRect((int)(bounds.x*player.winWidth), (int)(bounds.y*player.winHeight), (int)(bounds.width*player.winWidth), (int)(bounds.height*player.winHeight));
	}
	
	@Override
	public boolean isAlive() {
		return alive||child.isAlive();
	}
	
	long value=2;
	
	@Override
	public long getValue() {
		return value;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,width*size,height*size);
	}
	
}
