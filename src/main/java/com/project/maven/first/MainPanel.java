package com.project.maven.first;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
		
	private final static int WIDTH = 800;
	private final static int HEIGHT = 800;
	
	public MainPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));		
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);		
	}
}
