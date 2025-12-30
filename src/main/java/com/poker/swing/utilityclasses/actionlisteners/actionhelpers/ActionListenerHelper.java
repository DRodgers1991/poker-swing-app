package com.poker.swing.utilityclasses.actionlisteners.actionhelpers;

import java.awt.Component;
import java.util.List;

import javax.swing.JTextField;

import com.poker.swing.entrypoint.SwingPokerApp;

/**
 * @author Darren
 * Utility methods to assist with admin/management action commands commands
 */
public class ActionListenerHelper {

	/**
	 * Sets the should pause flag in both the round and total timers threads
	 * this triggers specific functionality in those threads
	 * @param shouldPause
	 */
	public void pauseGame(boolean shouldPause) {
		if(shouldPause) {
			SwingPokerApp.getGame().getGameStateManager().pauseGame();
		} else {
			SwingPokerApp.getGame().getGameStateManager().unPauseGame();
		}
		
	}

	/**
	 * @param components
	 * @param componentNum
	 * @return
	 */
	public String isValueValid(List<Component> components, int componentNum) {
		String value = ((JTextField) components.get(componentNum)).getText();
		if("".equals(value)) {
			value="0";
		} 
		return value;
	}
}
