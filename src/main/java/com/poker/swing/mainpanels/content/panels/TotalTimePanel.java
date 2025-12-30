package com.poker.swing.mainpanels.content.panels;

import java.awt.FlowLayout;
import java.io.Serial;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.poker.swing.mainpanels.content.controllers.timers.TotalTimeController;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Panel to hold the Total Time information
 */
public class TotalTimePanel extends JPanel{
	@Serial
	private static final long serialVersionUID = -238974038069109445L;
    @Getter
    private final JLabel timeLabel;
	private final transient PokerGuiHelper guiHelper;
    @Getter
    private final JLabel startedLabel;

	/**
	 * Initialise the Panel
	 * @param totalTimeController
	 */
	public TotalTimePanel(TotalTimeController totalTimeController) {
		guiHelper = new PokerGuiHelper();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setOpaque(false);
        String totalTimeGone = totalTimeController.getTimeGone();
		timeLabel = guiHelper.createValueLabel(totalTimeGone);
        String timeStartedToString = totalTimeController.getTimeStarted();
		startedLabel = guiHelper.createValueLabel(timeStartedToString);    
		this.add(startedLabel);
		this.add(createPair("Total Time In Game : ", timeLabel));
		this.add(createPair("Time game started : ", startedLabel));
	}

	private JPanel createPair(String string, JLabel label) {
		JPanel pair = guiHelper.createClearJPanel(1);
		pair.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pair.add(guiHelper.createWhiteLabel(string));
		pair.add(label);
		return pair;
	}

}
