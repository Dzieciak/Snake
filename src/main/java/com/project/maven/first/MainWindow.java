package com.project.maven.first;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import org.apache.commons.io.IOUtils;

import com.project.maven.first.utils.Utils;

public class MainWindow extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu fileMenu, helpMenu;
	private JMenuItem newGameMenuItem, aboutMenuItem, exitMenuItem;
		
	public MainWindow() {
		initApplication();
	}
	
	private void initApplication() {
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		newGameMenuItem = new JMenuItem("New game", Utils.createImageIcon("/play_icon_16x16.png"));
		
		ExitAction exitAction = new ExitAction("Exit", Utils.createImageIcon("/exit_icon_16x16.png"), "Exit the application", KeyEvent.VK_E);
		exitMenuItem = new JMenuItem(exitAction);
		
		ShowDialogAction showDialogAction = new ShowDialogAction("About", Utils.createImageIcon("/about_icon_16x16.png"), this, "About this application", KeyEvent.VK_A);
		aboutMenuItem = new JMenuItem(showDialogAction);
		
		fileMenu.add(newGameMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		helpMenu.add(aboutMenuItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		MainPanel p = new MainPanel();
		this.add(p);
		
		this.setJMenuBar(menuBar);
		
		this.setTitle("First project main window");
		this.setSize(800,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(Utils.createImageIcon("/snake_icon_16x16.png").getImage());
		UIManager.put("swing.boldMetal", Boolean.FALSE);
	}	
		
	public class ExitAction extends AbstractAction {

		public ExitAction(String name, ImageIcon icon, String shortDescription, Integer mnemonic) {
			super(name, icon);
			putValue(SHORT_DESCRIPTION, shortDescription);
			putValue(MNEMONIC_KEY, mnemonic);
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	}
	
	public class ShowDialogAction extends AbstractAction {

		private JFrame frame;
		
		public ShowDialogAction(String name, ImageIcon icon, JFrame frame, String shortDescription, Integer mnemonic) {
			super(name,icon);
			this.frame = frame;
			putValue(SHORT_DESCRIPTION, shortDescription);
			putValue(MNEMONIC_KEY, mnemonic);
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			JDialog aboutDialog = new JDialog(frame, "About...", true);
			aboutDialog.setLocationRelativeTo(frame);
			aboutDialog.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel labelIcon = new JLabel(Utils.createImageIcon("/snake_icon_128x128.png"));
			labelIcon.setOpaque(true);
			labelIcon.setBackground(Color.RED);
			aboutDialog.add(labelIcon);
			try {
				String str = IOUtils.toString(this.getClass().getResourceAsStream("/About.html"),"UTF-8");
				JLabel labelText = new JLabel(str);
				labelText.setOpaque(true);
				labelText.setBackground(Color.CYAN);
				aboutDialog.add(labelText);
			} catch (IOException e) {
				e.printStackTrace();
			}
				aboutDialog.setIconImage(Utils.createImageIcon("/snake_icon_16x16.png").getImage());
				aboutDialog.setResizable(false);
				Point p = frame.getLocation();
				aboutDialog.setSize(400, 170);
				aboutDialog.setLocation(p.x + frame.getWidth()/2 - aboutDialog.getWidth()/2, p.y + frame.getHeight()/2 - aboutDialog.getHeight()/2);
				aboutDialog.setVisible(true);
		}
	}	
}
