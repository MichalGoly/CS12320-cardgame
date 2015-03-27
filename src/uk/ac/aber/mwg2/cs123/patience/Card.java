package uk.ac.aber.mwg2.cs123.patience;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Represents a single playing card. Each card has its value, suit and an image.
 * 
 * @author mwg2
 * @since 25 March 2015
 */
public class Card {

	private Suit suit;
	private Value value;
	private BufferedImage img;

	/**
	 * Creates a playing card with a specified suit and value. Its image will be
	 * loaded automatically.
	 * 
	 * @param suit A suit of the newly created card
	 * @param value A value of the card
	 */
	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;

		try {
			loadImage();
		} catch (IOException e) {
			// Notify the user that the corresponding image is missing
			JOptionPane.showMessageDialog(null,
					"Unable to load an image from file " + value.toString()
							+ suit.toString() + ".gif.\n" + "Please check if"
							+ " it exists.", null, JOptionPane.ERROR_MESSAGE);
			// we cannot continue playing so we crash
			System.exit(-1);
		}
	}
	
	/**
	 * @return The image of a card.
	 */
	public BufferedImage getImage() {
		return img;
	}
	
	// Loads an appropriate card image depending on the suit and value
	private void loadImage() throws IOException {
		String path = "res/" + value.toString() + suit.toString() + ".gif";
		img = ImageIO.read(new File(path));
	}
}