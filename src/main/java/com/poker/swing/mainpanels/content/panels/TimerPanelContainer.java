package com.poker.swing.mainpanels.content.panels;

import java.awt.FlowLayout;
import java.io.Serial;

import javax.swing.JPanel;

import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.controllers.timers.RoundTimeController;
import com.poker.swing.mainpanels.content.controllers.timers.TotalTimeController;

/**
 * @author Darren
 * Controller which manages both the round and total time
 */
public class TimerPanelContainer extends JPanel{
   @Serial
   private static final long serialVersionUID = 1L;

   /**
    * Creates A new JPanel which contains both the RoundTimePanel and TotalTimePanel
    */
   public TimerPanelContainer() {
      RoundTimeController roundTimeController = SwingPokerApp.getRoundTime();
      TotalTimeController totalTimeController = SwingPokerApp.getTotalTime();
      this.setLayout(new FlowLayout());
      this.setOpaque(false);
      this.add(totalTimeController.getPanel());
      this.add(roundTimeController.getPanel());    
   }

   
}
