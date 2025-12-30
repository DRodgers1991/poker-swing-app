package com.poker.swing.mainpanels.content.panels;

import java.awt.Dimension;
import java.io.Serial;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;

/**
 * @author Darren
 * Content holder for the right side of the UI
 */
public class RightContentPanel extends JPanel{

	@Serial
	private static final long serialVersionUID = 1L;
	private final PokerGuiHelper guiHelper;

	/**
	 * Initialise the Panel
	 */
	public RightContentPanel() {
		guiHelper = new PokerGuiHelper();  
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		setUpPanel();
	}

	private void setUpPanel() {
		JPanel top = guiHelper.createClearJPanel(1,1);
		top.setMaximumSize(new Dimension(Integer.MAX_VALUE,150));
		top.add(new TimerPanelContainer());

		JPanel middle = guiHelper.createClearJPanel(1,1);
		middle.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
		middle.add(SwingPokerApp.getBlinds().getBlindsPanel());
		JPanel bottom = guiHelper.createClearJPanel(1,1);
		bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE,150));
		bottom.add(SwingPokerApp.getStatsController().getPanel());
		this.add(top);
		this.add(middle);
		this.add(bottom);    
	}
}
