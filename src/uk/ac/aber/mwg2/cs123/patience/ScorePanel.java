package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

	private String currentScore;
	private String highScore;
	private Font font;

	private final String CURRENT_SCORE_LABEL = "SCORE:";
	private final String HIGH_SCORE_LABEL = "HIGH SCORE:";
	private final String SCORES_FILE = "scores.txt";

	private final int DEFAULT_WIDTH;
	private final int DEFAULT_HEIGHT = 30;

	public ScorePanel() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		DEFAULT_WIDTH = screenSize.width / 2;
		currentScore = "0";
		font = new Font("SansSerif", Font.BOLD, 18);
		highScore = loadHighScore();

		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setBackground(new Color(234, 0, 255));
	}
	
	/**
	 * Adds 10 point to the current score and updates the view.
	 */
	public void addPoints() {
		int intScore = Integer.parseInt(currentScore);
		currentScore = (intScore + 10) + "";
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setFont(font);
		g2.setColor(new Color(94, 255, 77));
		g2.drawString(CURRENT_SCORE_LABEL, 5, 20);
		g2.drawString(currentScore, 100, 20);
		g2.drawString(HIGH_SCORE_LABEL, 200, 20);
		g2.drawString(highScore, 355, 20);
	}

	/*
	 * Loads the high score from the text file which contains previously
	 * recorded scores and determines which one is the highest. Returns "0"
	 * String if there is a problem with loading from the txt file or there are
	 * no scores recorded.
	 */
	private String loadHighScore() {
		String result = "0";
		try (Scanner in = new Scanner(new File(SCORES_FILE))) {
			int num = Integer.parseInt(in.nextLine());
			List<Integer> results = new ArrayList<Integer>();

			// retrieve high scores
			for (int i = 0; i < num; i++) {
				String[] tokens = in.nextLine().split(":");
				results.add(Integer.parseInt(tokens[0]));
			}

			// determine the highest one
			result = Collections.max(results) + "";
		} catch (IOException e) {
			// in case there is a problem with loading high scores or there
			// isn't one, we leave the result as 0
			JOptionPane.showMessageDialog(getParent(),"There was a problem with"
					+ "retrieving high score. File 'scores.txt' may be corrupted");
		}
		return result;
	}
}
