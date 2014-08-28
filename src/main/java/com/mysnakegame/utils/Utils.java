package com.mysnakegame.utils;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.mysnakegame.MainPanel;
import com.mysnakegame.SnakeSegment;

public class Utils {

    public static Random rand = new Random();

    public static ImageIcon createImageIcon(String path) {
        URL imgUrl = Utils.class.getResource(path);
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static BufferedImage createBufferedImage(String path) {
        BufferedImage image;
        URL imgUrl = Utils.class.getResource(path);
        if (imgUrl != null) {
            try {
                image = ImageIO.read(imgUrl);
                return image;
            } catch (IOException e) {
                System.err.println("Error during reading file: " + path);
                return null;
            }
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static Point generateRandomLocation() {
        int x, y;
        x = rand.nextInt(MainPanel.WIDTH / SnakeSegment.SEGMENT_SIZE) * SnakeSegment.SEGMENT_SIZE;
        y = rand.nextInt(MainPanel.HEIGHT / SnakeSegment.SEGMENT_SIZE) * SnakeSegment.SEGMENT_SIZE;
        System.out.println("Random point: (" + x + ", " + y + ")");
        return new Point(x, y);
    }
}
