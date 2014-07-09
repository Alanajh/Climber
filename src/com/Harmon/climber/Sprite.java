package com.Harmon.climber;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Sprite {
	private long time;
	private long oldTime;
	private BufferedImage img = null;
	private BufferedImage[] sprites;
	private int frameRate;
	private int maxFrames;
	private int index = 0;
	private int x = 0;
	private int y = 0;
	
	// CONSTRUCTOR ///
	public Sprite(String path, int num, int rate){
		frameRate = rate;
		maxFrames = num;
		try{
			img = ImageIO.read(new File("climberSS.png"));
		}catch (IOException e){
			e.printStackTrace();
		}
		sprites = new BufferedImage[maxFrames];
		for(int i = 0; i < maxFrames; i++)
			sprites[i] = img.getSubimage(i * img.getWidth()/maxFrames, 0, img.getWidth()/maxFrames, img.getHeight());
	}
	public void Play(int start, int end){
		time = System.currentTimeMillis();
		if(oldTime + frameRate > time)
			return;
		oldTime = time;
		if(this.index < start || this.index >= end) 
			this.index = start;
		else this.index += 1;
	}
	public BufferedImage getSprite(){
		return this.sprites[this.index]; // returns current frame
	}
		public void setX(int xx){
			this.x = xx;
		}
		public void setY(int yy){
			this.y = yy;
		}
		public int getX(){
			return this.x;
		}
		public int getY(){
			return this.y;
		}
	private BufferedImage BuffIng(ImageIcon img){
		int w = img.getIconWidth();
		int h = img.getIconHeight();
		BufferedImage img2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img2.createGraphics();
		g.drawImage(img.getImage(), 0, 0, w, h, 0, 0, w, h, null);
		g.dispose();
		return img2;
	}
}

