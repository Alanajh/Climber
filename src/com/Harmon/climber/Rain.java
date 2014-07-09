package com.Harmon.climber;

import java.awt.Graphics;

public interface Rain {

	public void update();
	public void render(Graphics g);
	
	public double getX();
	public double getY();
}
