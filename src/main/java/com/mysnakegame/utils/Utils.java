package com.mysnakegame.utils;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysnakegame.MainPanel;
import com.mysnakegame.SnakeSegment;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);
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
        int x = rand.nextInt(MainPanel.WIDTH / SnakeSegment.SEGMENT_SIZE) * SnakeSegment.SEGMENT_SIZE;
        int y = rand.nextInt(MainPanel.HEIGHT / SnakeSegment.SEGMENT_SIZE) * SnakeSegment.SEGMENT_SIZE;
        LOGGER.info("Random point: ({}, {})", x, y);
        return new Point(x, y);
    }
}
