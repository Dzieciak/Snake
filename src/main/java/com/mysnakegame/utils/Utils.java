package com.mysnakegame.utils;

import java.net.URL;

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
}
