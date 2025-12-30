package com.poker.swing.utilityclasses.actionlisteners.commandobjects;

import com.poker.logic.game.GameStateManager;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.controllers.StatsController;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Darren
 * Class to add a re-buy to the game
 */
@Slf4j
public class AddRebuyCommand extends CommandObject {
	@Override
	public boolean execute() {
		GameStateManager gameManager = SwingPokerApp.getGame().getGameStateManager();
		if(gameManager.isGameStarted()) {
			log.info("Adding ReBuy");
			StatsController stats = SwingPokerApp.getStatsController();
			stats.update();
			return true;
		} else {
			log.info("Game Not not started cannot addRebuy");
			return false;
		}
	}

	@Override
	public void setupCommand() {
		log.info("AddRebuyCommand Created");
	}

}
