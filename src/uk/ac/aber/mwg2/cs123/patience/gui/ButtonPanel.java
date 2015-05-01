package uk.ac.aber.mwg2.cs123.patience.gui;

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

import uk.ac.aber.mwg2.cs123.patience.automation.Bot;

/**
 * This class represents a panel with buttons user can click to access
 * additional functions in the game (display & shuffle the pack and 
 * automate the gameplay).
 * 
 * @author mwg2
 * @since 1 April 2015
 */
public class ButtonPanel extends JPanel {

	private Game game;
	private Table table;
	private Bot bot;

	private final int DEFAULT_HEIGHT = 70;
	
	/**
	 * Constructs a new ButtonPanel object which will contain buttons user can 
	 * click to access additional functions. 
	 */
	public ButtonPanel(Game game, Table table, ScorePanel scorePanel) {
		this.game = game;
		this.table = table;
		bot = new Bot(table, scorePanel);
		init();
	}

	private void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(table.getWidth(), DEFAULT_HEIGHT));

		add(createPackPanel());
		add(createAutomationPanel());
	}
	
	private JPanel createPackPanel() {
		JPanel packPanel = new JPanel();
		packPanel.setBorder(BorderFactory.createTitledBorder("Pack"));
		packPanel.add(new JButton(new AbstractAction("Display") {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.getPack().diplayPack();
			}
		}));
		packPanel.add(new JButton(new AbstractAction("Shuffle") {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.getPack().shuffle();
			}
		}));
		return packPanel;		
	}
	
	private JPanel createAutomationPanel() {
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
		automationPanel.add(createXTimesButton());
		
		return automationPanel;
	}

	private JButton createXTimesButton() {
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
		return xTimesButton;
	}
}
