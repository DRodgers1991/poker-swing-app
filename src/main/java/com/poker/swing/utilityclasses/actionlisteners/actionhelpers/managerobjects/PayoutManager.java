package com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.poker.logic.config.PokerProperties;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;

/**
 * @author Darren
 * Manager class to handle changes to the end of game payout's
 */
public class PayoutManager extends ManagerObject{
	private JButton submit;
	private JSlider payoutsSlider;
	private int numPayouts;
	private JLabel totalPercentLabel;
	private List<JSlider> sliderList;
	private int totalPercentage;
	private PokerGuiHelper guiHelper;

	@Override
	public void createValues () {
		guiHelper = new PokerGuiHelper();
		reEvaluatePercentage();
		numPayouts = SwingPokerApp.getGame().getProperties().getPayoutPlaces();
		payoutsSlider.setValue(numPayouts);
		payoutsSlider.setMaximum(setMaxPlaces());
		payoutsSlider.setSnapToTicks(true);
		payoutsSlider.setPaintLabels(true);
	}

	@Override
	public void showFrame(){
		helper.affectConfigButtons(false);
		managerFrame.setEnabled(true);
		managerFrame.setAlwaysOnTop(true);
		managerFrame.setVisible(true);
		managerFrame.pack();
		totalPercentLabel.setText(Integer.toString(totalPercentage));
	}

	private JPanel createPayoutSliderPanel() {
		JPanel tmp = guiHelper.createClearJPanel(2, 1);
		tmp.add(numPayoutsPanel());   
		tmp.add(createSliderPanel());
		return tmp;
	}


	private Component createSliderPanel() {
		int totalSliders = SwingPokerApp.getGame().getProperties().getPlayersRemaining();
		JPanel tmp = guiHelper.createClearJPanel(totalSliders+1, 2);
		totalPercentage = 0; 
		for(int place = 1;place<=totalSliders;place++) {
            createSliders(tmp, place, place <= numPayouts);
		}
		tmp.add(guiHelper.createWhiteLabel(""));
		tmp.add(submit);

		return tmp;
	}

	private void createSliders(JPanel tmp, int place,boolean valueCheck) {
		JSlider slider;
		slider = createSlider(place, valueCheck);
		sliderList.add(slider);
		tmp.add(guiHelper.createWhiteLabel(place+" Percentage = "));
		tmp.add(slider);
		slider.setEnabled(valueCheck);
		totalPercentage = totalPercentage + slider.getValue();
	}

	private JSlider createSlider(int place, boolean checkForValue) {
		JSlider slider = new JSlider();
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true); 
		slider.setForeground(Color.ORANGE);
		if(checkForValue){
			slider.setValue(SwingPokerApp.getGame().getProperties().getPayoutValues().get(place-1));
		} else {
			slider.setValue(0);
		}
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(cl -> reEvaluatePercentage());
		return slider;
	}

	private Component numPayoutsPanel() {
		JPanel tmp = guiHelper.createClearJPanel(2, 2);
		payoutsSlider = new JSlider();
		payoutsSlider.setMajorTickSpacing(1);
		payoutsSlider.setPaintTicks(true); 
		payoutsSlider.setForeground(Color.ORANGE);
		numPayouts = SwingPokerApp.getGame().getProperties().getPayoutPlaces();

		payoutsSlider.addChangeListener(cl -> changePayoutPlaces());
		tmp.add(guiHelper.createWhiteLabel("Num Of Payouts = "));
		tmp.add(payoutsSlider);
		tmp.add(guiHelper.createWhiteLabel("Total Percentage"));
		tmp.add(totalPercentLabel);

		return tmp;
	}

	private int setMaxPlaces() {
		PokerProperties properties = SwingPokerApp.getGame().getProperties();
		return Math.max(Math.max(properties.getInitialPlayers(), properties.getPlayersRemaining()), 9);
	}

	private void changePayoutPlaces() {
		sliderList.forEach(slider -> slider.setEnabled(false));
		System.out.println("Slider Size "+sliderList.size());
		int newValue = payoutsSlider.getValue();
		System.out.println("new Value "+newValue);
		for(int x = 0; x <newValue; x++) {
			System.out.println("x "+x);
			//If only one payout place, payout must be 100%
			if(x == 0 && newValue== 1) {
				sliderList.get(x).setValue(100);
			}
			sliderList.get(x).setEnabled(true);
		}
		reEvaluatePercentage();
	}

	private void reEvaluatePercentage() {
		totalPercentage = 0;

		sliderList.stream().filter(Component::isEnabled).forEach(filterResult ->
		totalPercentage = totalPercentage + filterResult.getValue()
				);  

		totalPercentLabel.setText(Integer.toString(totalPercentage));
		int zeroValues = sliderList.stream().filter(slider -> slider.isEnabled() && slider.getValue() ==0).toArray().length;
		if(totalPercentage == 100 && zeroValues == 0) {
			totalPercentLabel.setForeground(Color.GREEN);
			submit.setEnabled(true);
		} else {
			totalPercentLabel.setForeground(Color.RED);
			submit.setEnabled(false);
		}
	}

	@Override
	public void setupManager() {
		guiHelper = new PokerGuiHelper();
		submit = guiHelper.createButton("Submit");
		sliderList = new ArrayList<>();
		managerFrame = new ManagerFrame();
		totalPercentLabel = guiHelper.createWhiteLabel("");
		JPanel contentPanel = guiHelper.createClearJPanel(1,1);
		contentPanel.add(createPayoutSliderPanel());
		submit.addActionListener(e -> submit());
		managerFrame.add(contentPanel);

	}

	@Override
	public void submit() {
		SwingPokerApp.getGame().getProperties().setPayoutPlaces(payoutsSlider.getValue());
		List<Integer> newPayoutValues = new ArrayList<>();
		sliderList.stream().filter(Component::isEnabled).forEach(result -> newPayoutValues.add(result.getValue()));
		SwingPokerApp.getGame().getProperties().setPayoutValues(newPayoutValues);
		helper.affectConfigButtons(true);
		managerFrame.dispose();
	}
}
