package ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class VlootPlaatsView extends JPanel {
	
	GridBagConstraints constraints = new GridBagConstraints();
	
	
	public VlootPlaatsView(JPanel infoPanel, JComboBox<String> schepen) {

		Label schepenLabel = new Label("Beschikbare Schepen");
		schepen.addItem("Vliegdekschip (1)");
		schepen.addItem("Slagschip (2)");
		schepen.addItem("Onderzeeer (3)");
		schepen.addItem("Torpedobootjager (3)");
		schepen.addItem("Patrouilleschip (4)");
		this.setLayout(new GridLayout(2, 1));
		this.add(schepenLabel);
		this.add(schepen);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		infoPanel.add(this, constraints);
		
	}

}
