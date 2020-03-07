package ui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import domain.Positie;
import ui.Controller;
import ui.actionlisteners.HitListener;

public class TargetView extends JPanel implements Observer {

	private Controller controller;
	private JButton[][] targetVloot;
	private SpeelPanelView speelPanel;
	private ZeeslagFrame zeeslagFrame;

	private static final long serialVersionUID = 1L;
	GridBagConstraints constraints = new GridBagConstraints();

	public TargetView(Controller controller, JButton[][] targetVloot, VlootView vlootView, SpeelPanelView speelPanel,
			ZeeslagFrame zeeslagFrame) {
		this.controller = controller;
		this.targetVloot = targetVloot;
		this.speelPanel = speelPanel;
		this.zeeslagFrame = zeeslagFrame;
		this.setPreferredSize(new Dimension(250, 250));
		this.setVisible(true);
		this.setLayout(new GridLayout(10, 10));

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JButton button = new JButton();
				this.add(button);
				Positie pos = new Positie(j, i);
				targetVloot[i][j] = button;
				button.addActionListener(new HitListener(controller, pos, speelPanel, zeeslagFrame));
			}
		}
		controller.addObserver(this);

	}

	public void setEnabled(boolean enabled) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				targetVloot[i][j].setEnabled(enabled);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (controller.getSchepen(0).isEmpty()) {
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					
					targetVloot[j][i].setOpaque(true);
					targetVloot[j][i].setBackground(null);
					targetVloot[j][i].setBorderPainted(true);
					
					}
				}
		}
		
		List<Positie> schepenPosities = controller.getSchepen(1);// schepen
		// computer

		for (Positie pos : controller.getHitsSpeler2()) {// hits op bord computers
			if (schepenPosities.contains(pos)) {
				if (controller.isSchipKapot(pos, 1)) {
					targetVloot[pos.getY()][pos.getX()].setOpaque(true);
					targetVloot[pos.getY()][pos.getX()].setBackground(Color.RED);
					targetVloot[pos.getY()][pos.getX()].setEnabled(false);
				} else {
					targetVloot[pos.getY()][pos.getX()].setOpaque(true);
					targetVloot[pos.getY()][pos.getX()].setBackground(Color.YELLOW);
					targetVloot[pos.getY()][pos.getX()].setEnabled(false);
				}
			} else {
				targetVloot[pos.getY()][pos.getX()].setOpaque(true);
				targetVloot[pos.getY()][pos.getX()].setBackground(Color.GRAY);
				targetVloot[pos.getY()][pos.getX()].setEnabled(false);
			}
		}
		if(controller.isSpelGestopt()){
			this.setEnabled(false);
		}
	}

}
