package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
		automationPanel.setBorder(BorderFactory.createTitledBorder("Play for me"));
		automationPanel.add(new JButton(new AbstractAction("Once") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bot.makeMove()) {
					table.repaint();
				} else {
					game.dispatchEvent(new WindowEvent(game, WindowEvent.WINDOW_CLOSING));
				}
			}
		}));
		automationPanel.add(new JButton(new AbstractAction("x Times") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		}));
		add(automationPanel);
	}
}
