package monster;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class MON_Ogre extends Entity{

	public MON_Ogre(GamePanel gp) {
		super(gp);
		
		
		name = "ogre";
		speed = 2;
		maxLife = 4;
		life = maxLife;
		solidArea.x = 8;
		solidArea.y = 10;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}
	public void getImage() {
		try {
		up1 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_up_1.png"));
		up2 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_up_2.png"));
		down1 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_down_1.png"));
		down2 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_down_2.png"));
		left1 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_left_1.png"));
		left2 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_left_2.png"));
		right1 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_right_1.png"));
		right2 = ImageIO.read(getClass().getResourceAsStream("/monster/orc_right_2.png"));
		
	}	catch(IOException e) {
		e.printStackTrace();
		}
	}
	public void setAction() {
	    // Player position
	    int playerX = gp.player.worldX;
	    int playerY = gp.player.worldY;

	    // Convert enemy position to screen coordinates
	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

	    // Check if enemy is within screen bounds
	    if (screenX + solidArea.width > 0 && screenX < gp.screenWidth &&
	        screenY + solidArea.height > 0 && screenY < gp.screenLength) {

	        // Chase player in both X and Y directions
	        if (worldX > playerX) {
	            direction = "left";
	            System.out.println("moving left");
	        } else if (worldX < playerX) {
	            direction = "right";
	            System.out.println("moving right");
	        } else if (worldY > playerY) {
	            direction = "up";
	            System.out.println("moving up");
	        } else if (worldY < playerY) {
	            direction = "down";
	            System.out.println("moving down");
	        }
	    } else {
	     
	        direction = "";
	    }
	}



}


