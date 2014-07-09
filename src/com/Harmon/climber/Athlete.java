package com.Harmon.climber;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Athlete implements KeyListener{

	private ImageIcon i, iMountain, iRockI, iRockII;
	private Image climber, mountainImg, rockI, rockII;
	public int x, y, xx, yy, rockY, rockIX, rockIXX, rockIY, rockIYY;
	private int key;
	public double mountainY, vy;
	private double speed;
	
	public int r, where;
	public Random ranFall = new Random();
	public double ranFallD;
	
	public Athlete(){
		i = new ImageIcon(getClass().getResource("climberIcon2.png"));
		climber = i.getImage();
		
		iMountain = new ImageIcon(getClass().getResource("fullBgWithMountains.png"));
		mountainImg = iMountain.getImage();
		
		iRockI = new ImageIcon(getClass().getResource("rockImg1.png"));
		rockI = iRockI.getImage();
		
		iRockII = new ImageIcon(getClass().getResource("rockImg2.png"));
		rockII = iRockII.getImage();
		
		speed = 0.1;
		mountainY = 0 - 200;
		vy = 0 - 200;
		//// left and right /////
		rockIX = ranFall.nextInt(250) + 10;
		rockIXX = ranFall.nextInt(250) + 10;
		////up and down ///////
		rockIY = ranFall.nextInt(100) - 200 ;
		rockIYY = ranFall.nextInt(100) - 200 ;
		
		//// mountain collision //////
	
		rockY = 0 - 280;
		r = (int) (Math.random() * (340));
		ranFallD = 1 + Math.random() * (3.0);
		where = (int) (Math.random() * (80));
		
		x = 100;
		y = 100;
	}
	public void mountainUpdate(){
		mountainY += speed;
		vy += speed;
		rockIY += 2 ;
		rockIYY += 1;
	}
	public void update(){
		x += xx;
		y += yy;
		
		if(x <= 0 - 6){ x = 0 - 6;}
		if(x >= 345){ x = 345;}
		if(y <= 40){ y = 40;}
		if(y >= 600){ y = 600;}
		
		rockIY += rockIYY;
	}
	public double getVY(){
		return vy;
	}
	public double getMountainY(){
		return mountainY;
	}
	public Image getMountainImage(){
		return mountainImg;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXX(){
		return xx;
	}
	public int getYY(){
		return yy;
	}
	public Image getImage(){
		return climber;
	}
	public int getRockIX(){
		return rockIX;
	}
	public int getRockIXX(){
		return rockIXX;
	}
	public int getRockIY(){
		return rockIY;
	}
	public int getRockIYY(){
		return rockIYY;
	}
	public Image getRockI(){
		return rockI;
	}
	public Image getRockII(){
		return rockII;
	}
	public void keyPressed(KeyEvent e){
		key = e.getKeyCode();
		if(key == KeyEvent.VK_UP){ yy = -1;} 
		if(key == KeyEvent.VK_DOWN){ yy = 1;}
		if(key == KeyEvent.VK_LEFT){ xx = -1;}
		if(key == KeyEvent.VK_RIGHT){ xx = 1;}
	}

	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){ xx = 0;}
		if (key == KeyEvent.VK_RIGHT){ xx = 0;}
		if (key == KeyEvent.VK_UP){yy = 0;}
		if (key == KeyEvent.VK_DOWN){yy = 0;}
	}
	public void keyTyped(KeyEvent e) {}
	public int getKeyReturn(){
		return key;
	}
}

