package ui.view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InfoPanelView extends JPanel {

	
	public InfoPanelView(Button startButton) {

		this.setLayout(new GridLayout(3, 1));
		this.setPreferredSize(new Dimension(275, 300));
		this.add(startButton);	
	}
}
