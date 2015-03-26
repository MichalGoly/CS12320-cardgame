package uk.ac.aber.mwg2.cs123.patience;

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
	TEN("t"), 
	JACK("j"), 
	QUEEN("q"),
	KING("k");
	
	private String value;
	
	private Value(String value) {
		this.value = value;
	}
	
	/**
	 * Static method which creates and returns an Enum based on the provided
	 * String value. 
	 * 
	 * @param string A String representation of the required Enum 
	 * @return Either the required Enum or a null for an invalid input
	 */
	public static Value fromString(String string) {
		Value result = null;
		for (Value v : Value.values()) {
			if (string.equals(v.value)) {
				result = v;
				break;
			}
		}
		return result;
	}
	
	/**
	 * @return A String represenation of this value.
	 */
	@Override
	public String toString() {
		return value;
	}
	
}
