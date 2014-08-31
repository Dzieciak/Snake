package com.mysnakegame.fruits;

import java.awt.image.BufferedImage;

import com.mysnakegame.utils.Utils;

public enum Fruits {
    APPLE(Utils.createBufferedImage("/fruits/apple_16x16.png"), 10), BANANA(Utils.createBufferedImage("/fruits/banana_16x16.png"), 5), ORANGE(
            Utils.createBufferedImage("/fruits/orange_16x16.png"), 7), PEAR(Utils.createBufferedImage("/fruits/pear_16x16.png"), 3), STRAWBERRY(
            Utils.createBufferedImage("/fruits/strawberry_16x16.png"), 1);

    private final BufferedImage image;
    private final int pointValue;

    Fruits(BufferedImage image, int pointValue) {
        this.image = image;
        this.pointValue = pointValue;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getPointsValue() {
        return pointValue;
    }
}
