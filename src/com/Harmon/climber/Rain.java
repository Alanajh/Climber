package com.Harmon.climber;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Rain {

	private double x;
	private double y;
	private double movementY;
	private Random r;
	private int counter;
	private Climber climber;
	
	public Rain(double x, double y, Climber climber) {
		this.x = x;
		this.y = y - 30;
	
	}

	public void update(){
	
		y += .4;
		if(y > 750){
			y = 0;
		}
	
	}
	public void render(Graphics g){
		
		g.setColor(new Color(12, 100, 200));
		g.fillRoundRect((int)x, (int)y, 6, 30, 30, 30);
		g.setColor(new Color(120, 10, 20));
		g.drawRoundRect((int)x, (int)y, 6, 30, 30, 30);
	}
}
