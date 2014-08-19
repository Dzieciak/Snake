package com.mysnakegame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import com.mysnakegame.dialogs.AboutDialog;
import com.mysnakegame.utils.Utils;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 8911063216016530341L;
    public static boolean gameStarted = false;
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem newGameMenuItem, aboutMenuItem, exitMenuItem;
    private AboutDialog aboutDialog;

    public MainWindow() {
        setTitle("Wonrz");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(Utils.createImageIcon("/snake_icon_16x16.png").getImage());

        initApplication();
    }

    private final void initApplication() {
        setLayout(new GridLayout(1, 1, 0, 0));

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        NewGameAction newGameAction = new NewGameAction("New game", Utils.createImageIcon("/play_icon_16x16.png"), "Start new game",
                KeyEvent.VK_N);
        newGameMenuItem = new JMenuItem(newGameAction);

        ExitAction exitAction = new ExitAction("Exit", Utils.createImageIcon("/exit_icon_16x16.png"), "Exit the application", KeyEvent.VK_E);
        exitMenuItem = new JMenuItem(exitAction);

        ShowDialogAction showDialogAction = new ShowDialogAction("About", Utils.createImageIcon("/about_icon_16x16.png"), this,
                "About this application", KeyEvent.VK_A);
        aboutMenuItem = new JMenuItem(showDialogAction);

        fileMenu.add(newGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        MainPanel p = new MainPanel();
        add(p);
        pack();

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public class ExitAction extends AbstractAction {

        private static final long serialVersionUID = 3039059743053449508L;

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

        private static final long serialVersionUID = -7666919232280854843L;
        private JFrame frame;

        public ShowDialogAction(String name, ImageIcon icon, JFrame frame, String shortDescription, Integer mnemonic) {
            super(name, icon);
            this.frame = frame;
            putValue(SHORT_DESCRIPTION, shortDescription);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            aboutDialog = new AboutDialog(frame);
        }
    }

    public class NewGameAction extends AbstractAction {

        private static final long serialVersionUID = 4592465389199267807L;

        public NewGameAction(String name, ImageIcon icon, String shortDescription, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, shortDescription);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("New game started...");
            gameStarted = true;
        }

    }
}
