package com.poker.swing.entrypoint;

import java.util.Timer;

import com.poker.logic.game.PokerGame;
import com.poker.logic.config.PokerProperties;
import com.poker.swing.mainpanels.content.controllers.BlindsController;
import com.poker.swing.mainpanels.content.controllers.ChipColourController;
import com.poker.swing.mainpanels.content.controllers.ClientResponseController;
import com.poker.swing.mainpanels.content.controllers.LeftContentController;
import com.poker.swing.mainpanels.content.controllers.RightContentController;
import com.poker.swing.mainpanels.content.controllers.StatsController;
import com.poker.swing.mainpanels.content.controllers.timers.RoundTimeController;
import com.poker.swing.mainpanels.content.controllers.timers.TotalTimeController;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;


/**
 * @author Darren
 * Main Entry point for application
 *
 */
@Slf4j
public class SwingPokerApp {
	@Getter
    private static PokerGame game;
	@Getter
    private static RoundTimeController roundTime;
	@Getter
    private static TotalTimeController totalTime;
	@Getter
    private static LeftContentController leftContent;
	@Getter
    private static RightContentController rightContent;
	@Getter
    private static ClientResponseController clientResponse;
	@Getter
    private static BlindsController blinds;
	@Getter
    private static ChipColourController colourPanel;
	@Getter
    private static StatsController statsController;
	private static Timer roundTimer;
	private static Timer totalTimer;
	private static int chipNumbers;


	private SwingPokerApp() {}
	/**
	 * Entry point of the application on start up initiation sequence is as follows
	 *  Create GUI components and once intialised make visible
	 *  Create the Listener for the Android client application
	 *  Start the Client listener thread
	 * @param args standard vm arguments
	 */
	public static void main(String[] args) {		
		log.info("Initialising Poker Manager ... ");
		String[] buttons = { "4", "5",};
		chipNumbers = JOptionPane.showOptionDialog(null, "How many chipColors do you want to use ?", "Chip Select",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[1]);


		if(chipNumbers >= 0) {
			chipNumbers = chipNumbers == 0 ? 4 : 5;
			log.info("chips Choice -> {}", chipNumbers);
			game = new PokerGame(chipNumbers, new SwingPokerTimer());
			PokerProperties props = game.getProperties();
			createSwingControllers(props);
			blinds = new BlindsController(colourPanel);
			rightContent = new RightContentController();
			leftContent = new LeftContentController();
			new BackgroundPanel().launch();
		} else {
			log.warn("Invalid selection for chip or cancel selected");
		}
	}

	private static void createSwingControllers(PokerProperties props) {
		statsController = new StatsController();
		clientResponse = new ClientResponseController();
		colourPanel = new ChipColourController(props.getChips());
		roundTime = new RoundTimeController();
		totalTime = new TotalTimeController();
	}

    public static void updateUI(){
		roundTime.upDate();
		totalTime.upDate();
		blinds.upDate();
		statsController.update();
	}

	public static void startTimers() {
		roundTimer = new Timer();
		roundTimer.scheduleAtFixedRate(SwingPokerApp.getRoundTime(), 0L,500L);
		totalTimer = new Timer();
		totalTimer.scheduleAtFixedRate(SwingPokerApp.getTotalTime(), 0L, 500L);
	}

	public static void cancelTimers() {
		roundTimer.cancel();
		totalTimer.cancel();
	}
	public static void reset() {
		cancelTimers();
		game = new PokerGame(chipNumbers, new SwingPokerTimer());
		createSwingControllers(game.getProperties());
	}
}