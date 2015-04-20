package uk.ac.aber.mwg2.cs123.patience.cards;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import uk.ac.aber.mwg2.cs123.patience.gui.PackDialog;

/**
 * This class initialy represents an ordered 52 cards pack. User can shuffle it
 * and remove cards from it, using provided methods.
 * 
 * @author mwg2
 * @since 26 March 2015
 *
 */
public class Pack extends CardsCollection {

	private PackDialog packDialog;
	private boolean shuffled = false;
	private BufferedImage img;
	
	private final String PACK_FILE = "pack.txt";
	private final String IMG_FILE = "res/bb.gif";
	
	/**
	 * Creates a sorted 52 cards pack
	 */
	public Pack() {
		super();
		
		// load the image of a reversed card
		loadImage();
		
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
	 * Returns an image of a reversed playing card. It represents the whole
	 * pack placed on the table upside down.
	 * @return Image of the reversed pack
	 */
	public BufferedImage getImage() {
		return img;
	}
	
	/**
	 * Returns and removes the first card from the pack. Note that a null will 
	 * be returned if the pack is empty.
	 * @return Card from the 'head' of the pack or a null if the pack is empty
	 */
	public Card dealCard() {
		if (!getCards().isEmpty()) {
			return getCards().remove(0);
		} else {
			return null;
		}
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
			Collections.shuffle(getCards());
			shuffled = true;
		} else {
			JOptionPane.showMessageDialog(packDialog,
					"This pack has been already shuffled.");
		}
	}
	
	// Loads the image of a reversed card, as pack will be upside down
	private void loadImage() {
		try {
			img = ImageIO.read(new File(IMG_FILE));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to load the image of a "
					+ "reversed card into deck", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Loads 52 cards into the 'cards' list.
	private void loadCards() throws IOException {
		try (Scanner in = new Scanner(new File(PACK_FILE))) {
			int num = Integer.parseInt(in.nextLine());
			for (int i = 0; i < num; i++) {
				String[] cardInfo = in.nextLine().split(":");
				addCard(new Card(Suit.fromString(cardInfo[0]), Value
						.fromString(cardInfo[1])));
			}
		}
	}
}
