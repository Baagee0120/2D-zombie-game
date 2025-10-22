package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import entity.Player;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

	final int originalTilesize = 16;
	final int scale = 3;
	public final int tileSize = scale * originalTilesize;
	public final int maxScreenRow = 16;
	public final int maxScreenCol = 12;
	public final int screenWidth = tileSize * maxScreenRow;
	public final int screenLength = tileSize* maxScreenCol;
	
	// FPS
	int fps = 60;
	
	// world settings
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldRow;
	public final int worldHeight = tileSize * maxWorldCol;
	
	
	
	TileManager tileM = new TileManager(this);
	
	Thread gameThread;

	public CollisionChecker cChecker = new CollisionChecker(this);
	Keyhandler KeyH = new Keyhandler();
	public Player player = new Player(this, KeyH);
	public SuperObject obj[] = new SuperObject[10]; 
	public AssetSetter Setter = new AssetSetter(this); //
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenLength));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		Setter.setObject();
		
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
	  
	 

	    while (gameThread != null) {
	        long currentTime = System.nanoTime();

	        delta += (currentTime - lastTime) / drawInterval;
	     
	        lastTime = currentTime;

	        if (delta >= 1) {
	            update();
	            repaint();
	            delta--;
	           
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
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		
		player.draw(g2);
		
		g2.dispose();
	}
}
