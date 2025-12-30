package com.poker.swing.utilityclasses.actionlisteners;

import java.awt.Component;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.ActionListenerHelper;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.ChipManager;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Darren
 * Document Listener to allow any changes made in config to be reflected on the chip pair UI
 */
@Slf4j
public class ChipPairListener implements DocumentListener {
	private final ChipManager manager;
	private int totalChips = 0;
	/**
	 * Initialise the listener
	 * @param manager
	 */
	public ChipPairListener(ChipManager manager) {
		this.manager = manager;
	}

	private void implementChange() {
		totalChips =0;
		int numOfPlayers = SwingPokerApp.getGame().getProperties().getInitialPlayers();
		log.info("ReCalculating values");
		ActionListenerHelper actionHelper = new ActionListenerHelper();
		Map<String, List<Component>> componentList = manager.getEditableChipPair();

		componentList.forEach((key, compList) -> {
			String value = actionHelper.isValueValid(compList, 1);
			String dealt = actionHelper.isValueValid(compList, 2);
			int newTotal = Integer.parseInt(value) * Integer.parseInt(dealt);
			log.info("Chip Color : {}, New Total = {}, old = {}", key,newTotal, ((JLabel) compList.get(3)).getText());
			((JLabel) compList.get(3)).setText(Integer.toString(newTotal));
			totalChips += newTotal;
		});

		manager.getTotalChips().setText(Integer.toString(totalChips*numOfPlayers));
		manager.getStartingStack().setText(Integer.toString(totalChips));
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		implementChange();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		implementChange();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		implementChange();
	}

}
