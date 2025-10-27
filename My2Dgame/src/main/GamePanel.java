package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Entity;
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


	Keyhandler KeyH = new Keyhandler(this); 
	Sound music = new Sound();
	Sound se = new Sound();
	public Player player = new Player(this, KeyH); 
	public SuperObject obj[] = new SuperObject[10]; 
	public AssetSetter Setter = new AssetSetter(this); 
	public CollisionChecker cChecker = new CollisionChecker(this); 
	public UI ui = new UI(this);
	Thread gameThread; 
	ArrayList<Entity> entityList = new ArrayList<>();
	public Entity[] monster = new Entity[20];


	// game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int overState = 2;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenLength));
		this.setDoubleBuffered(true);
		this.setBackground(Color.black);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		Setter.setObject();
		Setter.setMonster();
		playMusic(0);
		gameState = titleState;
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
	        try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }


	       
	    }
	}

			
		
		
		
	
	public void update() {
		if(gameState == playState) {
		player.update();
		}
		for(int i = 0; i < monster.length; i++) {
			if(monster[i]!= null) {
				monster[i].update();
			}
		}
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
	if (gameState == titleState){
	
		ui.draw(g2);
	}
	else if(gameState == overState){
		ui.draw(g2);
	}
	else {
		tileM.draw(g2);

		// Draw objects
		for (SuperObject o : obj) if (o != null) o.draw(g2, this);

		// Draw monsters
		for (Entity m : monster) if (m != null) m.draw(g2);

		// Draw player last
		player.draw(g2);

		//UI
		ui.draw(g2);
		
		g2.dispose();
	}
}
	
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	public void stopMusic() {
		music.stop();
		
	}
	public void playSE(int i){
		se.setFile(i);
		se.play();
	}
	
}
