package com.poker.swing.utilityclasses.actionlisteners.commandobjects;


import com.poker.logic.game.GameStateManager;
import com.poker.swing.entrypoint.SwingPokerApp;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Darren
 * Class to handle manually ending the game
 */
@Slf4j
public class EndGameCommand extends CommandObject {

	@Override
	public boolean execute() {
		GameStateManager gameManager = SwingPokerApp.getGame().getGameStateManager();
		if(gameManager.isGameStarted()) {
			log.info("Manually Triggering End Game ");
			SwingPokerApp.getGame().getProperties().setPlayersRemaining(1);
			return true;
		} else {
			log.warn("Game Not not started cannot endGame");
			return false;
		}
	}

	@Override
	public void setupCommand() {
		log.info("End Game Command created");
	}

}
