package com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Manager Class to handle the Timings and other tournament options
 */
@Slf4j
public class TourneyManager extends ManagerObject{
	private JPanel contentPanel;
	private JPanel topPanel;
	private JButton submit;
	private JTextField roundIncrementsText;
	private JTextField roundTimeText;
	private PokerGuiHelper guiHelper;

	@Override
	public void submit() {
		PokerProperties properties = SwingPokerApp.getGame().getProperties();
		try{
			int newTime = Integer.parseInt(roundTimeText.getText());
			log.info("Attempting to set new Round Length of {} minutes",roundTimeText.getText());
			properties.setRoundTime(newTime);
		} catch (NumberFormatException nfe) {
			log.error("Error setting Round Time expected Integer got => {}",roundTimeText.getText());
			JOptionPane.showMessageDialog(null, "Invalid Number format entered please try again");
		}
		try{
			log.info("Attempting to set new Break Increments of {}", roundIncrementsText.getText() );
			properties.setBreakIncrements(Integer.parseInt(roundIncrementsText.getText()));
		} catch (NumberFormatException nfe) {
			log.error("Error setting Break Increments expected long got => {}", roundTimeText.getText());
			JOptionPane.showMessageDialog(null, "Invalid Number format entered please try again");
		}
		SwingPokerApp.getRoundTime().upDate();
		helper.affectConfigButtons(true);
		managerFrame.dispose();
	}

	@Override
	public void createValues () {
		roundIncrementsText.setText(String.valueOf(SwingPokerApp.getGame().getProperties().getBreakIncrements()));
		roundTimeText.setText(String.valueOf(SwingPokerApp.getGame().getProperties().getRoundTime()));
	}

	@Override
	public void showFrame(){
		helper.affectConfigButtons(false);     
		managerFrame.setVisible(true);
	}

	@Override
	public void setupManager() {
		guiHelper = new PokerGuiHelper();
		roundIncrementsText = guiHelper.createTextField();
		roundTimeText = guiHelper.createTextField();
		topPanel = createTopPanel();
		contentPanel = createContentPanel();
		setupManagerFrame();

		log.info("Tournament Manger Setup");
	}

	private void setupManagerFrame() {
		guiHelper = new PokerGuiHelper();
		managerFrame = new ManagerFrame();
		submit.addActionListener(al -> submit());
		managerFrame.add(contentPanel);
		managerFrame.setEnabled(true);
		managerFrame.setAlwaysOnTop(true);
		managerFrame.pack();
	}

	private JPanel createTopPanel() {
		JPanel panel = guiHelper.createClearJPanel(2,2);
		panel.add(guiHelper.createWhiteLabel("Round Increments =>"));

		panel.add(roundIncrementsText);
		panel.add(guiHelper.createWhiteLabel("Round Time =>"));
		panel.add(roundTimeText);
		return panel;
	}

	private JPanel createContentPanel() {
		JPanel panel = guiHelper.createClearJPanel(2,1);
		panel.add(topPanel);
		JPanel bottomPanel = guiHelper.createClearJPanel(1);

		submit = guiHelper.createButton("Submit");
		bottomPanel.add(submit);
		panel.add(bottomPanel);
		return panel;
	}
}
