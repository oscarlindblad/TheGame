package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5036919599729261540L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;

	private Canvas canvas;

	
	public Game(){
		new Window(WIDTH, HEIGHT, "The Game", this);
		
		handler = new Handler(); 
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stpo() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				render();
				delta--;
				frames++;
			}

			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("fps: " + frames);
				frames = 0;
			}
		}
		stpo();
	}
	
	private void tick () {
		handler.tick();
	}
	
	public void setCanvas(Canvas canvas){
		this.canvas = canvas;
	}
	
	private void render() {
		
		canvas.createBufferStrategy(3);
		BufferStrategy bs = canvas.getBufferStrategy();

		
		Graphics g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(Assets.car,150,150,64,64,null);
		
		
		handler.render(g);
		
		bs.show();
		g.dispose();

	}
	public static void main(String args[]) {
		new Game();
	}
	
}
