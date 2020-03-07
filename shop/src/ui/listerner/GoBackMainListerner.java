package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import domain.Shop;
import ui.view.AddProductView;
import ui.view.MainView;

public class GoBackMainListerner implements ActionListener {
	
	private JFrame parentJFrame;
	private JFrame childJFrame;
	private Shop shop;
	
	public GoBackMainListerner(JFrame parentJFrame, JFrame childJFrame) {
		
		this.parentJFrame = parentJFrame;
		this.childJFrame = childJFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		childJFrame.setVisible(false);
		
		parentJFrame.setVisible(true);
		
		
		
	}

}
