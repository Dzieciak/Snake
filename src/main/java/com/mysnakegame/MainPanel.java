package com.mysnakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.mysnakegame.Snake.MovementDirection;
import com.mysnakegame.fruits.Fruit;
import com.mysnakegame.fruits.Fruits;

public class MainPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 6021045202646433511L;
    private static final int REFRESH_TIME = 75;
    public static final int WIDTH = 960;
    public static final int HEIGHT = 960;
    private Snake snake;
    private Fruit fruit;
    private Thread thread;
    private boolean running = false;
    private boolean isDirChgAllowed = false;
    private boolean gameStarted = false;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        snake = new Snake();
        fruit = new Fruit(Fruits.APPLE, snake);
        KeyHandler kh = new KeyHandler();
        addKeyListener(kh);
        setFocusable(true);
        thread = new Thread(this, "Main thread");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < snake.getSize(); i++) {
            snake.getSegments().get(i).draw(g);
        }
        fruit.draw(g);
    }

    public void start() {
        gameStarted = false;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = true;
        thread = new Thread(this, "Main thread");
        snake.reset();
        thread.start();
        gameStarted = true;
    }

    public void move() {

        switch (snake.getMovementDirection()) {
            case UP:
                snake.addSegment(snake.getHeadPosition().x, snake.getHeadPosition().y - SnakeSegment.SEGMENT_SIZE);
                break;
            case RIGHT:
                snake.addSegment(snake.getHeadPosition().x + SnakeSegment.SEGMENT_SIZE, snake.getHeadPosition().y);
                break;
            case DOWN:
                snake.addSegment(snake.getHeadPosition().x, snake.getHeadPosition().y + SnakeSegment.SEGMENT_SIZE);
                break;
            case LEFT:
                snake.addSegment(snake.getHeadPosition().x - SnakeSegment.SEGMENT_SIZE, snake.getHeadPosition().y);
                break;
        }

        if (snake.getHeadPosition().x >= WIDTH || snake.getHeadPosition().x < 0 || snake.getHeadPosition().y >= HEIGHT
                || snake.getHeadPosition().y < 0) {
            running = false;
            gameStarted = false;
        } else {
            for (int i = 1; i < snake.getSize(); i++) {
                if (snake.getHeadPosition().x == snake.getSegment(i).getX() && snake.getHeadPosition().y == snake.getSegment(i).getY()) {
                    running = false;
                    gameStarted = false;
                    // snake.getSegment(i).setColor(Color.BLACK);
                }
            }
        }

        if (fruit.getLocation().equals(snake.getHeadPosition())) {
            fruit.setNewLocation();
        } else {
            snake.removeLast();
        }

    }

    @Override
    public void run() {
        while (running) {
            isDirChgAllowed = true;
            move();
            repaint();
            try {
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (isDirChgAllowed) {
                if (key == KeyEvent.VK_UP && !(snake.getMovementDirection().equals(Snake.MovementDirection.DOWN)) && running) {
                    snake.setMovementDirection(MovementDirection.UP);
                } else if (key == KeyEvent.VK_RIGHT && !(snake.getMovementDirection().equals(Snake.MovementDirection.LEFT)) && running) {
                    snake.setMovementDirection(MovementDirection.RIGHT);
                } else if (key == KeyEvent.VK_DOWN && !(snake.getMovementDirection().equals(Snake.MovementDirection.UP)) && running) {
                    snake.setMovementDirection(MovementDirection.DOWN);
                } else if (key == KeyEvent.VK_LEFT && !(snake.getMovementDirection().equals(Snake.MovementDirection.RIGHT)) && running) {
                    snake.setMovementDirection(MovementDirection.LEFT);
                }
                isDirChgAllowed = false;
            }

            if (key == KeyEvent.VK_PAUSE && thread.isAlive() && gameStarted) {
                running = false;
                try {
                    thread.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            } else if (key == KeyEvent.VK_PAUSE && !thread.isAlive() && gameStarted) {
                running = true;
                thread = new Thread(MainPanel.this, "Main thread");
                thread.start();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

    }

}
