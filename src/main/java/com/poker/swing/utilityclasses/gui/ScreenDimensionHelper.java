package com.poker.swing.utilityclasses.gui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenDimensionHelper {
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static int calcHeight() {
		return (int) screenSize.getHeight();   
	}

	public static int calcWidth() {
		return  (int) screenSize.getWidth();
	}

	public  static BufferedImage scaleImage(int width, int height, String filename) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(
				RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(new ImageIcon(filename).getImage(), 0, 0, width, height, null);
		return bi;
	}
}
