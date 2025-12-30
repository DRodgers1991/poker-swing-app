package com.poker.swing.mainpanels.content.panels;

import lombok.Getter;

import java.awt.Color;
import java.io.Serial;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Darren
 * Panel to show the connectivity of outside applications
 */
@Getter
public class ClientResponsePanel extends JPanel {
	@Serial
	private static final long serialVersionUID = -6370663007784875245L;
	private final JTextArea textArea;

	/**
	 * Initialise the panel
	 */
	public ClientResponsePanel() {
		textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setForeground(Color.ORANGE);
		this.add(textArea);
		this.setOpaque(false);
	}


}
