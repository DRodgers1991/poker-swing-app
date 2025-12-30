package com.poker.swing.mainpanels.content.controllers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poker.logic.config.Chip;
import com.poker.swing.entrypoint.SwingPokerApp;
import com.poker.swing.mainpanels.content.panels.ChipColorPanel;
import com.poker.swing.utilityclasses.actionlisteners.actionhelpers.managerobjects.ChipManager;
import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import com.poker.swing.utilityclasses.gui.ScreenDimensionHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren
 * Controller to handle the chip configurations
 */
@Slf4j
public class ChipColourController {

	@Getter
    private final ChipColorPanel chipColourPanel;
	private final Map<String, JLabel> chipPairs = new LinkedHashMap<>();
	private final List<Chip> chipConfigs;
	private final PokerGuiHelper guiHelper;
	
	/**
	 * Constructor 
	 * @param chipConfigs
	 */
	public ChipColourController(List<Chip> chipConfigs){
		guiHelper = new PokerGuiHelper();
		int numOfColors = SwingPokerApp.getGame().getProperties().getNumOfChips();
		this.chipConfigs = chipConfigs;
		chipColourPanel = new ChipColorPanel(this,numOfColors);
	}


	/**
	 * Returns an amalgamated Chip panel 
	 * @param chipNumber
	 * @return
	 */
	public JPanel createChipPair(int chipNumber) {
		JPanel chipPair = guiHelper.createClearJPanel(2,1);
		String chipColor = chipConfigs.get(chipNumber-1).getColor();
		String value = chipConfigs.get(chipNumber-1).getValue();
		log.info("Chip : {}, Color : {}, Value : {}, Image File : {} ",chipNumber, chipColor,value,"Images \\"+chipColor+".jpeg");


		BufferedImage bufferedImage = ScreenDimensionHelper.scaleImage(90, 90, System.getProperty("configDir") + "/Images/" + chipColor + ".png");
		ImageIcon chipImage = new ImageIcon(bufferedImage);
		JLabel label = new JLabel(chipImage);
		chipPair.add(label);
		JLabel valueLabel = guiHelper.createValueLabel(value);
		chipPairs.put(chipColor, valueLabel);
		chipPair.add(valueLabel,BorderLayout.CENTER);

		return chipPair;

	}

	/**
	 * If chip values have been udated update the values in the ChipPairs Map
	 * and update the UI
	 * @param manager
	 */
	public void upDate(ChipManager manager){
		chipPairs.forEach((k,v)->{
			log.info("Updating chipColor : {}", k);
			String newValue = ((JTextField) manager.getEditableChipPair().get(k).get(1)).getText();
			log.info("New value = {}", newValue);
			v.setText(newValue);
		});
	}
}
