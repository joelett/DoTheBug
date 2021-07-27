package game;

import java.awt.Graphics;

public abstract class EnemyEntitie {
	public abstract long getValue();
	public abstract void update(float timeScinceLastFrame);
	public abstract void draw(Graphics g);
	public abstract boolean isAlive();
	public abstract Rectangle getBounds();
}
