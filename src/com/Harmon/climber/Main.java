package com.Harmon.climber;

import java.awt.Dimension;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Main extends JFrame{

	private static final long serialVersionUID = 1L;
	private static int WIDTH = 400;
	private static int HEIGHT = 800;
	private JFrame frame;
	private Climber c;
	
	public Main() {
		
		c = new Climber();
		frame = new JFrame();
		
		frame.add(c);

		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setTitle("Climber");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public static void main(String[] args) {
		
		new Main();

	}

}
