package de.zigames.window;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener{

	public Mouse() {
		// TODO Auto-generated constructor stub
	}
	
	private static boolean[] keys = new boolean[1024];
	
	public static boolean isKeyDown(int keyCode) {
		if(keyCode>=0&&keyCode<keys.length) return keys[keyCode];
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int keyCode = e.getButton();
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int keyCode = e.getButton();
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]=false;
	}

}
