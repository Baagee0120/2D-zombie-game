package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener{
	GamePanel gp ;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public Keyhandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(gp.gameState == gp.overState) {
		
			if(code == KeyEvent.VK_ENTER) {
				switch(gp.ui.commandNum) {
				
				case 0:
					System.exit(0);
					break;
			}
		  }
		}
		if(gp.gameState == gp.titleState) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum = 0;
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum = 1;
			}
			if(code == KeyEvent.VK_ENTER) {
				switch(gp.ui.commandNum) {
				case 0:
					gp.gameState = gp.playState;
				break;
				case 1:
					System.exit(0);
					break;
			}
		  }
		}
		
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}

	

	}


