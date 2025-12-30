package com.poker.swing.mainpanels.content.controllers;

import com.poker.swing.mainpanels.content.panels.LeftContentPanel;
import lombok.Getter;

/**
 * @author Darren
 * Controller for the left side of the UI
 */
@Getter
public class LeftContentController {
	private LeftContentPanel panel;

    public void initalisePanel(){
		panel = new LeftContentPanel();
	}
}
