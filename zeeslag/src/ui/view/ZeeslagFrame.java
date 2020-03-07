package ui.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import domain.ZeeslagSpeler;
import ui.Controller;
import ui.actionlisteners.ShipPickerListener;
import ui.actionlisteners.StartButtonActionListener;

//TODO refactoren alle JPanels naar IV's
public class ZeeslagFrame extends JFrame {

	private Controller controller;
	private JPanel zeeslagView;
	private VlootView vlootView;
	private TargetView targetView;
	private SpeelPanelView speelPanel;
	private JPanel infoPanel;
	private GridBagConstraints constraints = new GridBagConstraints();
	Button startButton;

	public ZeeslagFrame(Controller controller) {

		String naam1 = JOptionPane.showInputDialog("Wat is uw naam?:");
		String naam2 = JOptionPane.showInputDialog("Geef de naam voor de AI:");

		this.controller = controller;

		controller.setSpelers(naam1, naam2);

		zeeslagView = new ZeeslagView(this, controller);
		speelPanel = new SpeelPanelView(controller);

		startButton = new Button("Start");
		infoPanel = new InfoPanelView(startButton);

		JButton[][] gridFloot = new JButton[10][10];

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 0, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;

		// Vlootplaatsview
		JComboBox<String> schepen = new JComboBox<>();
		new VlootPlaatsView(infoPanel, schepen);

		// Richting
		ButtonGroup buttons = new ButtonGroup();
		new RichtingView(infoPanel, buttons);

		vlootView = new VlootView(controller, schepen, gridFloot, buttons, startButton);

		constraints.gridx = 1;
		constraints.gridy = 1;
		speelPanel.add(vlootView, constraints);

		JButton[][] targetVloot = new JButton[10][10];
		// TargetView
		targetView = new TargetView(controller, targetVloot, vlootView, speelPanel, this);
		constraints.gridx = 2;
		constraints.gridy = 1;
		speelPanel.add(targetView, constraints);

		vlootView.setVisible(true);
		targetView.setVisible(true);
		targetView.setEnabled(false);

		startButton.setEnabled(false);
		startButton.addActionListener(new StartButtonActionListener(controller, targetView, vlootView, startButton));
		

		// ZeeslagView
		zeeslagView.add(infoPanel);
		zeeslagView.add(speelPanel);
		this.pack();
	}
}
