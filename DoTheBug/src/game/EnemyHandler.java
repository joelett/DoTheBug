package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.zigames.window.Drawable;

public class EnemyHandler extends Drawable{
	
	Player player;
	
	public EnemyHandler(Player player) {
		this.player=player;
	}
	
	float enemySpawnRate[] = {5000,3000,2100,2100,2100};//IN MILISECONDS
	float enemyProbabillity[][] = {// e0;e1;e2;e3;e4...
			{0.99f,0.01f,0,0,0},{0.95f,0.00f,0,0.05f,0},{0.55f,0.35f,0,0.1f,0},{0.45f,0.1f,0.30f,0.1f,0.05f},{0.35f,0.15f,0.20f,0.2f,0.1f}
	};
	
	List<EnemyEntitie> enemies = new ArrayList<EnemyEntitie>();
	List<Coin> coins = new ArrayList<Coin>();
	
	long score = 0;
	float timer = 2000f;
	
	public void update(float timeScinceLastFrame) {
		if(player.alive) {
			for(Coin c:coins){
				if(!c.alive){
					coins.remove(c);
					break;
				}
			}
			
			for(Coin c:coins){
				c.update(timeScinceLastFrame);
			}
			
			for(EnemyEntitie e:enemies) {
				if(!e.isAlive()) {
					coins.add(new Coin(e.getBounds().x,e.getBounds().y,player));
					enemies.remove(e);
					score+=e.getValue();
					break;
				}
			}
			
			if((score>=3&&player.level==0)||(score>=15&&player.level==1)||(score>=30&&player.level==2)
					||(score>=45&&player.level==3)) {
				player.level++;
			}
			
			if(timer>=enemySpawnRate[player.level]) {
				Random random = new Random();
				float rand = random.nextFloat();
				if(rand<enemyProbabillity[player.level][0]) {
					enemies.add(new Enemy00(player,-5,-5));
				}else if(rand<enemyProbabillity[player.level][0]+enemyProbabillity[player.level][1]) {
					enemies.add(new Enemy01(player,-5,-5));
				}else if(rand<enemyProbabillity[player.level][0]+enemyProbabillity[player.level][1]+enemyProbabillity[player.level][2]) {
					enemies.add(new Enemy02(player,-5,-5));
				}else if(rand<enemyProbabillity[player.level][0]+enemyProbabillity[player.level][1]+enemyProbabillity[player.level][2]+enemyProbabillity[player.level][3]) {
					enemies.add(new Enemy03(player,-5,-5));
				}
				//enemies.add(new Enemy00(player,-16,-16));
				timer = 0f;
			}else {
				timer+=timeScinceLastFrame*1000f;
			}
		}
		for(EnemyEntitie e:enemies) {
			e.update(timeScinceLastFrame);
		}
	}

	@Override
	public void draw(Graphics g) {
		for(EnemyEntitie e:enemies) {
			e.draw(g);
		}
		for(Coin c:coins){
			c.draw(g);
		}
	}

	public void reset() {
		enemies = new ArrayList<EnemyEntitie>();
		coins = new ArrayList<Coin>();
		///////////////
		score=0;
	}
}
