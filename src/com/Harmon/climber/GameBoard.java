package com.Harmon.climber;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class GameBoard {

	private Image gameboard, lossScreen, rox, rox2, rox3, rox4;
	private ImageIcon iBoard, iLoss, iRox, iRox2, iRox3, iRox4;
	
	public double boardX;
	public double boardY, boardYY;
	
	public double roxX;
	public double roxY;

	private double movement, movement2, roxMove;
	private Random r;
	
	public GameBoard() {
		
		iBoard = new ImageIcon(getClass().getResource("notBlurredMountains.png"));
		gameboard = iBoard.getImage();
		
		iLoss = new ImageIcon(getClass().getResource("loseScreen.png"));
		lossScreen = iLoss.getImage();
		
		iRox = new ImageIcon(getClass().getResource("rockImg1.png"));
		rox = iRox.getImage();
		
		iRox2 = new ImageIcon(getClass().getResource("rockImg2.png"));
		rox2 = iRox2.getImage();
				
		iRox3 = new ImageIcon(getClass().getResource("rockImg3.png"));
		rox3 = iRox3.getImage();
		
		iRox4 = new ImageIcon(getClass().getResource("rockImg4.png"));
		rox4 = iRox4.getImage();
		
		boardX = 0;
		boardY = 0 - 100;
		boardYY = 0 - 800;
		
		roxX = 0;
		roxY = 0;
		
		movement = 0.1;
		movement2 = 0.25;
		
	}
	public void update(){
		
		boardY += movement;
		boardYY += movement;
	
		roxY += movement2;
	}
	public double getRoxX() {
		return roxX;
	}
	public double getRoxY() {
		return roxY;
	}
	public double getBoardX(){
		return boardX;
	}
	public double getBoardY(){
		return boardY;
	}
	public double getBoardYY(){
		return boardYY;
	}
	public Image getRox(){
		return rox;
	}
	public Image getBoard(){
		return gameboard;
	}
	public Image getLoss(){
		return lossScreen;
	}

}
