package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window{

	private Canvas canvas;
	private JFrame frame;
	private int width,height;
	
	private static final long serialVersionUID = 6075190148075814446L;

	public Window(int width, int height, String title){
		this.width = width;
		this.height = height;
		
		frame = new JFrame(title);
		
		frame.setSize(width,height);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		


		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
	
		
		frame.add(canvas);
		frame.pack();

	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	
}