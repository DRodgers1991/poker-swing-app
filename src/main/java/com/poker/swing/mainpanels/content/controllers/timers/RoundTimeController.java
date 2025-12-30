package com.poker.swing.mainpanels.content.controllers.timers;

import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.poker.logic.game.GameStateManager;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.buttons.ButtonsHelper;
import com.poker.swing.mainpanels.content.panels.RoundTimePanel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Controller to manage all aspect of round timings
 */
@Slf4j
public class RoundTimeController extends TimerTask{
	private final RoundTimePanel roundPanel;

	/**
	 * Initialise the Controller
	 */
	public RoundTimeController() {
		roundPanel = new RoundTimePanel();
		upDate();
	}

	/**
	 * update the RoundTime Panel UI
	 */
	public void upDate() {
		roundPanel.getBlindsUp().setText(SwingPokerApp.getGame().getTimer().getTimeToGoAsString());
		roundPanel.getTimeStarted().setText(SwingPokerApp.getGame().getTimer().getRoundStartAsString());
		int currentRound = SwingPokerApp.getGame().getTimer().getCurrentRound();
		roundPanel.getRoundNum().setText(Integer.toString(currentRound));
		roundPanel.getNextBreak().setText(Integer.toString(SwingPokerApp.getGame().getProperties().getNextBreak(currentRound)));
	}

	public RoundTimePanel getPanel() {
		return roundPanel;
	}
	
	@Override
	public void run() {
		GameStateManager gameState = SwingPokerApp.getGame().getGameStateManager();
		if(!gameState.isGamePaused()){
			upDate();
		}
		if(gameState.isGameEnded()){
			endGame();
		}
	}
	
	/**
	 * End the current game, reset the UI and then print the results in a JPanel 
	 */
	private void endGame() {
		log.info("Triggering End Game State");
		ButtonsHelper buttonsHelper = new ButtonsHelper();
		buttonsHelper.affectConfigButtons(true);
		buttonsHelper.endGame();
		SwingPokerApp.cancelTimers();
		buttonsHelper.affectConfigButtons(true);
		buttonsHelper.endGame();
		JOptionPane.showMessageDialog(null, SwingPokerApp.getGame().getPrizePlaceString());
		log.info("End Game State Complete");
		SwingPokerApp.updateUI();
		SwingPokerApp.reset();
	}
}
