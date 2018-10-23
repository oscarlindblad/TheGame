package com.tutorial.main;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 16, height = 16, biggerwidth =64, biggerheight = 64,nWidth=16,nHeight=32;
	
	
	public static BufferedImage car,idle,run1,run2,run3,run4,oscar,gameOver;
								
	public static void init(){
		
		SpriteSheet cars = new SpriteSheet(ImageLoader.loadimage("textures/cars.png"));
		SpriteSheet character = new SpriteSheet(ImageLoader.loadimage("textures/test.png"));
		SpriteSheet sprite = new SpriteSheet(ImageLoader.loadimage("textures/oscar.png"));
		SpriteSheet gameO = new SpriteSheet(ImageLoader.loadimage("textures/game.jpg"));
		car = cars.crop(0, 0, width, height);
		idle = character.crop(0, 0, nWidth, nHeight);
		run1 = character.crop(nWidth, 0, nWidth, nHeight);
		run2 = character.crop(nWidth*2, 0, nWidth, nHeight);
		run3 = character.crop(nWidth*3, 0, nWidth, nHeight);
		run4 = character.crop(nWidth*4, 0, nWidth, nHeight);
		gameOver = gameO.crop(0, 0, 260, 260);
		oscar = sprite.crop(0,0,102,141);
	}
	
}
