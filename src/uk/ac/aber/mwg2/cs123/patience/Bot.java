package uk.ac.aber.mwg2.cs123.patience;

import java.util.List;

/**
 * Bot class can be used to automate the game. 
 * 
 * @author mwg2
 * @since 1 April 2015
 */
public class Bot {
	
	private Table table;
	private Pile pile;
	private Pack pack;
	private ScorePanel scorePanel;
	
	public Bot(Table table, ScorePanel scorePanel) {
		this.table = table;
		this.scorePanel = scorePanel;
		pile = table.getPile();
		pack = table.getPack();
	}
	
	/**
	 * Tries to make the first available move in the game. By 'first' we mean 
	 * the 'furthest' one from the right. 
	 * 
	 * @return true if the move was made, false otherwise
	 */
	public boolean makeMove() {
		if (!pile.isEmpty()) {
			List<Card> pileCards = pile.getCards();
			
			// ignore if there is only one card in the pile
			if (pileCards.size() != 1) {
				// Go from right to left and check if any of the cards which are 
				// next to each other can be joined (same suit or same value)
				for (int i = pileCards.size() - 1; i > 0; i--) {
					// firstly unselect every card in the pile
					for (Card c : pileCards) {
						c.setPressed(false);
					}
					
					// then pick two cards and check if the move is valid
					pileCards.get(i).setPressed(true);
					pileCards.get(i - 1).setPressed(true);
					if (pile.isMoveValid()) {
						pile.makeMove();
						scorePanel.addPoints();
						// reset the amount of cards selected
						table.setCardsSelected(0);
						for (Card c : pileCards) {
							c.setPressed(false);
						}
						return true;
					}
				}
				
				
			}
		}
		return false;
	}
	
}
