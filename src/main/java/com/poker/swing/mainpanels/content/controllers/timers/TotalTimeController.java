package com.poker.swing.mainpanels.content.controllers.timers;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.TimeZone;
import java.util.TimerTask;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.TotalTimePanel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Controller for the overall timings of a poker game
 */
@Slf4j
public class TotalTimeController extends TimerTask {
	private final TotalTimePanel totalPanel;

	/**
	 * Initialise the controller 
	 */
	public TotalTimeController() {
		log.info("Initialising Total Timer ");
		totalPanel = new TotalTimePanel(this);
	}

	@Override
	public void run() {
		upDate();
	}

	public String getTimeGone() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
		if (!SwingPokerApp.getGame().getGameStateManager().isGameStarted()) {
			return LocalTime.MIDNIGHT.toString();
		} else {
			dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			return dateFormatter.format(SwingPokerApp.getGame().getTimer().getTotalGameTime(LocalTime.now()));
		}
	}

	public String getTimeStarted() {
		//On startUp sequence getControllers will be until both Timers are created,
		//hence the need for null check
		if (!SwingPokerApp.getGame().getGameStateManager().isGameStarted()) {
			return "00:00:00";
		} else {
			return SwingPokerApp.getGame().getTimer().getInitialStartTime().toString();
		}

	}

	/**
	 * Update the TotalTime UI
	 */
	public void upDate() {
		if(!"00:00:00".equals(getTimeGone())){
			totalPanel.getTimeLabel().setText(getTimeGone());
			totalPanel.getStartedLabel().setText(getTimeStarted());
		}
	}

	public Component getPanel() {
		return totalPanel;
	}

}
