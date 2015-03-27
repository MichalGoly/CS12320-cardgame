package uk.ac.aber.mwg2.cs123.patience;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	private Table table;
	
	private final int DEFAULT_HEIGHT = 40;
	
	public ButtonPanel(Table table) {
		this.table = table;
		init();
	}
	
	private void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(table.getWidth(), DEFAULT_HEIGHT));
		
	}
	
}
