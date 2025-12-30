package com.poker.swing.utilityclasses.actionlisteners.commandobjects;


import com.poker.logic.game.GameStateManager;
import com.poker.swing.entrypoint.SwingPokerApp;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Resume game if paused
 */
@Slf4j
public class ResumeGameCommand extends CommandObject {

	@Override
	public boolean execute() {
		GameStateManager gameManager = SwingPokerApp.getGame().getGameStateManager();
		if(gameManager.isGameStarted() && gameManager.isGamePaused()) {
			actionHelper.pauseGame(false);
			gameManager.unPauseGame();
			buttonHelper.enablePauseButton(true);
			return true;
		} else {
			log.warn("Game Not paused or Game not started cannot resume");
			return false;
		}
	}

	@Override
	public void setupCommand() {
		log.info("Resume game command created");
	}

}
