package com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.StatsPanel;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Management class to handle player management
 */
@Slf4j
public class PlayerManager extends ManagerObject{
	private JButton submit;
	private JTextField numPlayersTextBox;
	private JPanel contentPanel;

	@Override
	public void submit() {
		try {
			log.info("Setting Player number to {}",numPlayersTextBox.getText());
			SwingPokerApp.getGame().getProperties().setPlayersRemaining(Integer.parseInt(numPlayersTextBox.getText()));
			SwingPokerApp.getGame().getProperties().setInitialPlayers(Integer.parseInt(numPlayersTextBox.getText()));
			StatsPanel instance = SwingPokerApp.getStatsController().getPanel();
			instance.getPlayersStartedLabel().setText(SwingPokerApp.getGame().getProperties().getInitialPlayers()+"");
			instance.getPlayerRemainingLabel().setText(SwingPokerApp.getGame().getProperties().getPlayersRemaining()+"");
			instance.getAverageStackLabel().setText(String.valueOf(SwingPokerApp.getGame().getProperties().calcAverageStack()));
			instance.getTotalChips().setText(SwingPokerApp.getGame().getProperties().getTotalChipsInGame()+"");
			instance.getTotalPrize().setText("�"+SwingPokerApp.getGame().calcTotalPrizeMoney());
		} catch(NumberFormatException nfe) {
			log.error("Incorrect Number format expecting int received => {}",numPlayersTextBox.getText());
			JOptionPane.showMessageDialog(null, "Invalid Number format entered please try again");
		}
		helper.affectConfigButtons(true);
		managerFrame.dispose();
	}



	@Override
	public void createValues () {
		numPlayersTextBox.setText(String.valueOf(SwingPokerApp.getGame().getProperties().getInitialPlayers()));
	}

	@Override
	public void showFrame(){
		helper.affectConfigButtons(false); 

		numPlayersTextBox.setPreferredSize(new Dimension(100,50));
		contentPanel.add(numPlayersTextBox);
		contentPanel.add(submit);

		managerFrame.add(contentPanel);
		managerFrame.setEnabled(true);
		managerFrame.setAlwaysOnTop(true);
		managerFrame.pack();
		managerFrame.setVisible(true);

	}



	@Override
	public void setupManager() {
		managerFrame = new ManagerFrame();
		PokerGuiHelper guiHelper = new PokerGuiHelper();
		contentPanel = guiHelper.createClearJPanel(1);
		contentPanel.add(guiHelper.createWhiteLabel("Starting Players => "));
		numPlayersTextBox = guiHelper.createTextField();
		submit = guiHelper.createButton("Submit");
		submit.addActionListener(al -> submit());
	}

}
