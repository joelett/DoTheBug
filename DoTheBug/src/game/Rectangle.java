package game;

public class Rectangle {
	
	public float x,y,width,height;
	
	public Rectangle(float x,float y,float width,float height) {
		this.x=x;this.y=y;this.width=width;this.height=height;
	}
	
	public boolean intersect(Rectangle r) {
		return (x <= r.x+r.width &&
		          r.x <= x+width &&
		          y <= r.y+r.height &&
		          r.y <= y+height);
	}

}
