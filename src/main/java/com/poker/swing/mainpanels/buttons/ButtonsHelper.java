package com.poker.swing.mainpanels.buttons;

import java.util.List;

import javax.swing.JButton;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.LeftContentPanel;

/**
 * @author Darren
 * Utility class with methods to affect the state of the buttons on the UI
 */
public class ButtonsHelper {

	/**
	 * Enables or Disables all admin buttons
	 * Usually used once game has started or ended 
	 * @param enable - should the buttons be clickable or greyed out
	 */
	public void affectConfigButtons(boolean enable) {
		List<JButton> buttonList = SwingPokerApp.getLeftContent().getPanel().getButtonPanel().getButtonsToDisable();
		buttonList.forEach(i -> i.setEnabled(enable));
	}


	/**
	 * Resets buttons to their default values like they were at the start of the game
	 */
	public void endGame() {
		SwingPokerApp.getGame().getGameStateManager().endGame();
		ButtonsPanel panel = SwingPokerApp.getLeftContent().getPanel().getButtonPanel();
		panel.getEndGame().setEnabled(false);
		panel.getStartGame().setEnabled(true);
		panel.getPauseGame().setEnabled(false);
		panel.getEliminate().setEnabled(false);
		panel.getAddReBuy().setEnabled(false);
	}

	/**
	 * @param enable
	 * After calling pause this method swaps the active state on the pause and resume buttons
	 */
	public void enablePauseButton(boolean enable) {
		LeftContentPanel leftPanel = SwingPokerApp.getLeftContent().getPanel();
		leftPanel.getButtonPanel().getResumeGame().setEnabled(!enable);
		leftPanel.getButtonPanel().getPauseGame().setEnabled(enable);  
	}
}
