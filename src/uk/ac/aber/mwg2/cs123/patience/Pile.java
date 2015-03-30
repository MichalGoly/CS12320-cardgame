package uk.ac.aber.mwg2.cs123.patience;

import java.util.ArrayList;
import java.util.List;

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

	// Pressed cards which will be selected by the user
	private Card pressedOne;
	private Card pressedTwo;

	public Pile() {
		super();
	}
	
	/**
	 * This method will force Pile to check if the two pressed cards correspond
	 * to any of the known valid moves in the game. 
	 */
	public boolean isMoveValid() {
		if (pressedOne == null || pressedTwo == null) return false;
		// TODO
		return false;
	}
	
	private void retrievePressedCards() {
		List<Card> pair = new ArrayList<Card>(2);
		for (Card c : getCards()) {
			if (c.isPressed()) {
				pair.add(c);
			}
		}
		
		//if ()
	}
}
