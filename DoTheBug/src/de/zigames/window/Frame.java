package de.zigames.window;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
	
	Drawable[] drawable;
	Screen screen;
	
	public Frame(String windowname, Drawable... drawables) {
		super(windowname);
		drawable=drawables;
		screen = new Screen();
		screen.setBounds(0, 0, getWidth(), getHeight());
		add(screen);
		addMouseListener(new Mouse());
		addKeyListener(new KeyBoard());
	}
	
	public void repaintScreen() {
		screen.repaint();
	}
	
	private class Screen extends JLabel{
		
		@Override
		protected void paintComponent(Graphics g) {
			for(int i=0;i<drawable.length;i++) {
				drawable[i].draw(g);
			}
		}
	}

}
