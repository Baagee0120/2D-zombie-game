package main;

import monster.MON_Ogre;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	

	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 8 * gp.tileSize;
		gp.obj[1].worldY = 38 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door();
		gp.obj[2].worldX = 23 * gp.tileSize;
		gp.obj[2].worldY = 14 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Chest();
		gp.obj[3].worldX = 38 * gp.tileSize;
		gp.obj[3].worldY = 15 * gp.tileSize;
		
		
		gp.obj[4] = new OBJ_Boots();
		gp.obj[4].worldX = 22 * gp.tileSize;
		gp.obj[4].worldY = 25 * gp.tileSize;
		
		
	}
	public void setMonster() {
		gp.monster[0]= new MON_Ogre(gp);
		gp.monster[0].worldX = 27 *gp.tileSize;
		gp.monster[0].worldY = 10 *gp.tileSize;
		gp.monster[1]= new MON_Ogre(gp);
		gp.monster[1].worldX = 23 *gp.tileSize;
		gp.monster[1].worldY = 30 *gp.tileSize;
		gp.monster[2]= new MON_Ogre(gp);
		gp.monster[2].worldX = 8 *gp.tileSize;
		gp.monster[2].worldY = 38 *gp.tileSize;
	
	}
	
}

