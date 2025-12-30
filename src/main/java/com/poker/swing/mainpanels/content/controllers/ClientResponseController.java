package com.poker.swing.mainpanels.content.controllers;

import java.sql.Time;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import com.poker.swing.mainpanels.content.panels.ClientResponsePanel;
import com.poker.swing.utilityclasses.gui.ActionOptions;
import com.poker.swing.utilityclasses.gui.AdminCommandObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Class handles if there is a response from the client to display it on the UI
 * It also handles logging out any commands received locally or from an outside source
 */
@Getter
@Slf4j
public class ClientResponseController {

	private final ClientResponsePanel panel;

	/**
	 * default Constructor
	 */
	public ClientResponseController() {
		panel = new ClientResponsePanel();
	}

    /**
	 * Add the received app message to the UI
	 * 
	 * @param currentTimeMillis
	 * @param action
	 * @param source
	 */
	public void addToLog(long currentTimeMillis, ActionOptions action,
			String source) {
		AdminCommandObject newCommand = new AdminCommandObject(new Time(currentTimeMillis), source, action);
		log.info("Command added to Log | {}",newCommand);
		panel.getTextArea().append(newCommand+"\n");
		trunkTextArea(panel.getTextArea());
	}


	private void trunkTextArea(JTextArea txtWin) {
		int scrollSize = 10;
		int numLinesToTrunk = txtWin.getLineCount() - scrollSize;
		if(numLinesToTrunk > 0) {
			try {
				int posOfLastLineToTrunk = txtWin.getLineEndOffset(numLinesToTrunk - 1);
				txtWin.replaceRange("",0,posOfLastLineToTrunk);
			}
			catch (BadLocationException ex) {
				log.error("Error scrolling pane {}", ex);
			}
		}
	}
}
