package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

	private int x,y;
	private float drag;
	private Rectangle hitbox;
	private BufferedImage[] runAnimation;
	private BufferedImage[] jumpAnimation;
	private KeyManager k;
	private Game game;
	
	public Player(int x, int y,KeyManager k,Game game){
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x,y,16,32);
		this.k=k;
		this.game = game;
		runAnimation = new BufferedImage[4];
		jumpAnimation = new BufferedImage[4];
		
		runAnimation[0] = Assets.run1;
		runAnimation[1] = Assets.run2;
		runAnimation[2] = Assets.run3;
		runAnimation[3] = Assets.run4;
	}
	
	
	public void tick(){
		if(k.left){
			x--;
		} else if(k.right){
			x++;
		}
		
		if(k.up){
			y++;
		} else if(k.down){
			y--;
		}
		
		
		hitbox.setLocation(new Point(x, y));
	}
	
	public void render(Graphics g){
		if(game.getFrames()<=15){
			g.drawImage(Assets.run1, x,y,32,64,null);
		} else if(game.getFrames()<=30&&game.getFrames()>15){
			g.drawImage(Assets.run2, x,y,32,64,null);
		} else if(game.getFrames()<=45&&game.getFrames()>30){
			g.drawImage(Assets.run3, x,y,32,64,null);
		} else if(game.getFrames()<=60&&game.getFrames()>45){
			g.drawImage(Assets.run4, x,y,32,64,null);
		}
	}
	
}
