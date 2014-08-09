package com.project.maven.first;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	GeneralPath gp = new GeneralPath();
	private int WIDTH;
	private int HEIGHT;
	
	public MainPanel(){
		setLayout(new BorderLayout());		
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		HEIGHT = getHeight();
		this.WIDTH = this.getWidth();
		gp.moveTo(WIDTH / 2,HEIGHT / 2);
		gp.lineTo(WIDTH / 2 + 50 ,HEIGHT / 2 + 50);
		gp.lineTo(WIDTH / 2 + 75 ,HEIGHT / 2 + 100);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(8.0f));
		g2d.setPaint(Color.BLUE);
		g2d.draw(gp);
	}
}
