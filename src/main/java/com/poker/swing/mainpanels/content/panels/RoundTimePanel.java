package com.poker.swing.mainpanels.content.panels;

import java.awt.Component;
import java.io.Serial;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Panel to handle the Round Timings
 */
public class RoundTimePanel extends JPanel{
	@Serial
	private static final long serialVersionUID = 8855227371983854706L;

    @Getter
    private final JLabel blindsUp;
	@Getter
    private final JLabel timeStarted;
	@Getter
    private final JLabel roundNum;
	@Getter
    private final JLabel nextBreak;
	private final transient PokerGuiHelper guiHelper;

	/**
	 * Initialise the panel
	 */
	public RoundTimePanel(){
		guiHelper = new PokerGuiHelper();
		blindsUp = guiHelper.createValueLabel("");
		timeStarted = guiHelper.createValueLabel("");
		roundNum = guiHelper.createValueLabel("");
		nextBreak = guiHelper.createValueLabel("");
        JPanel mainPanel = guiHelper.createClearJPanel(2);
		JPanel outer = guiHelper.createClearJPanel(1);
		JPanel top = guiHelper.createClearJPanel(1);
		JPanel bottom = guiHelper.createClearJPanel(1);
		outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
		mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		top.add(createPair("Time until blinds up : ",blindsUp));
		top.add(createPair("Round number : ",roundNum));
		bottom.add(createPair("Next Break : ",nextBreak));
		bottom.add(createPair("Time Round Started : ",timeStarted));
		outer.add(top);
		outer.add(bottom);
		mainPanel.add(outer);
		this.setOpaque(false);
		this.add(mainPanel);
	}

	private JPanel createPair(String string, JLabel label) {
		JPanel pair = guiHelper.createClearJPanel(1);
		pair.setAlignmentX(Component.LEFT_ALIGNMENT);
		pair.setLayout(new BoxLayout(pair, BoxLayout.X_AXIS));
		pair.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pair.add(guiHelper.createWhiteLabel(string));
		pair.add(label);
		return pair;
	}
}
