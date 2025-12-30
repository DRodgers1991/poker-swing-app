package com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects;

import javax.swing.JFrame;

import com.poker.swing.mainpanels.buttons.ButtonsHelper;



/**
 * @author Darren
 * Template for management commands
 */
public abstract class ManagerObject {
	JFrame managerFrame;
	final ButtonsHelper helper;

	/**
	 * Default constructor
	 */
	public ManagerObject(){
		setupManager();
		helper = new ButtonsHelper();
		addWindowListener(managerFrame);
	}

	/**
	 * Setup management command
	 */
	public abstract void setupManager();
	/**
	 * Setup values needed
	 */
	public abstract void createValues();
	/**
	 * Show result
	 */
	public abstract void showFrame();
	/**
	 * Execute command values
	 */
	public abstract void submit();

	private void addWindowListener(JFrame frame) {
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				helper.affectConfigButtons(true);
			}
		});
	}

}
