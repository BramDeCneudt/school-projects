package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import domain.Shop;
import ui.view.AddCustomerView;
import ui.view.AddProductView;

public class AddCustomerListerner implements ActionListener {

	private JFrame jframe;
	private Shop shop;
	
	public AddCustomerListerner(JFrame jframe, Shop shop) {
		
		this.jframe = jframe;
		this.shop = shop;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		jframe.setVisible(false);
		
		AddCustomerView panel = new AddCustomerView(jframe, shop);
		panel.setVisible(true);
		
		
		
	}

}
