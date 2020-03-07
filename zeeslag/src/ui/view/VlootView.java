package ui.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import domain.Positie;
import ui.Controller;
import ui.actionlisteners.ShipPickerListener;

public class VlootView extends JPanel implements Observer {

	private JButton[][] gridVloot;
	private Controller controller;
	private JComboBox<String> schepen;
	private ButtonGroup buttons;
	private Button startButton;

	public VlootView(Controller controller, JComboBox<String> schepen, JButton[][] gridVloot, ButtonGroup buttons,
			Button startButton) {

		this.controller = controller;
		this.setPreferredSize(new Dimension(250, 250));
		this.setVisible(true);
		this.setLayout(new GridLayout(10, 10));

		this.gridVloot = gridVloot;
		this.schepen = schepen;
		this.buttons = buttons;
		this.startButton = startButton;
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JButton button = new JButton();
				this.add(button);
				// wordt toegevoegd aan grid om later te recallen het is [?][?]
				// en begint linksboven
				gridVloot[i][j] = button;
				button.addActionListener(
						new ShipPickerListener(controller, schepen, gridVloot, i, j, buttons, this, startButton));
			}
		}
		
		controller.addObserver(this);
	}


	public void setEnabled(boolean enabled) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				gridVloot[i][j].setEnabled(enabled);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (controller.getSchepen(0).isEmpty()) {
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					gridVloot[j][i].setOpaque(true);
					gridVloot[j][i].setBackground(null);
					gridVloot[j][i].setBorderPainted(true);
					this.setEnabled(true);
					}
				}
		}
		
		for (Positie positie : controller.getSchepen(0)) {
			gridVloot[positie.getY()][positie.getX()].setOpaque(true);
			gridVloot[positie.getY()][positie.getX()].setBackground(Color.BLACK);
			gridVloot[positie.getY()][positie.getX()].setBorderPainted(false);
		}

		List<Positie> schepenPosities = controller.getSchepen(0);// schepen
		// computer

		for (Positie pos : controller.getHitsSpeler1()) {// hits op bord computers
			if (schepenPosities.contains(pos)) {
				if (controller.isSchipKapot(pos, 0)) {
					gridVloot[pos.getY()][pos.getX()].setOpaque(true);
					gridVloot[pos.getY()][pos.getX()].setBackground(Color.RED);
					gridVloot[pos.getY()][pos.getX()].setEnabled(false);
				} else {
					gridVloot[pos.getY()][pos.getX()].setOpaque(true);
					gridVloot[pos.getY()][pos.getX()].setBackground(Color.YELLOW);
					gridVloot[pos.getY()][pos.getX()].setEnabled(false);
				}
			} else {
				gridVloot[pos.getY()][pos.getX()].setOpaque(true);
				gridVloot[pos.getY()][pos.getX()].setBackground(Color.GRAY);
				gridVloot[pos.getY()][pos.getX()].setEnabled(false);
			}
		}

	}

}
