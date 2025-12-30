package com.poker.swing.utilityclasses.gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

class RoundJTextField extends JTextField {
   private static final long serialVersionUID = 1L;
   private final int roundedDegree = 20;
   private static final  String FONT = "Ariel";

   RoundJTextField(String value) {
      super(value);
      setCommonProperties();
   }

   RoundJTextField() {
      super();
      setCommonProperties();
   }

   private void setCommonProperties() {
      this.setBackground(Color.GRAY);
      this.setForeground(Color.ORANGE);
      this.setFont(new Font(FONT, Font.PLAIN, 14));
      setOpaque(false); 
   }



   @Override
   protected void paintComponent(Graphics g) {
      g.setColor(getBackground());
      g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, roundedDegree, roundedDegree);
      super.paintComponent(g);
   }

   @Override
   protected void paintBorder(Graphics g) {
      g.setColor(getForeground());
      g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, roundedDegree, roundedDegree);
   }
}