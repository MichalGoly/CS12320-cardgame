package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

/**
 * Table class represents a playing table on which cards are being printed.
 * User can manipulate cards by clicking and selecting them. If the move is
 * supported by the game, playing table will be updated and repainted.
 * 
 * TODO better explenation later on
 * 
 * @author mwg2
 * @since 26 March 2015
 *
 */
public class Table extends JPanel {
	
	private final int GAP_BETWEEN_CARDS = 6;
	private final int DEFAULT_HEIGHT = 300;
	// 30 is the additional space on both sides of the panel
	private final int DEFAULT_WIDTH = 
			30 * 2 + Card.IMG_WIDTH * 52 + GAP_BETWEEN_CARDS * 51;
	
	private Game game;
	private ScorePanel scorePanel;
	private Pack pack;
	private Pile pile;
	// Locations where images will be drawn
	private Rectangle[] fields;
	private int cardsSelected = 0;
	
	// variables for decoration purposes (borders around selected cards)
	private boolean packPressed = false;
	
	public Table(ScorePanel scorePanel, Game game) {
		this.game = game;
		this.scorePanel = scorePanel;
		pack = new Pack();
		pile = new Pile();
		initImgBoundries();
		initComponent();
	}
	
	/**
	 * @return Current contents of the pack.
	 */
	public Pack getPack() {
		return pack;
	}
	
	/**
	 * @return Current contents of the pile
	 */
	public Pile getPile() {
		return pile;
	}
	
	/**
	 * Sets the amount of cards selected to the given value. 
	 * @param cardsSelected Number of cards selected
	 */
	public void setCardsSelected(int cardsSelected) {
		this.cardsSelected = cardsSelected;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.CYAN);
		
		// print the pile
		int i = 0;
		for (Card c : pile.getCards()) {
			int x = (int) fields[i].getX();
			int y = (int) fields[i].getY();
			g2.drawImage(c.getImage(), null, x, y);
			
			if (c.isPressed()) {
				g2.drawRect(x - 1, y - 1, Card.IMG_WIDTH + 2, Card.IMG_HEIGHT + 4);
			}
			i++;
		}
			
		// print an image of the reversed card, which represents the pack
		if (!pack.getCards().isEmpty()) {
			int x = (int) fields[52].getX();
			int y = (int) fields[52].getY();
			g2.drawImage(pack.getImage(), null, x, y);
			
			if (packPressed) {
				g2.drawRect(x - 1, y - 1, Card.IMG_WIDTH + 2, Card.IMG_HEIGHT + 4);
			}
		}
	}
	
	// Generates boundries of each image that will be drawn on the table. This
	// will enable us to select cards. 
	private void initImgBoundries() {
		fields = new Rectangle[53];
		
		// fields for the 52 playing cards
		int y = 30;
		int i = 0;
		int finalX = (109 - 30) * 51 + 30;
		for (int x = 30; x <= finalX; x += Card.IMG_WIDTH + GAP_BETWEEN_CARDS) {
			fields[i] = new Rectangle(x, y, Card.IMG_WIDTH, Card.IMG_HEIGHT);
			i++;
		}
		
		// one field for the reversed card
		fields[52] = new Rectangle(60, 2 * y + Card.IMG_HEIGHT, 
				Card.IMG_WIDTH, Card.IMG_HEIGHT); 
	}
	
	private void initComponent() {
		setPreferredSize(new Dimension(DEFAULT_WIDTH , DEFAULT_HEIGHT));
		setBackground(new Color(13, 137, 13));
		addMouseListener(new MouseHandler());
	}
		
	// Takes care of mouse events and allows the user to play the game
	private class MouseHandler extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			// clear the 'invalid move' notification from the panel
			scorePanel.setInvalidMove(false);
			
			// select a card if pressed
			if (fields[52].contains(e.getPoint()) && (!pack.isEmpty())) {
				pile.addCard(pack.dealCard());
				packPressed = true;
			}
			checkCards(e.getPoint());
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			packPressed = false;
			
			// if two cards are selected, make a move or unselect if its illegal
			if (cardsSelected == 2 && (!pile.isEmpty())) {
				if (pile.isMoveValid()) {
					pile.makeMove();
					scorePanel.addPoints();
				} else {
					scorePanel.setInvalidMove(true);
				}
				
				for (Card c : pile.getCards()) {
					c.setPressed(false);
				}
				cardsSelected = 0;
			}
			repaint();
			
			// check if the player won and finish the game if he did
			if (isGameWon()) {
				game.dispatchEvent(
						new WindowEvent(game, WindowEvent.WINDOW_CLOSING));
			}
		}
		
		// Checks if any of the 52 playing cards needs to be selected and selects 
		// them if necessary 
		private void checkCards(Point p) {
			if (pile.isEmpty()) return;
			
			for (int i = 0; i < pile.getCards().size(); i++) {
				if (fields[i].contains(p)) {
					Card c = pile.getCards().get(i);
					if (c.isPressed()) {
						// unselect the card if it has been already pressed
						c.setPressed(false);
						cardsSelected--;
					} else {
						// select the card otherwise
						c.setPressed(true);
						cardsSelected++;
					}
				}
			}
		}
		
		// Checks if the player won. There should be no cards left in the pack
		// and pile size has to be equal 1
		private boolean isGameWon() {
			boolean isWon = false;
			if (pile.getCards().size() == 1 && pack.isEmpty()) {
				isWon = true;
			} 
			return isWon;
		}
	}
}
