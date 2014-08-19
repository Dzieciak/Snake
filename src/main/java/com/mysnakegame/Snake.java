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

    public Snake() {
        md = MovementDirection.RIGHT;
        segments = new ArrayList<SnakeSegment>();
        for (int i = initialLength; i > 0; i--) {
            addSegment(MainPanel.WIDTH / 2 - i * SnakeSegment.size, MainPanel.HEIGHT / 2);
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
        segments.add(0, new SnakeSegment(x, y, Color.GREEN));
        segments.get(0).setColor(Color.BLACK);
    }

    public Point getHeadPosition() {
        return new Point(segments.get(0).getX(), segments.get(0).getY());
    }

    public void removeLast() {
        segments.remove(getSize() - 1);
    }

    public MovementDirection getMovementDirection() {
        return md;
    }

    public void setMovementDirection(MovementDirection md) {
        this.md = md;
    }

    public void reset() {
        segments.clear();
        md = MovementDirection.RIGHT;
        for (int i = initialLength; i > 0; i--) {
            addSegment(MainPanel.WIDTH / 2 - i * SnakeSegment.size, MainPanel.HEIGHT / 2);
        }
    }

}
