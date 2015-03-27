package uk.ac.aber.mwg2.cs123.patience;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
		table = new Table();
		add(table, BorderLayout.CENTER);
		buttonPanel = new ButtonPanel(table);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void initFrame() {
		pack();
		setTitle(WINDOW_TITLE);
		setLocationRelativeTo(null);
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
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
