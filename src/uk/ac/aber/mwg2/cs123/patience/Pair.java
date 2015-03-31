package uk.ac.aber.mwg2.cs123.patience;

/**
 * Simple generic class which represents a Pair of any two objects of the same
 * class. Allows to pass around two objects together. 
 * 
 * @author mwg
 * @since 31 March 2015
 * @param <T>
 */
public class Pair<T> {
	
	private T first;
	private T second;
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
	
}
