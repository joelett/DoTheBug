package de.zigames.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Image {

	public Image() {
		
	}
	
	public static int[][] getPixelArray(BufferedImage image){
		int pixel[][] = new int[image.getWidth()][image.getHeight()];
		for(int row=0;row<image.getHeight();row++) {
			for(int col=0;col<image.getWidth();col++) {
				pixel[col][row]=image.getRGB(col, row);
			}
		}
		return pixel;
	}
	
	public static int[][] getPixelArray(BufferedImage image,int multiply){
		int pixel[][] = new int[image.getWidth()*multiply][image.getHeight()*multiply];
		for(int row=0;row<image.getWidth();row++) {
			for(int col=0;col<image.getHeight();col++) {
				pixel[col*multiply][row*multiply]=image.getRGB(col, row);
				for(int row2=0;row2<multiply;row2++) {
					for(int col2=0;col2<multiply;col2++) {
						if(col!=0&&row!=0) {
							pixel[(col*multiply)+col2][(row*multiply)+row2]=pixel[col*multiply][row*multiply];
						}
					}
				}
			}
		}
		return pixel;
	}
	
	public static int[][] maximize(int pixelArray[][],int multiply){
		int[][] pixel = new int[pixelArray[0].length*multiply][pixelArray.length*multiply];
		for(int row=0;row<pixelArray.length;row++) {
			for(int col=0;col<pixelArray[0].length;col++) {
				pixel[col*multiply][row*multiply]=pixelArray[col][row];
				for(int row2=0;row2<multiply;row2++) {
					for(int col2=0;col2<multiply;col2++) {
						if(col!=0&&row!=0) {
							pixel[(col*multiply)+col2][(row*multiply)+row2]=pixel[col*multiply][row*multiply];
						}
					}
				}
			}
		}
		return pixel;
	}
	
	public static void draw(Graphics g, BufferedImage img, int x, int y) {
		int pixel[][]=getPixelArray(img);
		for(int row=0;row<pixel.length;row++) {
			for(int col=0;col<pixel[row].length;col++) {
				g.setColor(new Color(pixel[row][col]));
				g.fillRect(x+col, y+row, 1, 1);
			}
		}
	}
	
	public static void draw(Graphics g, int[][] pixel, int x, int y) {
		for(int row=0;row<pixel.length;row++) {
			for(int col=0;col<pixel[row].length;col++) {
				g.setColor(new Color(pixel[row][col]));
				g.fillRect(x+col, y+row, 1, 1);
			}
		}
	}

}
