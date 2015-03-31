package uk.ac.aber.mwg2.cs123.patience;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * At the end of the game, typically when someone presses the 'x' exit button,
 * this JDialog will pop up. Player then can review his score versus the
 * previously recorded ones. Next he should insert his name to save his score
 * in the 'database'. In case the 'name' field is left blank, his score is lost.
 * 
 * @author mwg2
 * @since 31 March 2015
 */
public class GameOverDialog extends JDialog {
	
	// initialize them explicitly here to minimalize length of the class
	private JLabel youLabel = new JLabel("You");;	
	private JLabel highScoresLabel = new JLabel("High Scores");
	private JLabel nameLabel = new JLabel("Name:");
	private JTextField name = new JTextField();
	private JLabel scoreLabel = new JLabel("Score:");
	private JLabel score = new JLabel("0");
	private JTextArea highScores = new JTextArea();
	
	private List<String> highScoreList;
	private final String SCORE_FILE = "scores.txt";
	
	/**
	 * Creates the GameOverDialog which can be used to show and record
	 * high scores at the end of the game.
	 */
	public GameOverDialog(String userScore) {
		score.setText(userScore);
		
		JPanel centralPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		add(centralPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		initCentralPanel(centralPanel);
		initButtonPanel(buttonPanel);
		
		pack();
		setTitle("Game Over!");
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
	}
	
	private void initCentralPanel(JPanel centralPanel) {
		loadHighScores(highScores);
		highScores.setEditable(false);
		highScores.setFont(new Font("SansSerif", Font.BOLD, 12));
		JScrollPane scrollPane = new JScrollPane(highScores);		
		centralPanel.setLayout(new GridBagLayout());
		centralPanel.setPreferredSize(new Dimension(400, 200));
		
		// Adding components to the panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100;
		centralPanel.add(youLabel, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 100;
		centralPanel.add(highScoresLabel, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 100;
		centralPanel.add(nameLabel, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 100;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		name.setPreferredSize(new Dimension(75, 30));
		centralPanel.add(name, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 4;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;
		scrollPane.setPreferredSize(new Dimension(150, 160));
		centralPanel.add(scrollPane, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 100;
		gbc.weighty = 100;
		centralPanel.add(scoreLabel, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 100;
		score.setPreferredSize(new Dimension(75, 40));
		centralPanel.add(score, gbc);
	}
	
	private void initButtonPanel(JPanel buttonPanel) {
		buttonPanel.add(new JButton(new AbstractAction("Save & Exit") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!name.getText().trim().isEmpty()) {
					saveScore(name.getText().trim(), score.getText());
				}
				System.exit(0);
			}
		}));
	}
	
	/*
	 * Loads previously recorded high scores from a txt file "scores.txt" 
	 * into the text area and a list of scores. TODO better writup
	 */
	private void loadHighScores(JTextArea textArea) {
		try (Scanner in = new Scanner(new File(SCORE_FILE))) {
			highScoreList = new ArrayList<String>();
			
			int num = Integer.parseInt(in.nextLine());
			for (int i = 0; i < num; i++) {
				String record = in.nextLine();
				highScoreList.add(record);
				String[] tokens = record.split(":");
				highScores.append(tokens[1] + " " + tokens[0] + "\n");
			}
		} catch (IOException e) {
			highScores.append("No scores previously recorded");
		}
	}
	
	private void saveScore(String name, String score) {
		
	}
	
	// test
	public static void main(String... args) {
		new GameOverDialog("320").setVisible(true);
	}
}
