package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

public class UI {
	Graphics2D g2;
	GamePanel gp;
	Font arial_40;
	BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;
	public int commandNum = 0;
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
		
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		if(gp.gameState == gp.playState) {
		drawPlayerLife();
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x" + gp.player.hasKey, 74, 65);
		}
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if(gp.gameState == gp.overState) {
			drawOverScreen();
		}
		
	}
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize*2;
		int i = 0;
		// max life
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			x+=gp.tileSize;
		}
		//reset
		x = gp.tileSize/2;
		y = gp.tileSize*2;
		i = 0;
		// draw current life
		while(i< gp.player.life) {
			g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			if(i<gp.player.life) {
				g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, null);
			}
			i++;
			x+= gp.tileSize;
		}
	}
	public void drawTitleScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		
		//title name
		String text = "ZOMDIE";
		int x = xCentered(text);
		int y = gp.tileSize*3;
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		//player image
		x = gp.screenWidth/2 - gp.tileSize;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		text = "START";
		x = xCentered(text);
		y+=gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum ==0)
		g2.drawString(">", x-gp.tileSize, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		text = "QUIT";
		x = xCentered(text);
		y+=gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1)
		g2.drawString(">", x-gp.tileSize, y);
	}
	public void drawOverScreen() {
	g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		
		//title name
		String text = "GAME OVER";
		int x = xCentered(text);
		int y = gp.tileSize*3;
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		//player image
		x = gp.screenWidth/2 - gp.tileSize;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		text = "QUIT";
		x = xCentered(text);
		y+=gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0)
		g2.drawString(">", x-gp.tileSize, y);
	}
	public int xCentered(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
