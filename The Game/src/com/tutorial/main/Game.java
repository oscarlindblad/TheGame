package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;



public class Game implements Runnable{
	
	private static final long serialVersionUID = -5036919599729261540L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private KeyManager keyManager;
	private Canvas canvas;
	private Window window;
	private BufferStrategy bs;
	private int frames,lastFrames,tempy=100,tempx=100;//REMOVE THIS LATER
	private Graphics g;
	private Player p;
	private World w;
	private Rectangle windowRect;

	private boolean gameOver;
	
	public Game(){
		window = new Window(WIDTH, HEIGHT, "The Game");
		windowRect = new Rectangle(0,0,WIDTH,HEIGHT);
		handler = new Handler(); 
		Assets.init();
		keyManager = new KeyManager();
		window.getFrame().addKeyListener(keyManager);
		start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		running = true;
		p = new Player(WIDTH/2,HEIGHT/2,keyManager,this);
		w = new World(WIDTH,HEIGHT,this);
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
		frames = 0;
		
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
				lastFrames=frames;
				frames = 0;
			}
		}
		stop();
	}
	
	private void startOver(){
		p = new Player(WIDTH/2,HEIGHT/2,keyManager,this);
		w = new World(WIDTH,HEIGHT,this);
		gameOver=false;
	}
	
	private void tick () {
		if(gameOver&&keyManager.space){
			startOver();
		}
		handler.tick();
		keyManager.tick();
		p.tick();
		w.tick();
		
	}
	
	public World getWorld(){
		return w;
	}
	
	public boolean isWithinBox(Rectangle rect, int x, int y){
		return rect.contains(new Point(x,y));
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
		w.render(g);
		p.render(g);
		g.setColor(Color.GREEN);
		g.drawString("FPS: "+lastFrames, 0, 20);
		g.drawString("Points: "+w.getPoints(), 0, 40);
		if(gameOver){
			g.setColor(Color.RED);
			g.setFont(new Font("Helvetica",26,26));
			g.drawString("GAME OVER", WIDTH/2-80, HEIGHT/2-13);
			g.drawString("(Press space to restart)", WIDTH/2-120, HEIGHT/2-13+30);
		}
		//End drawing
		bs.show();
		g.dispose();

	}
	
	public int getFrames(){
		return frames;
	}
	
	public Rectangle getRect(){
		return windowRect;
	}
	
	public boolean getGameOver(){
		return gameOver;
	}
	
	public void setGameOver(){
		gameOver=!gameOver;
	}
	
	public static void main(String args[]) {
		Game g = new Game();
	}
	
}
