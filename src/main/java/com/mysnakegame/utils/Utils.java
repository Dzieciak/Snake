package com.mysnakegame.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utils {
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
}
