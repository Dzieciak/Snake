package com.mysnakegame.fruits;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.mysnakegame.SnakeSegment;
import com.mysnakegame.utils.Utils;

public enum Fruits {
    APPLE(Utils.createBufferedImage("/apple_16x16.png"), 10), BANANA(Utils.createBufferedImage("/banana_16x16.png"), 5), ORANGE(Utils
            .createBufferedImage("/orange_16x16.png"), 7), PEAR(Utils.createBufferedImage("/pear_16x16.png"), 3), STRAWBERRY(Utils
            .createBufferedImage("/strawberry_16x16.png"), 1);

    private final BufferedImage image;
    private final int pointValue;

    Fruits(BufferedImage image, int pointValue) {
        this.image = image;
        this.pointValue = pointValue;
    }

    public void draw(Graphics g, int posX, int posY) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D r = new Rectangle2D.Double(posX, posY, SnakeSegment.size, SnakeSegment.size);
        TexturePaint tp = new TexturePaint(image, r);
        g2.setPaint(tp);
        g2.fill(r);
    }
}
