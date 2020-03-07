package ui.actionlisteners;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.AlleSchepenGeplaatstException;
import domain.DomainException;
import domain.Positie;
import domain.schepen.Schip;
import ui.Controller;
import ui.view.VlootView;

public class ShipPickerListener implements ActionListener {

	private JComboBox schepen;
	private JButton[][] grid;
	private int x;
	private int y;
	private ButtonGroup richting;
	private Controller controller;
	private VlootView vlootView;
	private Button startButton;

	/*
	 * Combobox moet worden meegegeven
	 */
	public ShipPickerListener(Controller controller, JComboBox schepen, JButton[][] grid, int y, int x,
			ButtonGroup richting, VlootView vlootView, Button startButton) {
		this.controller = controller;
		this.schepen = schepen;
		this.grid = grid;
		this.x = x;
		this.y = y;
		this.richting = richting;
		this.vlootView = vlootView;
		this.startButton = startButton;
	}

	/*
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// is nodig om de nummering (x) te spliten van de text
		String[] schipStringArray = this.schepen.getSelectedItem().toString().split(" ");

		String schipString = schipStringArray[0];
		String richtingString = this.richting.getSelection().getActionCommand().toUpperCase();
		Positie positie = new Positie(x, y);
		try {
			controller.setSchip(schipString, richtingString, positie, 0);
		} catch (DomainException d) {
			JOptionPane.showMessageDialog(vlootView, d.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (AlleSchepenGeplaatstException a) {
			// TODO start spel button enabled
			JOptionPane.showMessageDialog(vlootView, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			startButton.setEnabled(true);
		}

	}

}
