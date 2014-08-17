package com.mysnakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.mysnakegame.Snake.MovementDirection;

public class MainPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 6021045202646433511L;
    private final static int WIDTH = 800;
    private final static int HEIGHT = 800;
    private Snake snake;
    private Thread thread;
    private boolean running = false;

    Point p = MouseInfo.getPointerInfo().getLocation();

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        this.snake = new Snake(WIDTH / 2, HEIGHT / 2);

        start();

        MouseListenerEvents mle = new MouseListenerEvents();
        addMouseListener(mle);

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

    private class MouseListenerEvents implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX() + " , " + e.getY());

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }

    public void start() {
        running = true;
        thread = new Thread(this, "Main thread");
        thread.start();
    }

    public void move() {

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

        snake.removeLast();

        if (snake.getHeadPosition().x >= WIDTH || snake.getHeadPosition().x <= 0 || snake.getHeadPosition().y >= HEIGHT
                || snake.getHeadPosition().y < 0) {
            running = false;
        } else {
            for (int i = 0; i < snake.getSize() - 1; i++) {
                if (snake.getHeadPosition().x == snake.getSegment(i).getX() && snake.getHeadPosition().y == snake.getSegment(i).getY()) {
                    running = false;
                }
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            move();
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP && !(snake.getMovementDirection().equals(Snake.MovementDirection.DOWN))) {
                snake.setMovementDirection(MovementDirection.UP);
            } else if (key == KeyEvent.VK_RIGHT && !(snake.getMovementDirection().equals(Snake.MovementDirection.LEFT))) {
                snake.setMovementDirection(MovementDirection.RIGHT);
            } else if (key == KeyEvent.VK_DOWN && !(snake.getMovementDirection().equals(Snake.MovementDirection.UP))) {
                snake.setMovementDirection(MovementDirection.DOWN);
            } else if (key == KeyEvent.VK_LEFT && !(snake.getMovementDirection().equals(Snake.MovementDirection.RIGHT))) {
                snake.setMovementDirection(MovementDirection.LEFT);
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
