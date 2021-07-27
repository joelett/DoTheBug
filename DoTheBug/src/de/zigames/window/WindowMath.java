package de.zigames.window;

public class WindowMath {
	
	private static long lastFrame=System.currentTimeMillis();
	public static float timeScinceLastFrame() {
		long thisFrame = System.currentTimeMillis();
		float tsclf = (float)(thisFrame-lastFrame)/1000f;
		lastFrame = thisFrame;
		return tsclf;
		
	}

}
