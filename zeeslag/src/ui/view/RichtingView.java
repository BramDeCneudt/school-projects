package ui.view;

import java.awt.GridBagConstraints;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RichtingView extends JPanel {
	
	GridBagConstraints constraints = new GridBagConstraints();
	
	public RichtingView(JPanel infoPanel, ButtonGroup buttons) {

	Label richting = new Label("Richting:");
	
	JRadioButton horizontaal = new JRadioButton("Horizontaal");
	horizontaal.setActionCommand("horizontaal");

	horizontaal.setSelected(true);
	JRadioButton verticaal = new JRadioButton("verticaal");
	verticaal.setActionCommand("verticaal");
	buttons.add(horizontaal);
	buttons.add(verticaal);
	this.add(richting);
	this.add(horizontaal);
	this.add(verticaal);

	constraints.gridx = 0;
	constraints.gridy = 1;
	infoPanel.add(this, constraints);
	}

}
