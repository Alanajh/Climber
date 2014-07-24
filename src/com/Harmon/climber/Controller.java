package com.Harmon.climber;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

	private LinkedList<Rain> rain = new LinkedList<Rain>();
	private Rain raindrop;
	private Climber climber;
	
	private Random r = new Random();
	
	public Controller(Climber climber) {
		this.climber = climber;
	
		for( int x = 10; x < 500; x += 40){
			//for(int y = 0; y < 300; y += 300){
				addRain(new Rain(r.nextInt(500), r.nextInt(200) , climber));
			//}
		}
	}
	public void update(){

		for(int i = 0; i < rain.size(); i++){
			raindrop = rain.get(i);
			raindrop.update();
		}
	}
	public void render(Graphics g){
		for(int i = 0; i < rain.size(); i++){
			raindrop = rain.get(i);
			raindrop.render(g);
		}
	}
	public void addRain(Rain block){ rain.add(block);}
	public void removeRain(Rain block){ rain.remove(block);}
}
