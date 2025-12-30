package com.poker.swing.utilityclasses.gui;


public enum ManagementOptions {
   
   //MANAGEMENTSCREENS
   PLAYER_MANAGEMENT("Opening player management screen"),
   TOURNAMENT_MANAGER("Opening tournament management screen"),
   CHIP_MANAGER("Opening Chip management screen"),
   PAYOUT_MANAGER("Opening pay out manager");
   
   private static final String LOGMESSAGE = "";
   ManagementOptions(String logMessage) {
   }
   
   /**
    * Gets the log message associated with this Option
    * @return
    */
   public String getLogMessage() {
      return LOGMESSAGE;
   }
}
