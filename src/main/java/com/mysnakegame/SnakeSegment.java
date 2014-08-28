package com.mysnakegame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.mysnakegame.utils.Utils;

public class SnakeSegment {

    public static final BufferedImage IMAGE1 = Utils.createBufferedImage("/skin_ver1_1_16x16.png");
    public static final BufferedImage IMAGE2 = Utils.createBufferedImage("/skin_ver1_2_16x16.png");

    public final static int SEGMENT_SIZE = 16;
    private int posX, posY;
    // private Color color;

    private BufferedImage image;

    public SnakeSegment(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        // this.color = color;
        image = IMAGE1;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D r = new Rectangle2D.Double(posX, posY, SEGMENT_SIZE, SEGMENT_SIZE);
        // Rectangle2D tr = new Rectangle2D.Double(posX, posY, size, size);
        TexturePaint tp = new TexturePaint(image, r);
        g2.setPaint(tp);
        g2.fill(r);
        // g.setColor(color);
        // g.fillRect(posX, posY, size, size);
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public Point getLocation() {
        return new Point(posX, posY);
    }

    /*
     * public Color getColor() { return color; }
     */

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }

    /*
     * public void setColor(Color color) { this.color = color; }
     */

    public void setFill(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getFill() {
        return image;
    }

}
