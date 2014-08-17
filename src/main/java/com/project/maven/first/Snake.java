package com.project.maven.first;

import java.awt.Color;
import java.awt.Point;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

public class Snake {

	public enum MovementDirection {
		UP, RIGHT, DOWN, LEFT
	}
	
	private final static int initialLength = 15;
	private ArrayList<SnakeSegment> segments;		
	private MovementDirection md;
	
	public Snake(int initialX, int initialY) {
		md = MovementDirection.RIGHT;
		segments = new ArrayList<SnakeSegment>();				
		for(int i = 0; i < initialLength; i++) {
			if(getSize() == 0) {
				addSegment(initialX, initialY);
			} else {
				addSegment(getHeadPosition().x, getHeadPosition().y);
			}			
		}		
	}
	
	public int getSize() {
		return segments.size();
	}
	
	public ArrayList<SnakeSegment> getSegments() {
		return segments;
	}
	
	public void addSegment(int x, int y) {
		if(getSize() == 0){
			segments.add(new SnakeSegment(x, y, Color.BLUE));
		} else if(segments.get(getSize() - 1).getColor().equals(Color.BLUE)){
			segments.add(new SnakeSegment(x, y, Color.CYAN));
		} else {
			segments.add(new SnakeSegment(x, y, Color.BLUE));
		}		
	}
	
	public Point getHeadPosition() {
		return new Point(segments.get(getSize() - 1).getX() + SnakeSegment.size, segments.get(getSize() - 1).getY());
	}
	
	public void removeLast() {
		segments.remove(0);
	}
	
	public MovementDirection getMovementDirection() {
		return md;
	}
	
	public void setMovementDirection(MovementDirection md) {
		this.md = md;
	}
	
	public void moveToPoint(int x, int y) {
		segments.clear();		
		for(int i = 0; i < initialLength; i++) {
			if(getSize() == 0) {
				addSegment(x, y);
			} else {
				addSegment(getHeadPosition().x, getHeadPosition().y);
			}			
		}		
	}
	
}
