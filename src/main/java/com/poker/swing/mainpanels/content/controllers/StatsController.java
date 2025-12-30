package com.poker.swing.mainpanels.content.controllers;

import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.StatsPanel;
import lombok.Getter;

/**
 * @author Darren
 * Handler for a current games statistics
 */
@Getter
public class StatsController {
	private final StatsPanel panel;
	/**
	 * Initialise controller 
	 */
	public StatsController(){
		panel = new StatsPanel();
	}

    /**
	 * Update the statistics UI
	 */
	public void update() {
		PokerProperties props = SwingPokerApp.getGame().getProperties();
		props.addRebuy();

		panel.getTotalChips().setText(String.valueOf(props.getTotalChipsInGame()));
		panel.getReBuyLabel().setText(String.valueOf(props.getRebuys()));
		panel.getTotalPrize().setText("�"+SwingPokerApp.getGame().calcTotalPrizeMoney()); 
		panel.getAverageStackLabel().setText(String.valueOf(props.calcAverageStack()));

	}

}
