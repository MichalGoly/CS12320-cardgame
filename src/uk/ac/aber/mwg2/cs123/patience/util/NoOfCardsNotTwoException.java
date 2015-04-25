package uk.ac.aber.mwg2.cs123.patience.util;

/**
 * Thrown whenever the number of pressed cards is not equal to two, when it should
 * be.
 * 
 * @author mwg2
 */
public class NoOfCardsNotTwoException extends Exception {
	
	public NoOfCardsNotTwoException(String message) {
		super(message);
	}
}
