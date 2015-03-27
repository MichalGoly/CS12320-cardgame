package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Table class represents a playing table on which cards are being printed.
 * User can manipulate cards by clicking and selecting them. If the move is
 * supported by the game, playing table will be updated and reprinted.
 * 
 * TODO better explenation later on
 * 
 * @author mwg2
 * @since 26 March 2015
 *
 */
public class Table extends JPanel {
	
	private final int DEFAULT_HEIGHT = 300;
	
	private Pack pack;
	
	public Table() {
		pack = new Pack();
		init();
	}
	
	/**
	 * @return Current contents of the pack.
	 */
	public Pack getPack() {
		return pack;
	}
	
	private void init() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screenSize.width / 2 , DEFAULT_HEIGHT));
		setBackground(new Color(13, 137, 13));
	}
	
}
