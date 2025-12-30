package com.poker.swing.utilityclasses.actionlisteners.commandobjects;

import com.poker.logic.game.GameStateManager;
import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.StatsPanel;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Darren
 * Class to handle eliminating a player from the game
 */
@Slf4j
public class EliminatePlayerCommand extends CommandObject {

	@Override
	public boolean execute() {
		GameStateManager gameManager = SwingPokerApp.getGame().getGameStateManager();
		PokerProperties properties = SwingPokerApp.getGame().getProperties();
		if(gameManager.isGameStarted()) {
			log.info("Attempting to eliminate player");
			gameManager.eliminatePlayer();
			StatsPanel instance = SwingPokerApp.getStatsController().getPanel();
			instance.getPlayerRemainingLabel().setText(String.valueOf(properties.getPlayersRemaining()));
			if(gameManager.isGameEnded()){
				buttonHelper.endGame();
			} else {
				instance.getAverageStackLabel().setText(String.valueOf(properties.calcAverageStack()));
			}
			return true;
		} else {
			log.warn("Game Not not started cannot eliminate player");
			return false;
		}
	}

	@Override
	public void setupCommand() {
		log.info("Eliminate Player Command Created");
	}

}
