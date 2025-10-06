package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

	final int originalTilesize = 16;
	final int scale = 3;
	public final int tileSize = scale * originalTilesize;
	public final int maxScreenRow = 12;
	public final int maxScreenCol = 16;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenLength = tileSize* maxScreenRow;
	
	// FPS
	int fps = 60;
	
	// world settings
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	TileManager tileM = new TileManager(this);
	
	Thread gameThread;

	
	Keyhandler KeyH = new Keyhandler();
	public Player player = new Player(this, KeyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenLength));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	@Override
	public void run() {
	    double drawInterval = 1000000000.0 / fps; // time per frame in nanoseconds
	    double delta = 0;
	    long lastTime = System.nanoTime();
	    long timer = 0;
	    int drawCount = 0;

	    while (gameThread != null) {
	        long currentTime = System.nanoTime();

	        delta += (currentTime - lastTime) / drawInterval;
	        timer += (currentTime - lastTime);
	        lastTime = currentTime;

	        if (delta >= 1) {
	            update();
	            repaint();
	            delta--;
	            drawCount++;
	        }

	        // Print FPS every second
	        if (timer >= 1000000000) {
	            System.out.println("FPS: " + drawCount);
	            drawCount = 0;
	            timer = 0;
	        }
	    }
	}

			
		
		
		
	
	public void update() {
		player.update();
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}
