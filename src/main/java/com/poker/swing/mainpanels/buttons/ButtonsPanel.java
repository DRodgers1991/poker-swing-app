package com.poker.swing.mainpanels.buttons;

import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.poker.swing.utilityclasses.actionlisteners.ActionListenerController;
import com.poker.swing.utilityclasses.gui.ActionOptions;
import com.poker.swing.utilityclasses.gui.ManagementOptions;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Panel to hold the admin and game controller buttons
 */
public class ButtonsPanel extends JPanel{
	@Serial
	private static final long serialVersionUID = -8884448925047682459L;
	private final transient List<JButton> buttonsToDisable;
	private JButton playerManager;
	private JButton tourneyManager;
	private JButton payoutManager;
	private JButton chipManager;
	private transient ActionListenerController controller;
	@Getter
    private JButton startGame;
	private final PokerGuiHelper guiHelper;
	@Getter
    private JButton pauseGame;
	private JButton resumeGame;
	@Getter
    private JButton endGame;
	private static final String SOURCE = "Local";
	@Getter
    private JButton eliminate;
	@Getter
    private JButton addReBuy;

	/**
	 * Creates a JPanel containing two inner JPanels
	 * the left containing the Admin Commands 
	 * the right containing Game Actions start, pause etc ...
	 */
	public ButtonsPanel() {
		guiHelper = new PokerGuiHelper();
		this.setOpaque(false);
		this.setLayout(new GridLayout(1,1));
		buttonsToDisable = new ArrayList<>();
		assignButtons();
		addButtonstoList();
		addActionListeners();
		setDefaultEnabled();
		this.add(createTopPanel());
	}



	private JPanel createTopPanel() {
		JPanel topPanel = guiHelper.createClearJPanel(1, 2);
		JPanel tmp = guiHelper.createClearJPanel(1);
		tmp.add(guiHelper.createTitle("Game Management", SwingConstants.CENTER));
		tmp.add(playerManager);
		tmp.add(tourneyManager);
		tmp.add(payoutManager);
		tmp.add(chipManager);
		topPanel.add(tmp);
		tmp = guiHelper.createClearJPanel(1);
		tmp.add(guiHelper.createTitle("Game Actions", SwingConstants.CENTER));
		tmp.add(startGame);
		tmp.add(pauseGame);
		tmp.add(resumeGame);
		tmp.add(endGame);
		tmp.add(eliminate);
		tmp.add(addReBuy);
		topPanel.add(tmp);
		return topPanel;
	}
	private void assignButtons() {
		playerManager = guiHelper.createButton("Player Management");
		tourneyManager = guiHelper.createButton("Tournament Timing");
		payoutManager = guiHelper.createButton("Payout Percentages");
		chipManager = guiHelper.createButton("Chip Configurations");

		startGame = guiHelper.createButton("Start Game");

		pauseGame = guiHelper.createButton("Pause Game");
		resumeGame = guiHelper.createButton("Resume Game");
		endGame = guiHelper.createButton("End Game");

		eliminate = guiHelper.createButton("EliminatePlayer");
		addReBuy = guiHelper.createButton("Add ReBuy");

	}

	private void setDefaultEnabled() {
		resumeGame.setEnabled(false);
		pauseGame.setEnabled(false);
		endGame.setEnabled(false);
		eliminate.setEnabled(false);
		addReBuy.setEnabled(false);
	}

	private void addButtonstoList() {   
		buttonsToDisable.add(tourneyManager);
		buttonsToDisable.add(playerManager);
		buttonsToDisable.add(payoutManager);
		buttonsToDisable.add(chipManager);
	}

	private void addActionListeners() {
		controller = new ActionListenerController();
		tourneyManager.addActionListener(al -> controller.execute(ManagementOptions.TOURNAMENT_MANAGER, SOURCE));
		playerManager.addActionListener(al -> controller.execute(ManagementOptions.PLAYER_MANAGEMENT, SOURCE));
		chipManager.addActionListener(al ->controller.execute(ManagementOptions.CHIP_MANAGER, SOURCE));
		payoutManager.addActionListener(al -> controller.execute(ManagementOptions.PAYOUT_MANAGER, SOURCE));

		eliminate.addActionListener(al -> controller.execute(ActionOptions.ELIMINATE_PLAYER, SOURCE));
		addReBuy.addActionListener(al -> controller.execute(ActionOptions.ADD_REBUY, SOURCE));
		startGame.addActionListener(al -> controller.execute(ActionOptions.START_GAME, SOURCE));
		pauseGame.addActionListener(al -> controller.execute(ActionOptions.PAUSE_GAME, SOURCE));
		resumeGame.addActionListener(al -> controller.execute(ActionOptions.RESUME_GAME, SOURCE));
		endGame.addActionListener(al -> controller.execute(ActionOptions.END_GAME, SOURCE));
	}

	JButton getResumeGame() {
		return resumeGame;
	}

    List<JButton> getButtonsToDisable() {
		return buttonsToDisable;
	}
}
