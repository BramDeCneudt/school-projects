package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JFrame;

import domain.Product;
import domain.Shop;
import ui.view.ShowProductsView;

public class ShowProductsListerner implements ActionListener {
	
	private Shop shop;
	private JFrame parentFrame;
	
	public ShowProductsListerner(JFrame parentFrame, Shop shop) {
		
		this.parentFrame = parentFrame;
		this.shop = shop;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		parentFrame.setVisible(false);
		Collection<Product> products = shop.getProducts();
		ShowProductsView panel = new ShowProductsView(parentFrame, products);
		parentFrame.setVisible(false);
		panel.setVisible(true);
		
		
	}

}
