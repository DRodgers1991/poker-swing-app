package com.poker.swing.utilityclasses.actionlisteners.commandobjects;

import com.poker.logic.game.GameStateManager;
import com.poker.logic.game.PokerGame;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.buttons.ButtonsPanel;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Darren 
 * Begin game
 */
@Slf4j
public class StartGameCommand extends CommandObject{
	@Override
	public boolean execute() {
		PokerGame game = SwingPokerApp.getGame();
		GameStateManager gameState = game.getGameStateManager();
		if(!gameState.isGameStarted()) {
			SwingPokerApp.startTimers();
			gameState.startGame(game.getTimer());
			buttonHelper.affectConfigButtons(false);
			ButtonsPanel buttonPanel = SwingPokerApp.getLeftContent().getPanel().getButtonPanel();
			buttonPanel.getEndGame().setEnabled(true);
			buttonPanel.getPauseGame().setEnabled(true);
			buttonPanel.getStartGame().setEnabled(false);
			buttonPanel.getAddReBuy().setEnabled(true);
			buttonPanel.getEliminate().setEnabled(true);
			return true;
		} else { 
			log.info("Game already running cannot start Again");
			return false;
		}
	}

	@Override
	public void setupCommand() {
		log.info("Start Game Command created");
	}


}
