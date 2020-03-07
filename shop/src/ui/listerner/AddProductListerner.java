package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import domain.Shop;
import ui.view.AddProductView;
import ui.view.MainView;

public class AddProductListerner implements ActionListener {
	
	private JFrame jframe;
	private Shop shop;
	
	public AddProductListerner(JFrame jframe, Shop shop) {
		
		this.jframe = jframe;
		this.shop = shop;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		jframe.setVisible(false);
		
		AddProductView panel = new AddProductView(jframe, shop);
		panel.setVisible(true);
		
		
		
	}

}
