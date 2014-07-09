package com.Harmon.climber;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.Harmon.climber.Climber.STATE;

public class pauseButton implements MouseListener{

	private Graphics g;
	
	public pauseButton() {
		
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(x >= 355 && x <= 390 && y >= 625 && y <= 660 && Climber.pause == false){
			Climber.state = STATE.PAUSE;
			Climber.pause = true;
		}else if (x >= 355 && x <= 390 && y >= 625 && y <= 660 && Climber.pause == true){
			Climber.state = STATE.PLAY;
			Climber.pause = false;
		}
		if( x >= 350 && x <= 390 && y >= 10 && y <= 35 && Climber.state != STATE.MENU){
			Climber.state = STATE.MENU;
		}else if (x >= 350 && x <= 390 && y >= 10 && y <= 35 && Climber.state == STATE.MENU){
			Climber.state = STATE.PLAY;
		}
	}
	public void mouseReleased(MouseEvent e) {}
}
