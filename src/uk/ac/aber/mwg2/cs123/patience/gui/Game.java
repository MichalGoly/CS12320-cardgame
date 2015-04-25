package uk.ac.aber.mwg2.cs123.patience.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 * Game class is the starting point of the application. It initializes
 * other components and puts them into the correct positions using 
 * BorderLayout.
 * 
 * @author mwg2
 * @since 26 March 2015
 */
public class Game extends JFrame {
	
	private ScorePanel scorePanel;
	private Table table;	
	private ButtonPanel buttonPanel;
	
	private final String WINDOW_TITLE = "Patience Game";
	
	/**
	 * Initializes the JFrame and its components
	 */
	public Game() {
		initComponents();
		initFrame();
	}
	
	private void initComponents() {
		scorePanel = new ScorePanel();
		add(scorePanel, BorderLayout.NORTH);
		table = new Table(scorePanel, this);
		add(new JScrollPane(table), BorderLayout.CENTER);
		buttonPanel = new ButtonPanel(this, table, scorePanel);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void initFrame() {
		pack();
		setTitle(WINDOW_TITLE);
		setLocationRelativeTo(null);
		addWindowListener(new WindowHandler());
	}
	
	// Starts the game and chooses appropriate look and feel depending
	// on user's operating system
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				System.exit(-1);
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new Game();
				frame.setVisible(true);
			}
		});
	}
	
	/*
	 * Makes sure that the GameOverDialog is being displayed when the windowClosing
	 * event is being dispatched.
	 */
	private class WindowHandler extends WindowAdapter {
		
		private GameOverDialog gameOverDialog;
		
		@Override
		public void windowClosing(WindowEvent e) {
			// display game over window
			gameOverDialog = new GameOverDialog(scorePanel.getCurrentScore());
			gameOverDialog.setVisible(true);
		}
	}
}
