package uk.ac.aber.mwg2.cs123.patience.automation;

import java.util.List;

import uk.ac.aber.mwg2.cs123.patience.cards.Card;
import uk.ac.aber.mwg2.cs123.patience.cards.Pack;
import uk.ac.aber.mwg2.cs123.patience.cards.Pile;
import uk.ac.aber.mwg2.cs123.patience.gui.ScorePanel;
import uk.ac.aber.mwg2.cs123.patience.gui.Table;

/**
 * Bot object can be used to automate the gameplay. 
 * 
 * @author mwg2
 * @since 1 April 2015
 */
public class Bot {
	
	private Table table;
	private Pile pile;
	private Pack pack;
	private ScorePanel scorePanel;
	
	/**
	 * Create a Bot object which can be used to automate the gameplay. 
	 * @param table Table where cards are drawn
	 * @param scorePanel A panel where current score is drawn
	 */
	public Bot(Table table, ScorePanel scorePanel) {
		this.table = table;
		this.scorePanel = scorePanel;
		pile = table.getPile();
		pack = table.getPack();
	}
	
	/**
	 * Tries to make a move in the game. Firstly checks if the neighbouring cards
	 * can be 'joined' if they have the same suit or value. Afterwards it checks 
	 * if cards that have two other in the middle can be joined. Finally in case
	 * previous attempts failed it deals a card from the deck.  
	 * 
	 * @return true if the move was made, false otherwise
	 */
	public boolean makeMove() {
		if (!pile.isEmpty()) {
			List<Card> pileCards = pile.getCards();
			
			// ignore if there is only one card in the pile, as move not possible
			if (pileCards.size() != 1) {
				if (tryNeighbouringCards(pileCards)) {
					return true;
				}
				// ignore if there is not enough cards to have two in the middle
				if (pileCards.size() >= 4) {
					if (tryCardsWithGap(pileCards)) {
						return true;
					}
				}
			}
		}
		// if above failed try to deal a card
		if (!pack.isEmpty()) {
			unselectEveryCard(pile.getCards());
			pile.addCard(pack.dealCard());
			return true;
		}
		return false;
	}
	
	/*
	 * Go from right to left and check if any of the cards which are 
	 * next to each other can be joined (same suit or same value)
	 * 
	 * @return true if the move was made, false otherwise
	 */
	private boolean tryNeighbouringCards(List<Card> pileCards) {
		for (int i = pileCards.size() - 1; i > 0; i--) {
			// firstly unselect every card in the pile
			unselectEveryCard(pileCards);
			
			// then pick two cards and check if the move is valid
			pileCards.get(i).setPressed(true);
			pileCards.get(i - 1).setPressed(true);
			if (pile.isMoveValid()) {
				pile.makeMove();
				scorePanel.addPoints();
				// reset the amount of cards selected
				table.setCardsSelected(0);
				unselectEveryCard(pileCards);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Go from right to left and check if any of the cards which have 
	 * other two cards in the middle can be joined (same suit or value)
	 * 
	 * @return true if the move was made, false otherwise
	 */
	private boolean tryCardsWithGap(List<Card> pileCards) {
		for (int i = pileCards.size() - 1; i > 2; i--) {
			// again unselect every card first
			unselectEveryCard(pileCards);
			
			pileCards.get(i).setPressed(true);
			pileCards.get(i - 3).setPressed(true);
			if (pile.isMoveValid()) {
				pile.makeMove();
				scorePanel.addPoints();
				// reset the amount of cards selected
				table.setCardsSelected(0);
				unselectEveryCard(pileCards);
				return true;
			}
		}
		return false;
	}
	
	// Unselects every card in the game 
	private void unselectEveryCard(List<Card> pileCards) {
		for (Card c : pileCards) {
			c.setPressed(false);
		}
	}
}
