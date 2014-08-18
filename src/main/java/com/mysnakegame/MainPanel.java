package com.mysnakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.mysnakegame.Snake.MovementDirection;

public class MainPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 6021045202646433511L;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private Snake snake;
    private Thread thread;
    private boolean isRunning = false;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        this.snake = new Snake(WIDTH / 2, HEIGHT / 2);
        start();
        KeyHandler kh = new KeyHandler();
        addKeyListener(kh);
        setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < snake.getSize(); i++) {
            snake.getSegments().get(i).draw(g);
        }
    }

    public void start() {
        isRunning = true;
        thread = new Thread(this, "Main thread");
        thread.start();
    }

    public void move() {

        snake.removeLast();

        switch (snake.getMovementDirection()) {
            case UP:
                snake.addSegment(snake.getHeadPosition().x, snake.getHeadPosition().y - SnakeSegment.size);
                break;
            case RIGHT:
                snake.addSegment(snake.getHeadPosition().x + SnakeSegment.size, snake.getHeadPosition().y);
                break;
            case DOWN:
                snake.addSegment(snake.getHeadPosition().x, snake.getHeadPosition().y + SnakeSegment.size);
                break;
            case LEFT:
                snake.addSegment(snake.getHeadPosition().x - SnakeSegment.size, snake.getHeadPosition().y);
                break;
        }

        if (snake.getHeadPosition().x >= WIDTH || snake.getHeadPosition().x < 0 || snake.getHeadPosition().y >= HEIGHT
                || snake.getHeadPosition().y < 0) {
            isRunning = false;
        } else {
            for (int i = 1; i < snake.getSize(); i++) {
                if (snake.getHeadPosition().x == snake.getSegment(i).getX() && snake.getHeadPosition().y == snake.getSegment(i).getY()) {
                    isRunning = false;
                }
            }
        }

    }

    @Override
    public void run() {
        int i = 0;
        while (isRunning) {
            move();
            repaint();
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i == 20) {
                System.out.println("Is Running... " + thread.isAlive());
                i = 0;
            }
        }
    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP && !(snake.getMovementDirection().equals(Snake.MovementDirection.DOWN)) && isRunning) {
                snake.setMovementDirection(MovementDirection.UP);
            } else if (key == KeyEvent.VK_RIGHT && !(snake.getMovementDirection().equals(Snake.MovementDirection.LEFT)) && isRunning) {
                snake.setMovementDirection(MovementDirection.RIGHT);
            } else if (key == KeyEvent.VK_DOWN && !(snake.getMovementDirection().equals(Snake.MovementDirection.UP)) && isRunning) {
                snake.setMovementDirection(MovementDirection.DOWN);
            } else if (key == KeyEvent.VK_LEFT && !(snake.getMovementDirection().equals(Snake.MovementDirection.RIGHT)) && isRunning) {
                snake.setMovementDirection(MovementDirection.LEFT);
            } else if (key == KeyEvent.VK_PAUSE && thread.isAlive()) {
                isRunning = false;
                try {
                    thread.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            } else if (key == KeyEvent.VK_PAUSE && !thread.isAlive()) {
                isRunning = true;
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
