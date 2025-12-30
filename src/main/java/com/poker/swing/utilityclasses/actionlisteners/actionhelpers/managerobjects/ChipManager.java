package com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects;

import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poker.logic.config.Chip;
import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.StatsPanel;
import com.poker.swing.utilityclasses.actionlisteners.ChipPairListener;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import lombok.Getter;

/**
 * @author Darren
 * Handler to handle management commands related to chip changes
 */
public class ChipManager extends ManagerObject{
    private List<Chip> chipPairs;
	private JTextField smallBlindsText;
	private JTextField bigBlindsText;
	private JTextField valueText;
	private JTextField numDealtText;
	@Getter
    private JLabel totalChips;
	@Getter
    private JLabel startingStack;
	private JComboBox<String> stratList;
	private PokerGuiHelper guiHelper = new PokerGuiHelper();

	@Getter
    private Map<String, List<Component>> editableChipPair;

	@Override
	public void submit() {
		setNewBlinds();
		setChipValues();
		helper.affectConfigButtons(true);
		managerFrame.dispose();
	}

	private void setChipValues() {
		chipPairs.forEach(pair -> {
			String newValue = ((JTextField) editableChipPair.get(pair.getColor()).get(1)).getText();
			String newAmount = ((JTextField) editableChipPair.get(pair.getColor()).get(2)).getText();
			pair.setValue(newValue);
			pair.setNumDealt(Integer.parseInt(newAmount));
			SwingPokerApp.getColourPanel().upDate(this);
			StatsPanel instance = SwingPokerApp.getStatsController().getPanel();
			instance.updateUI();
		});
	}

	private void setNewBlinds() {
		PokerProperties props =  SwingPokerApp.getGame().getProperties();
		props.setCurrentBigBlind(Integer.parseInt(bigBlindsText.getText()));
		props.setCurrentSmallBlind(Integer.parseInt(smallBlindsText.getText()));
		props.setBlindsAggression((String) stratList.getSelectedItem());
		SwingPokerApp.getBlinds().upDate();
	}


	@Override
	public void createValues () {
		bigBlindsText.setText(String.valueOf(SwingPokerApp.getGame().getProperties().getCurrentBigBlind()));
		smallBlindsText.setText(String.valueOf(SwingPokerApp.getGame().getProperties().getCurrentSmallBlind()));
	}

	@Override
	public void showFrame(){
		helper.affectConfigButtons(false);
		managerFrame.setEnabled(true);
		managerFrame.setAlwaysOnTop(true);
		managerFrame.setVisible(true);
		managerFrame.pack();
	}

	private JPanel createChipConfigPanel() {
		JPanel tmp = guiHelper.createClearJPanel(3, 1);
		tmp.add(createChipTable());
		tmp.add(createChipStats());
		tmp.add(createBlindsPanel());
		return tmp;
	}

	private JPanel createChipStats() {
		JPanel tmp = guiHelper.createClearJPanel(1);
		tmp.add(guiHelper.createWhiteLabel("Total Chips in Game = "));
		PokerProperties stats = SwingPokerApp.getGame().getProperties();
		totalChips = guiHelper.createWhiteLabel(String.valueOf(stats.getStack()*stats.getPlayersRemaining()));
		tmp.add(totalChips);
		tmp.add(guiHelper.createWhiteLabel("Starting Stack = "));
		startingStack = guiHelper.createWhiteLabel(String.valueOf(stats.getStack()));
		tmp.add(startingStack);
		return tmp;
	}

	private JPanel createChipTable() {
		JPanel tmp = guiHelper.createClearJPanel(chipPairs.size()+1,4);
		tmp.add(guiHelper.createWhiteLabel("ChipColor"));
		tmp.add(guiHelper.createWhiteLabel("Value"));
		tmp.add(guiHelper.createWhiteLabel("Number Dealt"));
		tmp.add(guiHelper.createWhiteLabel("Total"));
		editableChipPair = createMap();
		editableChipPair.forEach((key,chipPair) -> chipPair.forEach(tmp::add));
		return tmp;
	}

	private Map<String, List<Component>> createMap() {
		Map<String, List<Component>> newEditableChipPair = new LinkedHashMap<>();
		chipPairs.forEach(pair -> {
			List<Component> values = new ArrayList<>();
			values.add(guiHelper.createWhiteLabel(pair.getColor()));
			valueText = guiHelper.createTextField(pair.getValue());
			valueText.getDocument().addDocumentListener(new ChipPairListener(this));
			values.add(valueText);
			numDealtText = guiHelper.createTextField(""+pair.getNumDealt());
			numDealtText.getDocument().addDocumentListener(new ChipPairListener(this));
			values.add(numDealtText);
			values.add(guiHelper.createWhiteLabel(Integer.toString(Integer.parseInt(valueText.getText()) * Integer.parseInt(numDealtText.getText()))));
			newEditableChipPair.put(pair.getColor(),values);	
		});
		return newEditableChipPair;
	}


	private JPanel createBlindsPanel() {
		JPanel tmp = guiHelper.createClearJPanel(1);
		tmp.add(guiHelper.createWhiteLabel("Small Blind = "));
		tmp.add(smallBlindsText);
		tmp.add(guiHelper.createWhiteLabel("Big Blind = "));
		tmp.add(bigBlindsText);
		tmp.add(createBlindsAggression());
		return tmp;
	}

	private JPanel createBlindsAggression() {
		JPanel tmp = guiHelper.createClearJPanel(1,2);
		tmp.add(guiHelper.createWhiteLabel("BlindsStrategy"));
		String[] aggressionStrategies = { "double", "5", "10", "15", "20","25","50" };
		stratList = guiHelper.createDropdown(aggressionStrategies);

		tmp.add(stratList);
		stratList.setSelectedIndex(0);
		return tmp;
	}

    @Override
	public void setupManager() {
		guiHelper = new PokerGuiHelper();
        JButton submit = guiHelper.createButton("Submit");
		managerFrame = new ManagerFrame();
		smallBlindsText = guiHelper.createTextField();
		bigBlindsText = guiHelper.createTextField();
		chipPairs = SwingPokerApp.getGame().getProperties().getChips();

		JPanel contentPanel = guiHelper.createClearJPanel(2,1);
		contentPanel.add(createChipConfigPanel());
		JPanel buttonPanel = guiHelper.createClearJPanel(1);
		buttonPanel.add(submit);
		contentPanel.add(buttonPanel);

		submit.addActionListener(e -> submit());
		managerFrame.add(contentPanel);
	}
}
