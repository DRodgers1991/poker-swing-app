package com.poker.swing.mainpanels.content.panels;

import java.awt.FlowLayout;
import java.io.Serial;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.poker.swing.mainpanels.content.controllers.ChipColourController;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Panel to hold information on current small and big blinds and the next blinds
 */
public class BlindsPanel extends JPanel{

	@Serial
	private static final long serialVersionUID = -5407014183843370565L;
	private final PokerGuiHelper guiHelper;

	@Getter
    private JLabel bigBlind;
	@Getter
    private JLabel smallBlind;
	@Getter
    private JLabel nextSmallBlind;
	@Getter
    private JLabel nextBigBlind;

	/**
	 * Initialise the panel
	 *
	 * @param colourPanel
	 */
	public BlindsPanel(ChipColourController colourPanel) {
		guiHelper = new PokerGuiHelper();
		createPanel(colourPanel);
	}

	private JPanel createNextBlinds() {
		JPanel panel = guiHelper.createClearJPanel(2,1);
		panel.add(guiHelper.createWhiteLabel("Next Blinds"));
		JPanel content = guiHelper.createClearJPanel(2);
		content.add(guiHelper.createWhiteLabel("Small :"));
		nextSmallBlind = guiHelper.createValueLabel("");
		content.add(nextSmallBlind);
		content.add(guiHelper.createWhiteLabel("Big :"));
		nextBigBlind = guiHelper.createValueLabel("");
		content.add(nextBigBlind);

		panel.add(content);
		return panel;
	}

	private JPanel createCurrentBlinds() {
		JPanel panel = guiHelper.createClearJPanel(2,1); 
		panel.add(guiHelper.createWhiteLabel("Current Blinds"));
		JPanel content = guiHelper.createClearJPanel(0);
		content.add(guiHelper.createWhiteLabel("Small :"));
		smallBlind = guiHelper.createValueLabel("");
		content.add(smallBlind);

		content.add(guiHelper.createWhiteLabel("Big :"));
		bigBlind =  guiHelper.createValueLabel("");
		content.add(bigBlind);
		panel.add(content);
		return panel;
	}

	private void createPanel(ChipColourController colourPanel) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setOpaque(false);
		JPanel currentBlinds = createCurrentBlinds();
		JPanel nextBlinds = createNextBlinds();
		JPanel top = guiHelper.createClearJPanel(2,1);
		JPanel bottom = guiHelper.createClearJPanel(2);
		top.add(currentBlinds);
		top.add(nextBlinds);
		bottom.add(colourPanel.getChipColourPanel());
		this.add(top);
		this.add(bottom);
	}
}
