package com.poker.swing.utilityclasses.actionlisteners.commandobjects;

import com.poker.logic.game.GameStateManager;
import com.poker.swing.entrypoint.SwingPokerApp;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Pause both Timers 
 */
@Slf4j
public class PauseGameCommand extends CommandObject {

	@Override
	public boolean execute() {
		GameStateManager gameManager = SwingPokerApp.getGame().getGameStateManager();
		if(!gameManager.isGamePaused() && gameManager.isGameStarted()) {
			actionHelper.pauseGame(true);
			gameManager.pauseGame();
			buttonHelper.enablePauseButton(false);
			return true;
		} else {
			log.warn("Game already paused or Game not started cannot pause Again");
			return false;
		}
	}

	@Override
	public void setupCommand() {
		log.info("Pause Game Command created");
	}

}
