package com.mysnakegame;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {

    public enum MovementDirection {
        UP, RIGHT, DOWN, LEFT
    }

    private final static int initialLength = 25;
    private ArrayList<SnakeSegment> segments;    
    private MovementDirection md;

    public Snake(int initialX, int initialY) {
        md = MovementDirection.RIGHT;
        segments = new ArrayList<SnakeSegment>();
        for (int i = 0; i < initialLength; i++) {
            if (getSize() == 0) {
                addSegment(initialX, initialY);
            } else {
                addSegment(getHeadPosition().x + SnakeSegment.size, getHeadPosition().y);
            }
        }
    }

    public int getSize() {
        return segments.size();
    }

    public ArrayList<SnakeSegment> getSegments() {
        return segments;
    }
    public SnakeSegment getSegment(int idx) {
        return segments.get(idx);
    }

    public void addSegment(int x, int y) {
        for (int i = 0; i < getSize(); i++) {
            if (segments.get(i).getColor().equals(Color.GREEN)) {
                segments.get(i).setColor(Color.YELLOW);
            } else {
                segments.get(i).setColor(Color.GREEN);
            }
        }
        segments.add(new SnakeSegment(x, y, Color.GREEN));
    }

    public Point getHeadPosition() {
        return new Point(segments.get(getSize() - 1).getX(), segments.get(getSize() - 1).getY());
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
        for (int i = 0; i < initialLength; i++) {
            if (getSize() == 0) {
                addSegment(x, y);
            } else {
                addSegment(getHeadPosition().x, getHeadPosition().y);
            }
        }
    }

}
