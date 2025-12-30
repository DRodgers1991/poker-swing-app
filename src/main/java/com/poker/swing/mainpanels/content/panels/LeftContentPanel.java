package com.poker.swing.mainpanels.content.panels;

import java.awt.Dimension;
import java.io.Serial;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.buttons.ButtonsPanel;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Content holder for the left side of the UI
 */
public class LeftContentPanel extends JPanel{

	@Serial
	private static final long serialVersionUID = 1L;
	private final PokerGuiHelper guiHelper;
	@Getter
    private ButtonsPanel buttonPanel;

	/**
	 * Initialise the Panel
	 */
	public LeftContentPanel() {
		guiHelper = new PokerGuiHelper();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		setUpPanel();
	}

	private void setUpPanel() {
		JPanel top = guiHelper.createClearJPanel(1,1);
		top.setMaximumSize(new Dimension(Integer.MAX_VALUE,250));
		buttonPanel = new ButtonsPanel();
		top.add(buttonPanel);

		JPanel bottom = guiHelper.createClearJPanel(1,1);
		bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
		bottom.add(SwingPokerApp.getClientResponse().getPanel());
		this.add(top);
		this.add(bottom);    
	}
}
