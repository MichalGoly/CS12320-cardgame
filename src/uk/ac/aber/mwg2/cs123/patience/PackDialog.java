package uk.ac.aber.mwg2.cs123.patience;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PackDialog extends JDialog {
	
	private Pack pack;
	
	private final String TITLE = "Pack";
	
	public PackDialog(Pack pack) {
		this.pack = pack;
		init();
	}
	
	private void init() {
		// Create and add a drawing area to the JDialog
		JScrollPane scrollPane = new JScrollPane(new DrawingArea());
		add(scrollPane, BorderLayout.CENTER);
		
		// Create and add a button to the JDialog
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton(new AbstractAction("Close") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}));
		add(buttonPanel, BorderLayout.SOUTH);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screenSize.width / 2, 195));
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle(TITLE);
	}

	private class DrawingArea extends JPanel {
				
		// (72+2) is the sum of the width of one card and two additional pixels
		// 52 is the amount of cards in the pack
		private final int WIDTH = (72 + 2) * 52;
		private final int HEIGHT = 101;
		
		public DrawingArea() {			
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
			setBackground(Color.PINK);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			// Actual drawing of the pack
			int x = 2;
			int y = 2;
			for (Card c : pack.getCards()) {
				g2.drawImage(c.getImage(), null, x, y);
				// Width of card and two pixels to make some space between cards
				x += 72 + 2;
			}
		}
	}
}
