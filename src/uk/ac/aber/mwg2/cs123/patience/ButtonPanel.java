package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private Game game;
	private Table table;
	private Bot bot;

	private final int DEFAULT_HEIGHT = 70;

	public ButtonPanel(Game game, Table table, ScorePanel scorePanel) {
		this.game = game;
		this.table = table;
		bot = new Bot(table, scorePanel);
		init();
	}

	private void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(table.getWidth(), DEFAULT_HEIGHT));

		JPanel gamePanel = new JPanel();
		gamePanel.setBorder(BorderFactory.createTitledBorder("Game"));
		gamePanel.add(new JButton(new AbstractAction("Display Pack") {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.getPack().diplayPack();
			}
		}));
		gamePanel.add(new JButton(new AbstractAction("Shuffle") {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.getPack().shuffle();
			}
		}));
		add(gamePanel);

		JPanel automationPanel = new JPanel();
		automationPanel.setBorder(BorderFactory
				.createTitledBorder("Play for me"));
		automationPanel.add(new JButton(new AbstractAction("Once") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (bot.makeMove()) {
					table.repaint();
				} else {
					game.dispatchEvent(new WindowEvent(game,
							WindowEvent.WINDOW_CLOSING));
				}
			}
		}));
		JButton xTimesButton = new JButton("x Times");
		xTimesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = false;
				while (!valid) {
					String input = (String) JOptionPane.showInputDialog(
							xTimesButton, "How many moves should I make for you?",
							"", JOptionPane.PLAIN_MESSAGE, null, null, "0");
					// stop if user wants to close the dialog by pressing 'close'
					if (input == null) {
						break;
					}
					
					try {
						int intResult = Integer.parseInt(input.trim());
						if (intResult >= 0) {
							valid = true;
							for (int i = 0; i < intResult; i++) {
								if (bot.makeMove()) {
									table.repaint();
								} else {
									game.dispatchEvent(new WindowEvent(game,
											WindowEvent.WINDOW_CLOSING));
								}
							}
						}
					} catch (NumberFormatException exc) {
						Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		});
		automationPanel.add(xTimesButton);
		add(automationPanel);
	}
}
