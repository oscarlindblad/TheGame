package com.tutorial.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, enter, space,w,s,a,d,esc,ready;

	
	public KeyManager(){
		
		keys = new boolean[525];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
		space = keys[KeyEvent.VK_SPACE];
		w = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		a = keys[KeyEvent.VK_A];
		d = keys[KeyEvent.VK_D];
		esc = keys[KeyEvent.VK_ESCAPE];
		
	}
	
	public void keyPressed(KeyEvent e) {
		ready = true;
		keys[e.getKeyCode()] = true;
		System.out.println("Key pressed: "+ e.getKeyChar() +" do stuff at the screen and call it a game now");
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void resetBolean(){
		ready = false;
	}
	
	public boolean getReady(){
		return ready;
	}
	
}
