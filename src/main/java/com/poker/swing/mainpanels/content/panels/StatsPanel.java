package com.poker.swing.mainpanels.content.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.io.Serial;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Panel to hold current games statistics
 */
public class StatsPanel extends JPanel{
	@Serial
	private static final long serialVersionUID = 1L;
	@Getter
    private JLabel totalChips;
	@Getter
    private JLabel averageStackLabel;
	@Getter
    private JLabel playersStartedLabel;
	@Getter
    private JLabel reBuyLabel;
	@Getter
    private JLabel playerRemainingLabel;
	@Getter
    private JLabel totalPrize;
	private final PokerGuiHelper guiHelper;

	/**
	 * Initialise the panel
	 */
	public StatsPanel() {
		guiHelper = new PokerGuiHelper();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setOpaque(false);
		this.add(createPlayersLeft());
		this.add(createAverageStack());
	}

    private Component createAverageStack() {
		int stack = SwingPokerApp.getGame().getProperties().getStack();
		int players = SwingPokerApp.getGame().getProperties().getInitialPlayers();
		JPanel panel = guiHelper.createClearJPanel(1);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		totalChips = guiHelper.createValueLabel(Integer.toString(stack * players));
		panel.add(createPair("Total Chips in Game : ", totalChips));

		averageStackLabel = guiHelper.createValueLabel(Integer.toString(SwingPokerApp.getGame().getProperties().calcAverageStack()));
		panel.add(createPair("AverageStack : ",averageStackLabel));
		return panel;
	}

	private JPanel createPlayersLeft() {
		PokerProperties stats = SwingPokerApp.getGame().getProperties();
		JPanel panel = guiHelper.createClearJPanel(1);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		playersStartedLabel = guiHelper.createValueLabel(Integer.toString(stats.getInitialPlayers()));
		panel.add(createPair("Players Started : ", playersStartedLabel));

		reBuyLabel = guiHelper.createValueLabel(Integer.toString(stats.getRebuys()));
		panel.add(createPair("ReBuys : ", reBuyLabel));

		playerRemainingLabel = guiHelper.createValueLabel(Integer.toString(stats.getPlayersRemaining()));
		panel.add(createPair("Players Remaining : ", playerRemainingLabel));

		double prizePot = SwingPokerApp.getGame().calcTotalPrizeMoney();
		totalPrize = guiHelper.createValueLabel("�"+prizePot);
		panel.add(createPair("Total Prize fund : ", totalPrize));
		return panel;
	}

	private JPanel createPair(String string, JLabel label) {
		JPanel pair = guiHelper.createClearJPanel(1);
		pair.setLayout(new BoxLayout(pair, BoxLayout.X_AXIS));
		pair.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pair.add(guiHelper.createWhiteLabel(string));
		pair.add(label);
		return pair;
	}
}
