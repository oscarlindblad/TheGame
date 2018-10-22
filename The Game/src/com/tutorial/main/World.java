package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class World {

	private int length, height,windowWidth,widthLeft;
	private Rectangle[] blocks;
	private Rectangle[] filledBlocks;
	
	public World(int length,int height,int gameLength){
		this.windowWidth = gameLength;
		this.length = length*64;
		this.height = height*128;
		
		blocks = new Rectangle[length*height-(length*height)/8];
		filledBlocks = new Rectangle[(length*height)/8];
		
		widthLeft = this.length;
		System.out.println(widthLeft);
		int counter=0,index=0,fillIndex=0;
		
		for(int x=0; x<length; x++){
			for(int y=0; y<height; y++){
		
				if(counter%8==0){
					filledBlocks[fillIndex] = new Rectangle(x*64,y*128,64,128);
					fillIndex++;
				} else {
					blocks[index] = new Rectangle(x*64,y*128,64,128);
					index++;
				}
				counter++;
			}
		}
	}
	
	public void tick(){
		System.out.println(widthLeft +" "+windowWidth);
		if(widthLeft>windowWidth){
			for(Rectangle temp:blocks){
				temp.setLocation((int)temp.getX()-2,temp.y);
			}
			for(Rectangle temp:filledBlocks){
				temp.setLocation((int)temp.getX()-2,temp.y);
			}
			widthLeft -= 2;
		}
	}
	
	public void render(Graphics g){
		for(Rectangle temp:blocks){
			g.setColor(Color.black);
			g.drawRect(temp.x, temp.y, temp.width, temp.height);
		}
		for(Rectangle temp:filledBlocks){
			g.setColor(Color.black);
			g.fillRect(temp.x, temp.y, temp.width, temp.height);
		}
	}
	
	
}
