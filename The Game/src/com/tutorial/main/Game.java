package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
	
	private static final long serialVersionUID = -5036919599729261540L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;

	private Canvas canvas;
	private Window window;

	private BufferStrategy bs;

	private Graphics g;
	
	public Game(){
		window = new Window(WIDTH, HEIGHT, "The Game");
		handler = new Handler(); 
		start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		running = true;
		thread.start();

	}
	
	public synchronized void stop() {
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
		stop();
	}
	
	private void tick () {
		handler.tick();
	}

	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null){
			if(window.getCanvas()==null){
				return;
			}
			window.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		
		//clear screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 1, 200, 200);
		g.drawImage(Assets.car,150,150,64,64,null);
		
		//End drawing
		bs.show();
		g.dispose();

	}
	public static void main(String args[]) {
		Game g = new Game();
	}
	
}
