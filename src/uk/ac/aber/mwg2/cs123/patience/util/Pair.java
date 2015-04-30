package uk.ac.aber.mwg2.cs123.patience.util;

/**
 * Simple generic class which represents a Pair of any two objects of the same
 * class. Allows to pass around two objects together. 
 * 
 * @author mwg
 * @since 31 March 2015
 */
public class Pair<T> {
	
	private T first;
	private T second;
	
	/**
	 * Create a pair of two objects of the same type
	 * @param first First object
	 * @param second Second object
	 */
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * @return First object
	 */
	public T getFirst() {
		return first;
	}

	/**
	 * @return Second object
	 */
	public T getSecond() {
		return second;
	}
	
}
