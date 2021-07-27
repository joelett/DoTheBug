package de.zigames.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{

	public KeyBoard() {
	}
	
	private static boolean[] keys = new boolean[1024];
	
	public static boolean isKeyDown(int keyCode) {
		if(keyCode>=0&&keyCode<keys.length) return keys[keyCode];
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
