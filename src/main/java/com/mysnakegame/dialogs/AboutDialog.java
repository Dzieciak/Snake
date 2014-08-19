package com.mysnakegame.dialogs;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.mysnakegame.utils.Utils;

public class AboutDialog extends JDialog {

    private String title = "About...";
    private Component parent;
    private JLabel labelText;
    private JLabel labelIcon;
    private Point parentLocation;

    public AboutDialog(Component parent) {
        this.parent = parent;
        initialize();
    }

    private final void initialize() {
        labelIcon = new JLabel(Utils.createImageIcon("/snake_icon_128x128.png"));
        labelIcon.setOpaque(true);
        labelIcon.setBackground(Color.RED);
        add(labelIcon);

        parentLocation = parent.getLocation();

        try {
            String str = IOUtils.toString(this.getClass().getResourceAsStream("/About.html"), "UTF-8");
            labelText = new JLabel(str);
            labelText.setOpaque(true);
            labelText.setBackground(Color.CYAN);
            add(labelText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle(title);
        setLocationRelativeTo(parent);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setIconImage(Utils.createImageIcon("/about_icon_16x16.png").getImage());
        setResizable(false);
        setSize(400, 170);
        setLocation(parentLocation.x + parent.getWidth() / 2 - getWidth() / 2, parentLocation.y + parent.getHeight() / 2 - getHeight() / 2);
        setModal(true);
        setVisible(true);
    }

}
