package uk.ac.aber.mwg2.cs123.patience;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract construct which represents a collection of cards. Internally
 * cards are stored in a list and can be accessed and removed using 
 * provided methods.
 * 
 * @author mwg2
 * @since 27 March 2015
 *
 */
public abstract class CardsCollection {
	
	private List<Card> cards;
	
	public CardsCollection() {
		cards = new ArrayList<Card>();
	}
	
	/**
	 * @return A List of cards in the collection
	 */
	public List<Card> getCards() {
		return cards;
	}
	
	/**
	 * Adds a card at the end of the collection.
	 * @param card New card to be added
	 */
	public void addCard(Card card) {
		cards.add(card);
	}
	
}
