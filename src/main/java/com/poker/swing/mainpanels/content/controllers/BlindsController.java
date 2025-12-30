package com.poker.swing.mainpanels.content.controllers;

import java.util.Timer;
import java.util.TimerTask;

import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.BlindsPanel;
import lombok.Getter;

/**
 * @author Darren
 * Controller to handle manipulation to the blinds and the updating of them after each round
 */
@Getter
public class BlindsController {
	private final BlindsPanel blindsPanel;
	/**
	 * Constructor which also initialises the blinds panel
	 * @param colourPanel
	 */
	public BlindsController(ChipColourController colourPanel) {
		blindsPanel = new BlindsPanel(colourPanel);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				upDate();
			}
		};
		new Timer().scheduleAtFixedRate(task, 0L,500L);
	}

	/**
	 * call to update the blinds panel if true then it will force the update
	 */
	public void upDate(){
		PokerProperties properties = SwingPokerApp.getGame().getProperties();
		blindsPanel.getSmallBlind().setText(Integer.toString(properties.getCurrentSmallBlind()));
		blindsPanel.getBigBlind().setText(Integer.toString(properties.getCurrentBigBlind()));
		blindsPanel.getNextSmallBlind().setText(Integer.toString(properties.getNextSmallBlind()));
		blindsPanel.getNextBigBlind().setText(Integer.toString(properties.getNextBigBlind()));
	}


}
