package de.zigames.window;

import javax.swing.JFrame;

public class WindowInit {
	
	Frame f;
	
	public WindowInit(String name, int width, int height, Drawable...drawables) {
		f = new Frame(name,drawables);
		f.setSize(width, height);
		//f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public WindowInit(String name,int x,int y, int width, int height, Drawable...drawables) {
		f = new Frame(name,drawables);
		f.setBounds(x, y, width, height);
		//f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Frame getFrame() {
		return f;
	}

}
