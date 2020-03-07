package ui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import ui.Controller;

public class SpeelPanelView extends JPanel implements Observer{
	
	GridBagConstraints constraints = new GridBagConstraints();
	Controller controller;
	Label naamPlayer1;
	Label naamPlayer2;
	
	public SpeelPanelView(Controller controller) {
		
		this.setLayout(new GridBagLayout());
		this.controller = controller;
		
		naamPlayer1 = new Label(controller.getSpeler1Naam()+"("+controller.getBerekendeScoreSpeler1()+")");
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add(naamPlayer1, constraints);
		
		naamPlayer2 = new Label(controller.getSpeler2Naam()+"("+controller.getBerekendeScoreSpeler2()+")");
		constraints.gridx = 2;
		constraints.gridy = 0;
		this.add(naamPlayer2, constraints);
		controller.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.naamPlayer1.setText(controller.getSpeler1Naam()+"("+controller.getBerekendeScoreSpeler1()+")"); 
		this.naamPlayer2.setText(controller.getSpeler2Naam()+"("+controller.getBerekendeScoreSpeler2()+")");
	}

}
