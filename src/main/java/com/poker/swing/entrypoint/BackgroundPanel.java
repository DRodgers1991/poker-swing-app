package com.poker.swing.entrypoint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.poker.swing.utilityclasses.gui.PokerGuiHelper;
import com.poker.swing.utilityclasses.gui.ScreenDimensionHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Darren Class initialises and creates the UI
 */
@Slf4j
public class BackgroundPanel extends JPanel implements WindowListener {
	@Serial
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JLabel backGroundFrame;
	private final PokerGuiHelper guiHelper = new PokerGuiHelper();

	/**
	 * Begins the Creation of the GUI and initialisation of content in that order
	 */
	public void launch() {
		log.info("Creating GUI components");
		SwingPokerApp.getLeftContent().initalisePanel();
		prepareGUI();
		log.info("Creating Inner Panels");
		createContent();
		log.info("GUI Launched successfully");
	}

	private void prepareGUI() {
		mainFrame = createMainFrame();
		mainFrame.addWindowListener(this);
		backGroundFrame = guiHelper.setBackground(new ImageIcon(ScreenDimensionHelper.scaleImage(ScreenDimensionHelper.calcWidth(), ScreenDimensionHelper.calcHeight(),
				System.getProperty("configDir") + "/Images/bg.jpeg")));
		backGroundFrame.setLayout(new BorderLayout());
		mainFrame.add(backGroundFrame);
		ImageIcon icon = new ImageIcon(System.getProperty("configDir") + "/Images/House.jpeg");
		mainFrame.setIconImage(icon.getImage());
	}

	private JFrame createMainFrame() {
		final String appName = "Poker Manager";
		JFrame frame = new JFrame(appName);
		frame.setTitle(appName);
		int width = ScreenDimensionHelper.calcWidth();
		int height = ScreenDimensionHelper.calcHeight();
		frame.setSize(width, height);
		frame.setMaximumSize(new Dimension(width, height));
		return frame;
	}

	private void createContent() {
		log.info("Creating Content ...");
		this.setLayout(new GridLayout(1, 1));
		this.setOpaque(false);
		setupMainContentArea(this);
		log.info("All components created making visible");
		backGroundFrame.add(this);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void setupMainContentArea(JPanel contentFrame) {
		JPanel mainArea = guiHelper.createClearJPanel(1, 2);
		mainArea.add(SwingPokerApp.getLeftContent().getPanel());
		mainArea.add(SwingPokerApp.getRightContent().getPanel());
		contentFrame.add(mainArea);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Main Window Created");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		log.info("Attempting Close");
		mainFrame.dispose();
		System.exit(ALLBITS);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.debug("Window IconFied");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		log.debug("Window DeIconFied");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.debug("Window Activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		log.debug("Window Deactivated");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.debug("Window Closed");
		SwingUtilities.invokeLater(() -> mainFrame.dispose());
	}

}
