package com.poker.swing.utilityclasses.gui;


public enum ActionOptions {
   
   //GAME ACTIONS
   START_GAME("Attempting to start the game"),
   PAUSE_GAME("Attempting to pause the game"),
   RESUME_GAME("Attempting to resume the game"),
   END_GAME("Attempting to manually end the game"),
   ELIMINATE_PLAYER("Trying to eliminate a player from the game"),
   ADD_REBUY("Attempting to add a player rebuy");
   
   private static final String LOGMESSAGE = "";
   ActionOptions(String logMessage) {
   }
   
   /**
    * Gets the log message associated with this action
    * @return
    */
   public String getLogMessage() {
      return LOGMESSAGE;
   }

}
