package uk.ac.aber.mwg2.cs123.patience.gui;

/**
 * Suit enum represents a suit of a card. Each suit has its corresponding single
 * character String representation, accessible with the toString method.
 * 
 * @author mwg2
 * @since 25 March 2015
 */
public enum Suit {

	SPADES("s"), HEARTS("h"), DIAMONDS("d"), CLUBS("c");

	private String name;

	private Suit(String name) {
		this.name = name;
	}

	/**
	 * @return A single character String representaion of this suit.
	 */
	@Override
	public String toString() {
		return name;
	}
}