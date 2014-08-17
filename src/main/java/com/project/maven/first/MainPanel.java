package com.project.maven.first;

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

import com.project.maven.first.Snake.MovementDirection;

public class MainPanel extends JPanel implements Runnable {
		
	private final static int WIDTH = 800;
	private final static int HEIGHT = 800;
	private Snake s;
	private Thread thread;
	private boolean running = false;
	
	Point p = MouseInfo.getPointerInfo().getLocation();
	
	public MainPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));		
		setBackground(Color.WHITE);
		
		this.s = new Snake(WIDTH / 2, HEIGHT / 2);
		
		start();
		
		
		MouseListenerEvents mle = new MouseListenerEvents();
		addMouseListener(mle);
		
		KeyHandler kh = new KeyHandler();
		addKeyListener(kh);
		setFocusable(true);
						
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < s.getSize(); i++) {
			s.getSegments().get(i).draw(g);
			
		}		
	}
	
	private class MouseListenerEvents implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getX() + " , " + e.getY());
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Main thread");
		thread.start();
	}
	
	public void tick() {
		
		//p = MouseInfo.getPointerInfo().getLocation();
		//System.out.println(p.x + " , " + p.y);
		
		s.addSegment(s.getHeadPosition().x, s.getHeadPosition().y);
		s.removeLast();
		
		
		if (s.getHeadPosition().x >= WIDTH + Segment.size || s.getHeadPosition().y >= HEIGHT + Segment.size) {
			s.moveToPoint(WIDTH / 2, HEIGHT / 2);
		}
	}
	
	
	@Override
	public void run() {
		while(running) {
			tick();
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
			
			if(key == KeyEvent.VK_UP && !(s.getMovementDirection().equals(Snake.MovementDirection.DOWN))) {
				System.out.println("UP");
				s.setMovementDirection(MovementDirection.UP);
			} else if(key == KeyEvent.VK_RIGHT && !(s.getMovementDirection().equals(Snake.MovementDirection.LEFT))) {
				System.out.println("RIGHT");
				s.setMovementDirection(MovementDirection.RIGHT);
			} else if(key == KeyEvent.VK_DOWN && !(s.getMovementDirection().equals(Snake.MovementDirection.UP))) {
				System.out.println("DOWN");
				s.setMovementDirection(MovementDirection.DOWN);
			} else if(key == KeyEvent.VK_LEFT && !(s.getMovementDirection().equals(Snake.MovementDirection.RIGHT))) {
				System.out.println("LEFT");
				s.setMovementDirection(MovementDirection.LEFT);
			}
			
			/*if(key == KeyEvent.VK_P && running == true) {
				System.out.println("PAUSE");
				running = false;
			} else if(key == KeyEvent.VK_P && running == false) {
				System.out.println("RUN");
				running = true;
			}*/
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
						
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
	
	
}
