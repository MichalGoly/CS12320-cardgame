package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * Table class represents a playing table on which cards are being printed.
 * User can manipulate cards by clicking and selecting them. If the move is
 * supported by the game, playing table will be updated and reprinted.
 * 
 * TODO better explenation later on
 * 
 * @author mwg2
 * @since 26 March 2015
 *
 */
public class Table extends JPanel {
	
	private final int DEFAULT_HEIGHT = 300;
	private final int DEFAULT_WIDTH = 60 * 2 + 72 * 52 + 6 * 51;
	
	private Pack pack;
	private Pile pile;
	
	public Table() {
		pack = new Pack();
		pile = new Pile();
		init();
	}
	
	/**
	 * @return Current contents of the pack.
	 */
	public Pack getPack() {
		return pack;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// print the pile
		int x = 30;
		int y = 30;
		for (Card c : pile.getCards()) {
			g2.drawImage(c.getImage(), null, x, y);
			x += c.getImage().getWidth() + 6;
		}
			
		// print an image of the reversed card, which represents the pack
		if (!pack.getCards().isEmpty()) {
			g2.drawImage(pack.getImage(), null, 60,
					2 * y + pack.getImage().getHeight());
		}
	}
	
	private void init() {
		setPreferredSize(new Dimension(DEFAULT_WIDTH , DEFAULT_HEIGHT));
		setBackground(new Color(13, 137, 13));
		addMouseListener(new MouseHandler());
	}
	
	// Takes care of mouse events and allows the user to play the game
	private class MouseHandler extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (!pack.isEmpty()) {
				pile.addCard(pack.dealCard());
				repaint();
			}
		}
	}
}
