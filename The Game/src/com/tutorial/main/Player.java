package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class Player {

	private int x,y,width,height;
	private float gravity,velocity;
	private Rectangle hitbox;


	private KeyManager k;
	private Game game;
	private boolean gameOver;
	
	public Player(int x, int y,KeyManager k,Game game){
		this.width = game.getRect().width/12;
		this.height = game.getRect().height/12;
//		width*=2;
//		height*=2;
		this.x = x-width/2;
		this.y = y-height/2;
		
		hitbox = new Rectangle(this.x,this.y,width,height);
		this.k=k;
		gravity = 0.9f;
		gameOver = false;
		this.game = game;
	}
	
	
	public void tick(){
		if(!gameOver){
			if(k.space&&(velocity<1)){
				velocity=10;
			} else if(velocity<1){
				velocity=-10;
			}
			if(game.getRect().contains(hitbox)){
				y -= velocity;
				velocity *= gravity;
				hitbox.setLocation(new Point(x, y));
			} else {
				gameOver = true;
				game.setGameOver();
			}
			for(Rectangle temp : game.getWorld().getBlocks()){
				if(overlaps(temp)){
					gameOver = true;
					game.setGameOver();
				}
			}
			
			for(Rectangle temp : game.getWorld().getPassedBlocks()){
				if(overlaps(temp)){
					gameOver = true;
					game.setGameOver();
				}
			}
			
		}
	}
	
	private boolean overlaps (Rectangle r) {
	    return hitbox.x < r.x + r.width && hitbox.x + hitbox.width > r.x && hitbox.y < r.y + Math.abs(r.height) && hitbox.y + Math.abs(hitbox.height) > r.y;
	}
	
	public void render(Graphics g){
		g.drawImage(Assets.oscar, x, y,width,height ,null);
	}
	
}
