package uk.ac.aber.mwg2.cs123.patience;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * This class initialy represents an ordered 52 cards pack. User can shuffle it
 * and remove cards from it, using provided methods.
 * 
 * @author mwg2
 * @since 26 March 2015
 *
 */
public class Pack {
	
	private List<Card> cards;

	private static final String CARDS_DIR = "res/";
	
	/**
	 * Creates a sorted 52 cards pack
	 */
	public Pack() {
		cards = new ArrayList<Card>(52);
		try {
			loadCards();
		} catch (IOException e) {
			// Notify the user and crash as there would be no point to
			// carry on.
			JOptionPane.showMessageDialog(null,
					"Unable to load cards to the pack", null,
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	}
	
	// Loads 52 cards into the 'cards' list by iterating over the directory
	// where card images are stored. 
	private void loadCards() throws IOException {
		Path dir = Paths.get(CARDS_DIR);
		try (DirectoryStream<Path> paths = Files.newDirectoryStream(dir)) {
			for (Path p : paths) {
				String value = p.getFileName().toString().substring(0, 1);
				String suit = p.getFileName().toString().substring(1, 2);
				
				// ignore the reversed card
				if (!value.equals("b")) {
					cards.add(new Card(
							Suit.fromString(suit), Value.fromString(value)));
				}
			}
		}
	}
	
	// TEST
	public static void main(String[] args) {
		new Pack();
	}
}
