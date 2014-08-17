package com.project.maven.first;

import java.awt.Color;
import java.awt.Graphics;

public class Segment {	
	
	public final static int size = 10;
	private int x, y;
	private Color color;
	
	public Segment(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void draw(Graphics g) {		
		g.setColor(color);		
		g.fillRect(x, y, size, size);		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
