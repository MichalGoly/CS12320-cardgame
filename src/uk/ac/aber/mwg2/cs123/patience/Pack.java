package uk.ac.aber.mwg2.cs123.patience;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
	private PackDialog packDialog;
	private boolean shuffled = false;

	private final String PACK_FILE = "pack.txt";

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

	/**
	 * @return list of cards in the pack
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Creates a modal dialog which displays current pack
	 */
	public void diplayPack() {
		if (packDialog == null) {
			packDialog = new PackDialog(this);
		}
		packDialog.repaint();
		packDialog.setVisible(true);
	}

	/**
	 * Shuffles the pack of cards when called for the first time. Every other
	 * attept to use it will fail and notify the user.
	 */
	public void shuffle() {
		if (!shuffled) {
			Collections.shuffle(cards);
			shuffled = true;
		} else {
			JOptionPane.showMessageDialog(packDialog,
					"This pack has been already shuffled.");
		}
	}

	// Loads 52 cards into the 'cards' list.
	private void loadCards() throws IOException {
		try (Scanner in = new Scanner(new File(PACK_FILE))) {
			int num = Integer.parseInt(in.nextLine());
			for (int i = 0; i < num; i++) {
				String[] cardInfo = in.nextLine().split(":");
				cards.add(new Card(Suit.fromString(cardInfo[0]), Value
						.fromString(cardInfo[1])));
			}
		}
	}
}
