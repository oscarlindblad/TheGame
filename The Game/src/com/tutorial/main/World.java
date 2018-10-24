package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class World {

	private int  height,width,divider,speed;
	private ArrayList<Rectangle> filledBlocks,passedBlocks;
	private Stack<Rectangle> removeFill,removePass;
	private Game game;
	private float points;
	
	public World(int width,int height,Game game){
		divider = 12;
		this.width = width;
		this.height = height;
		removeFill = new Stack<Rectangle>();
		removePass = new Stack<Rectangle>();
		points = 0;
		filledBlocks = new ArrayList<Rectangle>();
		passedBlocks = new ArrayList<Rectangle>();

		speed = 4;
		this.game = game;
		
		nextBlock();

	}
	
	public float getPoints(){
		return points;
	}
	
	public ArrayList<Rectangle> getBlocks(){
		return filledBlocks;
	}
	
	public ArrayList<Rectangle> getPassedBlocks(){
		return passedBlocks;
	}
	
	private void nextBlock(){
		Random random = new Random();
		int r = (random.nextInt(3 - 2 + 2)+2);
		filledBlocks.add(new Rectangle(width,height-(height/divider)*r,width/divider,r*height/divider));
		filledBlocks.add(new Rectangle(width,0,width/divider,(random.nextInt(3 - 2 + 2)+2)*height/divider));
	}
	
	public void tick(){
		if(game.getGameOver()){
			return;
		}
		int lowestX=-(width/divider);
		
		int lowerX=width/2;
		
		for(Rectangle temp:passedBlocks){
			if(temp.x<lowestX){
				removePass.add(temp);
			}
			temp.setLocation((int)temp.getX()-speed,temp.y);
		}
		while(!removePass.isEmpty()){
			passedBlocks.remove(removePass.pop());
			
		}
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

		

	}
	
	public void render(Graphics g){

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
