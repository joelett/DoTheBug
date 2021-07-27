package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.zigames.window.Drawable;

public class VFX extends Drawable{

	public VFX(EnemyHandler elist,Player player) {
		this.player=player;
		this.elist=elist;
	}
	
	Player player;
	boolean paused;
	float timeScinceLastFrame;
	EnemyHandler elist;
	
	public void update(boolean paused,float timeScinceLastFrame){
		this.paused=paused;
		this.timeScinceLastFrame=timeScinceLastFrame;
	}
	
	final float fontSize=1.30f;
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial", Font.BOLD, (int)(fontSize*player.winWidth)));
		if(paused){
			g.drawString(">>>PAUSED<<<", (int)(12f*player.winWidth), (int)(16*player.winHeight));
			g.drawString(">>>PRESS ESC<<<", (int)(11f*player.winWidth), (int)(17*player.winHeight));
		}else {
			g.drawString(elist.score+"", (int)(30*player.winWidth), (int)(30*player.winHeight));
			g.drawString(player.coins+"", (int)(2.5*player.winWidth), (int)(30*player.winHeight));
			if(player.ammo>0) {
				g.drawString(player.ammo+"", (int)(2.5*player.winWidth), (int)(28*player.winHeight));
			}
		}
	}

}
