package ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.Positie;
import ui.Controller;
import ui.view.SpeelPanelView;
import ui.view.TargetView;
import ui.view.VlootView;
import ui.view.ZeeslagFrame;

public class HitListener implements ActionListener {

	private Controller controller;
	private Positie positie;
	private SpeelPanelView speelPanel;
	private ZeeslagFrame zeeslagFrame;
	
	public HitListener(Controller controller, Positie pos, SpeelPanelView speelPanel, ZeeslagFrame zeeslagFrame) {
		this.controller = controller;
		this.positie = pos;
		this.speelPanel = speelPanel;
		this.zeeslagFrame = zeeslagFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: Refactoren naar observer
		controller.raakVakjeSpeler1(positie);
		controller.AIValAan();
	}

}
