package uk.ac.aber.mwg2.cs123.patience.gui;

/**
 * Value enum represents a value of a card. Each value has its 
 * String representation, accessible with the toString method.
 * 
 * @author mwg2
 * @since 25 March 2015
 */
public enum Value {
	
	ACE("a"), 
	TWO("2"), 
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"), 
	EIGHT("8"),
	NINE("9"),
	TEN("10"), 
	JACK("j"), 
	QUEEN("q"),
	KING("k");
	
	private String value;
	
	private Value(String value) {
		this.value = value;
	}
	
	/**
	 * @return A String represenation of this value.
	 */
	@Override
	public String toString() {
		return value;
	}
	
}
