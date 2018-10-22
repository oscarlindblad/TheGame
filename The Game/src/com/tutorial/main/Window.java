package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6075190148075814446L;

	public Window(int width, int hight, String title, Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, hight));
		frame.setMaximumSize(new Dimension(width, hight));
		frame.setMinimumSize(new Dimension(width, hight));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		Canvas canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,hight));
		canvas.setMaximumSize(new Dimension(width, hight));
		canvas.setMinimumSize(new Dimension(width, hight));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.validate();
		frame.repaint();
		frame.pack();
		game.setCanvas(canvas);
		game.start();
	}
	
	
	
}