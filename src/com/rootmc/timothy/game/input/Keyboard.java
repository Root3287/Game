package com.rootmc.timothy.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean[150];
	public boolean up, down, left, right, exit;
	
	public void update(){
		up = keys[KeyEvent.VK_W];
		down=keys[KeyEvent.VK_S];
		left=keys[KeyEvent.VK_A];
		right=keys[KeyEvent.VK_D];
		exit = keys[KeyEvent.VK_F12];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]= true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]= false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
