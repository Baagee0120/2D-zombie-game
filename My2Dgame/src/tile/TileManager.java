package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		LoadMap("/maps/map01.txt");
		
	}

	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void LoadMap(String filePath) {
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int col = 0;

	        while (col < gp.maxWorldCol) {
	            String line = br.readLine();
	            String numbers[] = line.split(" ");

	            for (int row = 0; row < gp.maxWorldRow; row++) {
	                int num = Integer.parseInt(numbers[row]);
	                mapTileNum[col][row] = num;
	            }
	            col++;
	        }

	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	
	public void draw(Graphics2D g2) {
		
		
		
		
	    for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
	        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
	        	
	            int tileNum = mapTileNum[worldCol][worldRow];
	            
	            int x = worldRow * gp.tileSize;
	            int y = worldCol * gp.tileSize;
	            int screenX = x - gp.player.worldX + gp.player.screenX;
	            int screenY = y - gp.player.worldY + gp.player.screenY;
	            if(x + gp.tileSize > gp.player.worldX - gp.player.screenX && x - gp.tileSize < gp.player.worldX +gp.player.screenX &&
	               y + gp.tileSize > gp.player.worldY - gp.player.screenY && y - gp.tileSize< gp.player.worldY + gp.player.screenY) {
	            	 g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	            	 
	            }
	         
	       
	            		
	           
	        }
	    }
	    
	
	}


}
