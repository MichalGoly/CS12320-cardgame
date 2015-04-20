package uk.ac.aber.mwg2.cs123.patience.cards;

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
	 * Static method which creates and returns an Enum based on the provided
	 * String value. Valid options: 's', 'h', 'd' and 'c'. Anything else will
	 * create and return a null.
	 * 
	 * @param stringValue String representation of the required Enum 
	 * @return Either the required Enum or a null for an invalid input
	 */
	public static Suit fromString(String stringValue) {
		Suit result = null;
		for (Suit s : Suit.values()) {
			if (stringValue.equals(s.name)) {
				result = s;
				break;
			}
		}
		return result;
	}
	
	/**
	 * @return A single character String representaion of this suit.
	 */
	@Override
	public String toString() {
		return name;
	}
	
}