package com.Harmon.climber;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Climber extends JPanel implements Runnable {

	private Athlete athlete;
	private SpriteSheet sprite;
	private LevelRain rain;
	private Menu menu;
	private GameBoard board;
	private Sound sound;

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 600;
	public static int HEIGHT = 800;

	private boolean running = false;

	private Graphics g;
	private Graphics2D g2d;
	private BufferedImage backbuffer;
	private Image img, mountain, man, manLeft, manRight, bird, blurredMt;
	private int currentFrame = 0;
	private int currentBirdFrame = 0;
	private int climbFrames = 2;
	private int birdFrames = 16;

	private float birdRadius = 75; // // birds radius
	private float birdX2 = birdRadius + 25;
	private float birdY2 = birdRadius + 15;
	private float birdSpeedX = 2;
	private float birdSpeedY = 1;
	private final static int UPDATE_RATE = 25;

	private int direction = 1;
	private int frameCount = 0;
	private int frameCountBird = 0;
	private int frameDelay = 20;
	private int birdDelay = 0;

	private int birdX, birdXX, birdY, birdYY;
	private int energy = 100;
	private Random ran;

	private int rainCount = 3;
	private int rainX = 350;
	private int rainY = 0 - 25;

	private Thread thread;
	private Thread threadSound;
	public static boolean pause = false;

	private String menuString = "Menu";
	private String playString = "Play";
	private String skipString = "skip";

	private int playX = 102;
	private int helpX = 47;
	private int scoreX = 138;
	private int playY = 180;
	private int helpY = 350;
	private int scoreY = 515;

	private int bounce = 5;
	
	private String count = "3";
	private int countdown = 3;
	private int funDelay = 50;
	private int scoreDelay = 1000;
	private int countDownDelay = 1000;
	private Timer fun, score, countDown;
	
	private Stroke stroke;
	
	public static STATE state;

	public static enum STATE {
		COUNTDOWN, INTRO, MENU, HELP, PLAY, SCOREBOARD, PAUSE, PREINTRO, END
	}

	// // Constructor ////////
	public Climber() {
		backbuffer = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		backbuffer.createGraphics();

		Toolkit tk = Toolkit.getDefaultToolkit();
		blurredMt = tk.getImage(getURL("blurredMountains.png"));
		mountain = tk.getImage(getURL("fullBgWithMountains.png"));
		man = tk.getImage(getURL("climberSS.png"));
		manLeft = tk.getImage(getURL("climberLeftSS.png"));
		manRight = tk.getImage(getURL("climberRightSS.png"));
		bird = tk.getImage(getURL("Redbird.png"));

		ran = new Random();

		birdX = 450;
		birdXX = birdX - 40;
		birdY = 100 + (ran.nextInt(600));
		birdYY = birdY - 60;
		
		stroke = new BasicStroke(3);

		state = STATE.INTRO;
		this.addMouseListener(new MenuButtons());
		this.addMouseListener(new pauseButton());
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);

		sound = new Sound();
		sound.BackgroundMusic();
		
		rain = new LevelRain(rainX, rainY, this);
		menu = new Menu();
		sprite = new SpriteSheet();
		athlete = new Athlete();
		board = new GameBoard();
		
		//// timers for menu buttons ////
		fun = new Timer(funDelay, new FunTimerListener());
		fun.start();
		
		/// timer for countdown /////
		countDown = new Timer(countDownDelay, new CountTimerListener());
		countDown.start();
		
		// // GAME THREAD && SOUND THREAD //////
		thread = new Thread(this);
		threadSound = new Thread();

		thread.start();
		
		Thread birdThread = new Thread() {
			public void run() {
				birdX2 = 450;
				while (true) {
					
					// //// bird animation //////
					frameCountBird++;
					if (frameCountBird > birdDelay) {
						frameCountBird = 0;
						currentBirdFrame += direction;
						if (currentBirdFrame > birdFrames - 1) {
							currentBirdFrame = 0;
						} else if (currentBirdFrame < 0) {
							currentBirdFrame = birdFrames - 1;
						}
					}

					birdX2 -= birdSpeedX;
					birdY2 += birdSpeedY;

					if (birdX2 == 460) {
						birdX2 += birdSpeedX;
					}

					if (birdY2 - birdRadius < 0) {
						birdSpeedY = -birdSpeedY;
						birdY2 = birdRadius;
					} else if (birdY2 + birdRadius > 600) {
						birdSpeedY = -birdSpeedY;
						birdY2 = 600 - birdRadius;
					}
					repaint();
					try {
						Thread.sleep(1000 / UPDATE_RATE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		birdThread.start();
}
	public class CountTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent eee){
			if(state == STATE.COUNTDOWN){	
				countdown --;
			}
		}
	}
	public class FunTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(state == STATE.MENU){
				helpY -= bounce;
				playX += bounce;
				scoreX -= bounce;
				if(helpY <= 320 || playX >= 132 || scoreX <= 108){
					bounce --;
				}
				if(helpY >= 350 ){
					bounce ++;
			}
		}
	}
}

	public URL getURL(String filename) {
		URL url = null;
		try {
			url = this.getClass().getResource(filename);
		} catch (Exception e) {
		}
		return url;
	}

	public void run() {
		running = true;
		threadSound.start();////////////////// maybe cause of lag. There may not be a reason for this thread at the moment

		while (running) {
			render();
			repaint();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void render() {

		if (img == null) { // //// create the buffer
			img = createImage(WIDTH, HEIGHT);
			if (img == null) {
				return;
			} else
				g = img.getGraphics();
		}
		
		if (state == STATE.PLAY) {

			g.drawImage(mountain, 0, 0, null);
			rain.update();
			athlete.mountainUpdate();
			athlete.update();
			board.update();
		}
		if (state == STATE.PAUSE) {}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// /// SHOW GAME COMPONENTS ///////
		// //// STATE BUTTONS ////////

		double move = 0.5;
		double move2 = 0.4;
		
		if (state == STATE.INTRO) {
			
			// /// splash screen intro ////////

			g.drawImage(menu.getMenuImg(), menu.menuX, menu.menuY, null);
			g.drawImage(menu.title, menu.titleX, menu.titleY, (int) menu.growX, (int) menu.growY, null);
			g.drawImage(menu.getLeft(), (int) menu.lX, (int) menu.lY, null);
			g.drawImage(menu.getRight(), (int) menu.rX, (int) menu.rY, null);

			sprite.drawFrame(bird, g2d, (int) birdX2, (int) birdY2, 4, currentBirdFrame, 75, 54); // /NOTE: set x and y to mimic movement.

			g.setColor(Color.GRAY);
			g.fillRoundRect(15, 10, 45, 25, 25, 25);
			g.setColor(Color.WHITE);
			g.drawString(skipString, 23, 26);

			menu.lX -= move;
			menu.lY += move;
			menu.rX += move2;
			menu.rY += move2;
			menu.growX += move;
			menu.growY += move;

			if (menu.growX >= 340) {
				menu.growX = 340;
			}
			if (menu.growY >= 550) {
				menu.growY = 550;
				menu.titleY++;
			}
			if (menu.titleY >= 800) {
				g.drawImage(menu.getStaticImg(), menu.staticX, menu.staticY, null);
				state = STATE.MENU;
			}
		}/// end of intro state
		
		// /// start menu screen
		if (state == STATE.MENU){
			
			g.drawImage(menu.getStaticImg(), menu.staticX, menu.staticY, null);
			g.setColor(Color.GREEN);
			g.drawRoundRect(playX, playY, 220, 99, 15, 15);
			g.drawRoundRect(helpX, helpY, 220, 99, 15, 15);
			g.drawRoundRect(scoreX, scoreY, 220, 99, 15, 15);
		} /// end of menu screen
		//// start of help screen
		if (state == STATE.HELP) {
			g.setColor(Color.BLACK);
			g.fill3DRect(10, 10, 100, 100, true);
			g.fillRoundRect(10, 10, 380, 655, 15, 15);
			g.setColor(Color.RED);
			g.drawString("This is the help screen", 150, 350);
		}//// end of help screen
		
		/// start of countdown 
		if(state == STATE.COUNTDOWN){
			String count = Integer.toString(countdown);
			
			g.drawImage(blurredMt, 0, 0, this);
			g.setColor(new Color(0, 111, 15));
			g.setFont(new Font("Serif", Font.BOLD, 200));
			g.drawString(count, WIDTH/2 - 150, HEIGHT/2 - 50);
		
			if(countdown < 0){
				countDown.stop();
				state = STATE.PLAY;
			}
		}/// end of countdown state
		/// start of scoreboard screen
		if (state == STATE.SCOREBOARD) {
			g.drawString("This is the SCOREBOARD", 80, 300);
		}////end of scoreboard screen

		/// start of play and pause state.
		// /// GAMEBOARD MOVEMENT ///////////
		///draw the gameboard on the screen
		if (state == STATE.PLAY || state == STATE.PAUSE) {
			
			g2d.drawImage(blurredMt, 0, 0, null);
			
			g.drawImage(board.getBoard(), (int) board.getBoardX(),
					(int) board.getBoardY(), null);
			g.drawImage(board.getBoard(), (int) board.getBoardX(),
					(int) board.getBoardYY(), null);
			
		//start of gameboard bound detection
			if (board.getBoardY() >= 700) {
				board.boardY = 0 - 755;
			}
			if (board.getBoardYY() >= 700) {
				board.boardYY = 0 - 755;
			}/// end of bound detection 
			
			//// menu button
			g.setFont(new Font("Serif", Font.PLAIN, 14));
			g.setColor(Color.RED);
			g.fillRoundRect(350, 10, 40, 25, 25, 25);
			g.setColor(Color.BLACK);
			g.drawRoundRect(350, 10, 40, 25, 25, 25);
			g.setColor(Color.WHITE);
			
			/// pause button
			g.setColor(Color.RED);
			g.fillRoundRect(355, 625, 35, 35, 15, 15);
			g.setColor(Color.WHITE);
			g.fillRoundRect(360, 630, 10, 25, 0, 0);
			g.fillRoundRect(375, 630, 10, 25, 0, 0);			
			
			//// energy && health ///
			g.setColor(Color.GRAY);
			g.fillRoundRect(10, 20, 100, 20, 20, 20);
			g.setColor(Color.GREEN);
			g.fillRoundRect(10, 20, energy, 20, 20, 20);
			g.setColor(Color.YELLOW);
			g.drawRoundRect(10, 20, 100, 20, 20, 20);
			
			// // Athletes movements ///////
			
			if (athlete.getKeyReturn() == KeyEvent.VK_UP
					|| athlete.getKeyReturn() == KeyEvent.VK_DOWN) {
				sprite.drawFrame(man, g2d, athlete.getX(),
						athlete.getY(), 2, currentFrame, 64, 64); 
														
			} else if (athlete.getKeyReturn() == KeyEvent.VK_LEFT) {
				sprite.drawFrame(manLeft, g2d, athlete.getX(),
						athlete.getY(), 2, currentFrame, 64, 64);
			} else if (athlete.getKeyReturn() == KeyEvent.VK_RIGHT) {
				sprite.drawFrame(manRight, g2d, athlete.getX(),
						athlete.getY(), 2, currentFrame, 64, 64);
			} else
				sprite.drawFrame(man, g2d, athlete.getX(),
						athlete.getY(), 2, currentFrame, 64, 64);
			///  words on the menu buttin depeding on the state of play
			if (state != STATE.MENU)
				g.drawString(menuString, 354, 27);
			if (state == STATE.MENU)
				g.drawString(playString, 358, 27);
			
			/// collision detection between player and rocks //
			
			Rectangle2D r2dPlayer = new Rectangle2D.Double(athlete.getX() + 8, athlete.getY(), 48, 64);
			g.setColor(Color.RED);
			g.drawRoundRect(athlete.getX() + 8, athlete.getY(), 48, 64, 35, 35);

			Rectangle2D r2dRox = new Rectangle2D.Double(board.getRoxX() + 14, board.getRoxY(), 45, 55);
			g.drawRoundRect((int) board.getRoxX() + 14, (int) board.getRoxY(), 45, 55, 35, 35);

			// // ROCKS FALLING ///
			g.drawImage(board.getRox(), (int) board.getRoxX(),(int) board.getRoxY(), 80, 80, null);

			frameCount++;
			if (frameCount > frameDelay) {
				frameCount = 0;
				// update the animation frame
				currentFrame += direction;
				if (currentFrame > climbFrames - 1) {
					currentFrame = 0;
				} else if (currentFrame < 0) {
					currentFrame = climbFrames - 1;
				}
			}else if (state == STATE.PAUSE) {
				
				g.setColor(Color.BLACK);
				g.fillRoundRect(355, 625, 35, 35, 15, 15);
				g.setColor(Color.WHITE);
				g.fillRoundRect(360, 630, 10, 25, 0, 0);
				g.fillRoundRect(375, 630, 10, 25, 0, 0);
		}
// /// RAIN FALLING --- LEVEL DIFFICULTY ////

// // energy loss from rock collision ///////
			if (r2dRox.intersects(r2dPlayer)) {
				energy--;
				if (energy == 0) {
					new Athlete();
					new GameBoard();
					new Climber();
					this.energy = 100;
					state = STATE.END;
				}
			}

			if (board.roxY >= 700) {
				board.roxX = ran.nextInt(350);
				board.roxY = -50 - ran.nextInt(250);
			}
		
// / WHEN THE GAME IS OVER AND PLAYER HAS LOST ////////
			if (state == STATE.END) {
				g.drawImage(board.getLoss(), 0, 0, null);
			}
			/// player frame movement from spritesheet

				g.setColor(Color.RED);
				Rectangle2D redRec = new Rectangle2D.Double(birdXX - 300,
						birdYY - 60, 150, 200);// collision
				Rectangle2D redBird = new Rectangle2D.Double(birdX, birdY + 20,
						64, 64);// collision
				g.drawRect(birdX - 40, birdY - 60, 150, 200);// visual
				g.drawRect(birdX, birdY + 20, 64, 40);// visual
		}
	}
	public class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			athlete.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			athlete.keyPressed(e);
		}
	}
}