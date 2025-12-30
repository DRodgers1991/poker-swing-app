package com.poker.swing.utilityclasses.gui;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PokerGuiHelper {
   private JPanel panel;
   private JLabel label;
   private JButton button;
   private static final String FONT = "Ariel";

   /**
    * Creates a new JPanel with a specific alignment
    * left = 0
    * center = 1
    * right = 2
    * @param alignment
    * @return
    */
   public JPanel createClearJPanel(int alignment) {
      panel = new JPanel(new FlowLayout(alignment));
      panel.setOpaque(false);
      return panel;
   }

   /**
    * Creates a JPanel in a GridLayout
    * @param rowsDown
    * @param rowsAcross
    * @return
    */
   public JPanel createClearJPanel(int rowsDown, int rowsAcross) {
      panel = new JPanel(new GridLayout(rowsDown, rowsAcross));
      panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
      panel.setOpaque(false);
      return panel;
   }

   /**
    * Creates a JLabel with White Text, Ariel font size 20
    * @param txt
    * @return
    */
   public  JLabel createWhiteLabel(String txt) {
      label = new JLabel(txt, SwingConstants.CENTER);
      label.setForeground(Color.WHITE);
      label.setFont(new Font(FONT, Font.BOLD, 20));
      return label;
   }

   /**
    * Creates a JLabel with Orange Text, Ariel font size 20
    * @param txt
    * @return
    */
   public JLabel createValueLabel(String txt) {
      label = new JLabel(txt, SwingConstants.CENTER);
      label.setForeground(Color.ORANGE);
      label.setFont(new Font(FONT, Font.BOLD, 20));
      return label;
   }

   /**
    * Creates a larger White Label and aligns it with values
    * left = 0
    * center = 1
    * right = 2
    * Arial Font Size 20
    *
    * @param title
    * @param alignment
    * @return
    */
   public JLabel createTitle(String title, int alignment) {
      label = new JLabel(title, alignment);
      label.setVerticalAlignment(JLabel.TOP);
      Font font = new Font(FONT, Font.BOLD, 30);
      label.setFont(font);
      label.setForeground(Color.WHITE);
      return label;
   }

   /**
    * Creates a JLabel used for a background with the passed Image
    * @param imageIcon
    * @return
    */
   public  JLabel setBackground(ImageIcon imageIcon) {
      label = new JLabel(imageIcon);
      return label;
   }

   public  JButton createButton(String string) {
      button = new OvalButton(string);
      return button;
   }


   public  JTextField createTextField() {
      return new RoundJTextField();
   }


   public  JTextField createTextField(String value) {
      return new RoundJTextField(value);
   }


   /**
    * Creates a new Grey dropdown with Orange Text
    * @param values
    * @return
    */
   public  JComboBox<String> createDropdown(String[] values) {
      JComboBox<String> comboBox = new JComboBox<String>(values);
      comboBox.setEditable(true);
      comboBox.setFont(new Font(FONT, Font.PLAIN, 14));
      comboBox.getEditor().getEditorComponent().setBackground(Color.GRAY);
      comboBox.getEditor().getEditorComponent().setForeground(Color.ORANGE);
      return comboBox;
   }
}
