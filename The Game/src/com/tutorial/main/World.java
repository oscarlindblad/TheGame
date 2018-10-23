package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class World {

	private int  height,width,divider,speed;
	private ArrayList<Rectangle> blocks;
	private ArrayList<Rectangle> filledBlocks,passedBlocks;
	private Stack<Rectangle> removeQueue,removeFill,removePass;
	private Game game;
	private float points;
	
	public World(int width,int height,Game game){
		divider = 12;
		this.width = width;
		this.height = height;
		removeQueue = new Stack<Rectangle>();
		removeFill = new Stack<Rectangle>();
		removePass = new Stack<Rectangle>();
		points = 0;
		filledBlocks = new ArrayList<Rectangle>();
		passedBlocks = new ArrayList<Rectangle>();
		blocks = new ArrayList<Rectangle>();
		speed=4;
		this.game = game;
		
		nextBlock();
		/*
		for(int x=0; x<width+(2*(width/10)); x+=(width/divider)){
			for(int y=0; y<height; y+=(height/divider)){
				blocks.add(new Rectangle(x,y,width/divider,height/divider));
			}
		}*/
	}
	
	public float getPoints(){
		return points;
	}
	
	public ArrayList<Rectangle> getBlocks(){
		return filledBlocks;
	}
	
	private void nextBlock(){
		Random random = new Random();
		filledBlocks.add(new Rectangle(width,height,width/divider,-(random.nextInt(4 - 2 + 2)+2)*height/divider));
		filledBlocks.add(new Rectangle(width,0,width/divider,(random.nextInt(4 - 2 + 2)+2)*height/divider));
	}
	
	public void tick(){
		if(game.getGameOver()){
			return;
		}
		int lowestX=-(width/divider);

/*		for(Rectangle temp:blocks){
			
			if(temp.x<lowestX){
				removeQueue.push(temp);
			}
			temp.setLocation((int)temp.getX()-2,temp.y);
			
		}
	*/	
		
		int lowerX=width/2;
		
		for(Rectangle temp:filledBlocks){
			if(temp.x<lowerX){
				removeFill.push(temp);
			}

			temp.setLocation((int)temp.getX()-speed,temp.y);
		}
		boolean nextBlock=false;
		while(!removeFill.isEmpty()){
			Rectangle temp = removeFill.pop();
			filledBlocks.remove(temp);
			passedBlocks.add(temp);
			nextBlock = true;
			points+=0.5;
		}
		
		if(nextBlock){
			nextBlock();
		}
		
		for(Rectangle temp:passedBlocks){
			if(temp.x<lowestX){
				removePass.add(temp);
			}
			temp.setLocation((int)temp.getX()-speed,temp.y);
		}
		while(!removePass.isEmpty()){
			passedBlocks.remove(removePass.pop());
			
		}
		int y=0;
		/*
		while(!removeQueue.isEmpty()){
			blocks.remove(removeQueue.pop());
			blocks.add(new Rectangle(width+((width/divider)),y,width/divider,height/divider));
			y+=(height/divider);
		}
		*/
	}
	
	public void render(Graphics g){
		/*
		for(Rectangle temp:blocks){
			g.setColor(Color.black);
			g.drawRect(temp.x, temp.y, temp.width, temp.height);
		}*/
		for(Rectangle temp:filledBlocks){
			g.setColor(Color.black);
			g.fillRect(temp.x, temp.y, temp.width, temp.height);
		}
		for(Rectangle temp:passedBlocks){
			g.setColor(Color.black);
			g.fillRect(temp.x, temp.y, temp.width, temp.height);
		}

	}
	
	
}
