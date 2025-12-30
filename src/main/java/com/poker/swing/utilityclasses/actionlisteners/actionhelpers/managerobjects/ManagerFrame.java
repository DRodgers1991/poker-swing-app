package com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects;

import java.awt.Color;
import java.io.Serial;

import javax.swing.JFrame;

/**
 * @author Darren
 * Frame to display result of management action
 */
class ManagerFrame extends JFrame{

	@Serial
	private static final long serialVersionUID = 1L;

	public ManagerFrame() {
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setEnabled(false);
	}

}
