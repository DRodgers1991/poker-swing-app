package com.poker.swing.entrypoint;

import com.poker.logic.timers.AbstractPokerTimer;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.sql.Time;

@Slf4j
public class SwingPokerTimer extends AbstractPokerTimer {

    @Override
    public void announceBreak(Time resumeTime) {
        log.info("Break Beginning will resume at {}",resumeTime);
        JOptionPane.showMessageDialog(null, "Currently on a break Game Wil Resume at " + resumeTime);
    }

    @Override
    public void announceEndOfRound() {
        log.info("Round Ending ");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(System.getProperty("configDir") + "/sounds/Siren.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            Thread.sleep(5000);
            clip.start();
            clip.close();
        } catch (Exception ex) {
            log.error("Error with playing sound, ", ex);
        }
    }
}
