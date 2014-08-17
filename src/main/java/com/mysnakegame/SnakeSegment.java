package com.mysnakegame;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeSegment {

    public final static int size = 10;
    private int posX, posY;
    private Color color;
    
    public SnakeSegment(int posX, int posY, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(posX, posY, size, size);
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

}
