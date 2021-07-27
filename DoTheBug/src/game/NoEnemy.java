package game;

import java.awt.Graphics;

public class NoEnemy extends EnemyEntitie{
	public NoEnemy(Player player,float x,float y) {}
	public void draw(Graphics g) {}
	public void update(float timeScinceLastFrame) {}
	@Override
	public boolean isAlive() {return false;}
	@Override
	public long getValue() {return 0;}
	@Override
	public Rectangle getBounds() {return null;}
}
