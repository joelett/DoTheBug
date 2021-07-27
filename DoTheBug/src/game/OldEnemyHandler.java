package game;

import java.awt.Graphics;

import de.zigames.window.Drawable;

public class OldEnemyHandler extends Drawable{
	
	Player player;
	
	public OldEnemyHandler(Player player) {
		this.player=player;
		
		enemies = new EnemyEntitie[1];
		enemies[0] = new NoEnemy(player,0,0);
	}
	
	EnemyEntitie enemieLvl[][];
	
	EnemyEntitie enemies[];
	
	public void update(float timeScinceLastFrame) {
		if(player.level==-1){
			player.level=0;
			enemies = enemieLvl[0];
		}else{
			if(player.level<enemieLvl.length){
				boolean oneAlive=false;
				for(int i=0;i<enemies.length;i++){
					if(enemies[i].isAlive()){
						oneAlive=true;
					}
				}
				if(!oneAlive&&player.alive){
					player.level++;
					if(player.level!=enemieLvl.length){
						enemies = enemieLvl[player.level];
					}else{
						System.out.println("WINNER!");
					}
				}
			}
		}
		
		for(int i=0;i<enemies.length;i++) {
			enemies[i].update(timeScinceLastFrame);
		}
	}

	@Override
	public void draw(Graphics g) {
		for(int i=0;i<enemies.length;i++) {
			enemies[i].draw(g);
		}
	}
}
