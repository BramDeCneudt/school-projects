package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import domain.Shop;
import ui.view.RentalPriceView;

public class RentalPriceListerner implements ActionListener {

	private JFrame jframe;
	private Shop shop;
	
	
	public RentalPriceListerner(JFrame jframe, Shop shop) {
		
		this.jframe = jframe;
		this.shop = shop;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		jframe.setVisible(false);
		
		RentalPriceView panel = new RentalPriceView(jframe, shop);
		panel.setVisible(true);

	}

}
