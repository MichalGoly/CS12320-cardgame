package uk.ac.aber.mwg2.cs123.patience;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class Game extends JFrame {
	
	private ScorePanel scorePanel;
	private Table table;	
	private ButtonPanel buttonPanel;
	
	private final String WINDOW_TITLE = "Patience Game";
	
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
	
	private class WindowHandler extends WindowAdapter {
		
		@Override
		public void windowClosing(WindowEvent e) {
			// display game over window
			new GameOverDialog(scorePanel.getCurrentScore()).setVisible(true);
		}
	}
}
