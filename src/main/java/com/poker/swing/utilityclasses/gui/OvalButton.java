package com.poker.swing.utilityclasses.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

/**
 * OvalButton.java
 *
 */
class OvalButton extends JButton {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private final Color startColor = new Color(255, 61, 61);
   private final Color endColor = new Color(142, 0, 0);
   private final Color rollOverColor = new Color(50, 1, 84);
   private final Color rollOverColorEnd = new Color(50, 91, 84);
   private final Color pressedColor = new Color(204, 67, 0);
   private final int outerRoundRectSize = 30;
   private final int innerRoundRectSize = 28;
   private transient GradientPaint gp;

   /**
    * Constructor takes String argument
    * @param text
    */
   OvalButton(String text) {
      super();
      setText(text);
      setContentAreaFilled(false);
      setBorderPainted(false);
      setFont(new Font("Arial", Font.BOLD, 14));
      setForeground(Color.ORANGE);
      setFocusable(false);
      setRolloverEnabled(true);
   }

   /**
    * 
    */
   @Override
   public void paintComponent(Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      int h = getHeight();
      int w = getWidth();
      ButtonModel model = getModel();

      if (!model.isEnabled()) {
         gp = new GradientPaint(0, 0, new Color(1,1,1), 0, h, new Color(186, 186, 186),
               true);
      }else{
         if (model.isRollover()) {
            gp = new GradientPaint(0, 0, rollOverColor, 0, h, rollOverColorEnd,
                  true);

         } else {
            gp = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
         }
      }
      g2d.setPaint(gp);
      GradientPaint p1;
      GradientPaint p2;

      if (model.isPressed()) {
         gp = new GradientPaint(0, 0, pressedColor, 0, h, pressedColor, true);
         g2d.setPaint(gp);
         p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
               new Color(100, 100, 100));
         p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
               new Color(255, 255, 255, 100));
      } else {
         p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
               new Color(0, 0, 0));
         p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
               h - 3, new Color(0, 0, 0, 50));
         gp = new GradientPaint(0, 0, startColor, 0, h, endColor, true);
      }

      RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
            h - 1, outerRoundRectSize, outerRoundRectSize);
      Shape clip = g2d.getClip();
      g2d.clip(r2d);
      g2d.fillRect(0, 0, w, h);
      g2d.setClip(clip);
      g2d.setPaint(p1);
      g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
            outerRoundRectSize);
      g2d.setPaint(p2);
      g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
            innerRoundRectSize);
      g2d.dispose();

      super.paintComponent(g);
   }
}