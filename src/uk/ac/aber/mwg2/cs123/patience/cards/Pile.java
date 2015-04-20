package uk.ac.aber.mwg2.cs123.patience.cards;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import uk.ac.aber.mwg2.cs123.patience.util.NoOfCardsNotTwoException;
import uk.ac.aber.mwg2.cs123.patience.util.Pair;

/**
 * This class represents a collection of cards placed on a table. It also acts
 * as a controller and notifies the view (Table) to update itself whenever user
 * selects two cards and wants to perform a move in the game. Most of the game
 * logic is implemented here. 
 * 
 * @author mwg2
 * @since 30 March 2015
 */
public class Pile extends CardsCollection {
	
	// Indexes of selected cards
	private int firstIndex;
	private int secondIndex;
	
	public Pile() {
		super();
		firstIndex = -1;
		secondIndex = -1;
	}
	
	/**
	 * This method will force Pile to check if the two pressed cards correspond
	 * to any of the known valid moves in the game. 
	 */
	public boolean isMoveValid() {
		// get indexes of pressed cards
		try {
			Pair<Integer> indexes = retrieveIndexes();
			firstIndex = indexes.getFirst();
			secondIndex = indexes.getSecond();			
		} catch (NoOfCardsNotTwoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// Actual checking
		boolean result = false;
		if (secondIndex - firstIndex == 1 || secondIndex - firstIndex == 3) {
			Card c1 = getCards().get(firstIndex);
			Card c2 = getCards().get(secondIndex);
			if (c1.getSuit() == c2.getSuit() || c1.getValue() == c2.getValue()) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Makes a move in the game by updating contents of the collection of cards
	 * on the table (Pile). Pressed card with the lower index will be replaced
	 * with a card with the higher index.
	 * 
	 * NOTE that before making a move you should check if there is one by calling
	 * {@link #isMoveValid()} first.
	 */
	public void makeMove() {		
		List<Card> cards = getCards();
		cards.set(firstIndex, cards.remove(secondIndex));
	}
	
	//TODO
	private Pair<Integer> retrieveIndexes() throws NoOfCardsNotTwoException {
		List<Integer> tempList = new ArrayList<Integer>(2); 
		for (int i = 0; i < getCards().size(); i++) {
			if (getCards().get(i).isPressed()) {
				tempList.add(i);
			}
		}
		
		if (tempList.size() != 2) {
			throw new NoOfCardsNotTwoException("Pressed cards in pile != 2");
		}
		
		return new Pair<Integer>(tempList.get(0), tempList.get(1));		
	}
}
