package com.Harmon.climber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Menu {

	public int menuX, menuY, titleX, titleY, staticX, staticY;
	public Image menu, title, leftLeaves, rightLeaves, staticImg;
	public ImageIcon iMenu, iTitle, iLeft, iRight, iStatic;
	public double lX, lY, rX, rY, growX, growY;
	
	public Menu() {
		
		iMenu = new ImageIcon(getClass().getResource("blurredMountains.png"));
		menu = iMenu.getImage();
		
		iTitle = new ImageIcon(getClass().getResource("climberImg.png"));
		title = iTitle.getImage();
		
		iLeft = new ImageIcon(getClass().getResource("leftLeaves.png"));
		leftLeaves = iLeft.getImage();
		
		iRight = new ImageIcon(getClass().getResource("rightLeaves.png"));
		rightLeaves = iRight.getImage();
		
		iStatic = new ImageIcon(getClass().getResource("staticMenuWithBtns.png"));
		staticImg = iStatic.getImage();
		
		menuX = 0;
		menuY = 0;
		titleX = 40;
		titleY = 100;
		lX = 0;
		lY = 0; 
		rX = 0; 
		rY = 0;
		staticX = 0;
		staticY = 0;
		
		growX = 30;
		growY = 30;
	
	}
	public double getGrowX() {
		return growX;
	}
	public double getGrowY() {
		return growY;
	}
	public int getMenuX(){
		return menuX;
	}
	public int getMenuY(){
		return menuY;
	}
	public int getTitleY(){
		return titleY;
	}
	public double getLX(){
		return lX;
	}
	public double getLY(){
		return lY;
	}
	public double getRX(){
		return rX;
	}
	public double getRY(){
		return rY;
	}
	public int getStaticX(){
		return staticX;
	}
	public int getStaticY(){
		return staticY;
	}
	public Image getTitle(){
		return title;
	}
	public Image getMenuImg(){
		return menu;
	}
	public Image getLeft(){
		return leftLeaves;
	}
	public Image getRight(){
		return rightLeaves;
	}
	public Image getStaticImg(){
		return staticImg;
	}

}
