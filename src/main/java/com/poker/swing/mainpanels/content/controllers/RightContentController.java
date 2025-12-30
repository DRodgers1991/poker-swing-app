package com.poker.swing.mainpanels.content.controllers;

import com.poker.swing.mainpanels.content.panels.RightContentPanel;
import lombok.Getter;

/**
 * @author Darren
 * Controller for the right hand side of the UI
 */
@Getter
public class RightContentController {
	private final RightContentPanel panel;

	/**
	 * Initialise controller
	 */
	public RightContentController() {
		panel = new RightContentPanel();
	}

}
