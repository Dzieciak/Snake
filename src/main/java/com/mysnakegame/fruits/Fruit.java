package com.mysnakegame.fruits;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysnakegame.Snake;
import com.mysnakegame.SnakeSegment;
import com.mysnakegame.utils.Utils;

public class Fruit extends JComponent {

    private static final long serialVersionUID = 3854302124338512392L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Fruit.class);
    private int posX, posY;
    private Fruits fruit;
    private Snake snake;

    public Fruit(Fruits fruit, Snake snake) {
        this.fruit = fruit;
        this.snake = snake;
        setNewLocation();
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D r = new Rectangle2D.Double(posX, posY, SnakeSegment.SEGMENT_SIZE, SnakeSegment.SEGMENT_SIZE);
        TexturePaint tp = new TexturePaint(fruit.getImage(), r);
        g2.setPaint(tp);
        g2.fill(r);
    }

    public void setNewLocation() {
        boolean validPosition = false;
        Point point = new Point();
        while (!validPosition) {
            point = Utils.generateRandomLocation();
            validPosition = validateLocation(point, snake);
            LOGGER.info("Valid position: {}", validPosition);
        }
        posX = point.x;
        posY = point.y;
    }

    public Point getLocation() {
        return new Point(posX, posY);
    }

    public void setFruit(Fruits fruit) {
        this.fruit = fruit;
    }

    public Fruits getFruit() {
        return fruit;
    }

    public static boolean validateLocation(Point point, Snake snake) {
        for (SnakeSegment segment : snake.getSegments()) {
            if (segment.getLocation().equals(point)) {
                return false;
            }
        }
        return true;
    }

}
