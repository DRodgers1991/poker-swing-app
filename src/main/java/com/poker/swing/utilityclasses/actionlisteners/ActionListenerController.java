package com.poker.swing.utilityclasses.actionlisteners;

import java.util.HashMap;
import java.util.Map;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.ChipManager;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.ManagerObject;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.PayoutManager;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.PlayerManager;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.TourneyManager;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.AddRebuyCommand;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.CommandObject;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.EliminatePlayerCommand;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.EndGameCommand;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.PauseGameCommand;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.ResumeGameCommand;
import com.poker.swing.utilityclasses.actionlisteners.commandobjects.StartGameCommand;
import com.poker.swing.utilityclasses.gui.ActionOptions;
import com.poker.swing.utilityclasses.gui.ManagementOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Class to handle admin and management commands in a command pattern nature
 */
@Slf4j
public class ActionListenerController {
	private final Map<ActionOptions, CommandObject> actionCommands = new HashMap<>();
	private final Map<ManagementOptions, ManagerObject> managerCommands = new HashMap<>();

	/**
	 * Initialise the controllers and populate the two maps with the command objects
	 */
	public ActionListenerController() {
		log.info("ActionListenerController Created");
		setupMaps();
	}

	private void setupMaps() {
		setUpActionCommands();
		setUpManagerCommands();    
		logMaps();
	}

	private void setUpManagerCommands() {
		managerCommands.put(ManagementOptions.CHIP_MANAGER, new ChipManager());
		managerCommands.put(ManagementOptions.PLAYER_MANAGEMENT, new PlayerManager());     
		managerCommands.put(ManagementOptions.PAYOUT_MANAGER, new PayoutManager() );     
		managerCommands.put(ManagementOptions.TOURNAMENT_MANAGER, new TourneyManager());      
	}

	private void setUpActionCommands() {
		log.info("Creating Action Objects and adding to map");
		actionCommands.put(ActionOptions.START_GAME, new StartGameCommand());
		actionCommands.put(ActionOptions.PAUSE_GAME, new PauseGameCommand());
		actionCommands.put(ActionOptions.RESUME_GAME, new ResumeGameCommand());
		actionCommands.put(ActionOptions.END_GAME, new EndGameCommand());
		actionCommands.put(ActionOptions.ADD_REBUY, new AddRebuyCommand());
		actionCommands.put(ActionOptions.ELIMINATE_PLAYER, new EliminatePlayerCommand());
	}

	private void logMaps() {
		actionCommands.forEach((k,v) -> log.info("Action successfully created | {}", k));
	}


	/**
	 * Get the corresponding action from the action map and execute based on the message received
	 * @param action
	 * @param source
	 */
	public void execute(ActionOptions action, String source){
		log.info("Action Received {}, source {}, success {} ",action.getLogMessage(),source,actionCommands.get(action).execute());
		SwingPokerApp.getClientResponse().addToLog(System.currentTimeMillis(),action,source);
	}

	/**
	 * Get the corresponding management action from the action map and execute based on the message received
	 * @param action
	 * @param source
	 */
	public void execute(ManagementOptions action, String source){
		log.info("Configuration request received {}, source {}",action.getLogMessage(), source);
		ManagerObject manager = managerCommands.get(action);
		manager.createValues();
		manager.showFrame();
	}

}
