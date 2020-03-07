package ui.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.Controller;

public class ZeeslagView extends JPanel implements Observer {

	private Controller controller;

	public ZeeslagView(JFrame zeeslagView, Controller controller) {

		this.controller = controller;
		zeeslagView.setContentPane(this);
		zeeslagView.setPreferredSize(new Dimension(900, 400));
		zeeslagView.setTitle("Zeeslag");
		zeeslagView.setResizable(false);
		zeeslagView.setLayout(new GridBagLayout());
		controller.addObserver(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (controller.isSpelGedaan() != null) {
			JOptionPane.showMessageDialog(this, "Game Over!\n" + controller.isSpelGedaan().getNaam() + " won met "
					+ controller.isSpelGedaan().getBerekendeScore() + " punten...");
			if (!controller.isSpelGestopt()) {
				controller.setSpelGestopt();
			}
		}
	}

}
