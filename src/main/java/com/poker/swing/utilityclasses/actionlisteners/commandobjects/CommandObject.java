package com.poker.swing.utilityclasses.actionlisteners.commandobjects;

import com.poker.swing.mainpanels.buttons.ButtonsHelper;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.ActionListenerHelper;

/**
 * @author Darren
 * Template for game controlling commands
 */
public abstract class CommandObject {
	final ActionListenerHelper actionHelper = new ActionListenerHelper();
	final ButtonsHelper buttonHelper = new ButtonsHelper();
	
	/**
	 * Default constructor
	 */
	public CommandObject(){
		setupCommand();
	}

	/**
	 * Execute command
	 * @return boolean
	 */
	public abstract boolean execute();
	/**
	 * Setup game command values
	 */
	public abstract void setupCommand();
}
