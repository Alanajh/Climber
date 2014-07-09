package com.Harmon.climber;

import java.awt.Color;
import java.awt.Graphics;

public class LevelRain implements Rain{

	private double x, y;
	private Climber climber;
	
	public LevelRain(double x, double y, Climber climber) {
		this.x = x;
		this.y = y;
		this.climber = climber;
	}
	public void update() {
		y += 1;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRoundRect((int)x, (int)y, 3, 35, 15, 15);
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}
