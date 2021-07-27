package game;

import java.awt.Color;
import java.awt.Graphics;

public class Coin {
	
	float x,y;
	float size=0.5f;
	Player player;
	
	public Coin(float x,float y,Player p) {
		this.x=x;
		this.y=y;
		player=p;
	}
	
	public void update(float timeScinceLastFrame){
		if(new Rectangle(x,y,size,size).intersect(player.bounds)){
			player.coins++;
			alive=false;
		}else{
			if(size>0f){
				size-=0.1f*timeScinceLastFrame;
			}else{
				alive=false;
			}
		}
	}
	
	boolean alive=true;
	
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		//TODO GRAPHICS
		g.fillRect((int)(x*player.winWidth), (int)(y*player.winHeight), (int)(size*player.winWidth), (int)(size*player.winHeight));
	}

}
