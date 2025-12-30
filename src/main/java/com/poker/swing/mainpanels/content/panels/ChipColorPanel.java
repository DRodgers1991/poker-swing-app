package com.poker.swing.mainpanels.content.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.poker.swing.mainpanels.content.controllers.ChipColourController;

import java.io.Serial;

/**
 * @author Dazzler
 * Panel to hold the chip pairs<Image, Value>
 */
public class ChipColorPanel extends JPanel {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialise the panel
	 * @param controller
	 */
	public ChipColorPanel(ChipColourController controller, int numOfColours) {
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		for(int x=1; x <= numOfColours; x++){
			this.add(controller.createChipPair(x));
		}
	}

}
