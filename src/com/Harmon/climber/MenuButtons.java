package com.Harmon.climber;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.Harmon.climber.Climber.STATE;

public class MenuButtons implements MouseListener{

	public MenuButtons() {}
	public void mouseClicked(MouseEvent k) {}
	public void mouseEntered(MouseEvent k) {}
	public void mouseExited(MouseEvent k) {}
	public void mousePressed(MouseEvent k) {}
	public void mouseReleased(MouseEvent k) {
		int x = k.getX();
		int y = k.getY();
		
		//// play button on menu page /////
		if(x >= 105 && x <= 320 && y >= 185 && y <= 275 && Climber.state == STATE.MENU ){
			Climber.state = STATE.COUNTDOWN;
		}
		////  help button on menu page /////
		if(x >= 50 && x <= 265 && y >= 355 && y <= 445 && Climber.state == STATE.MENU){
			Climber.state = STATE.HELP;
		}
		//// scoreboard button on menu page /////
		if(x >= 135 && x <= 350 && y >= 522 && y <= 612){
			Climber.state = STATE.SCOREBOARD;
		}
		//// skip button on menu page ///////
		if(x >= 15 && x <= 60 && y >= 10 && y <= 35){
			Climber.state = STATE.MENU;
		}
	}
}
