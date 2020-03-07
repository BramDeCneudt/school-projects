package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.Product;
import domain.Shop;
import ui.view.ShowProductView;
import ui.view.ShowProductsView;

public class ShowProductListerner implements ActionListener {
	
	private Shop shop;
	private JFrame parentFrame;
	
	public ShowProductListerner(JFrame parentFrame, Shop shop) {
		
		this.parentFrame = parentFrame;
		this.shop = shop;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		parentFrame.setVisible(false);
		
		String id = JOptionPane.showInputDialog(parentFrame, "Geef id van product");
		Product product;
		
		try {
			
			product = shop.getProduct(id);
			
		} catch (Exception e2) {
			product = null;
		}
		
		
		
		if (product == null) {
			
			JOptionPane.showMessageDialog(parentFrame, "Product bestaat niet", "Error", JOptionPane.ERROR_MESSAGE);
			parentFrame.setVisible(true);
			
		} else {
			
			parentFrame.setVisible(false);
			JFrame panel = new ShowProductView(parentFrame, product);
			panel.setVisible(true);	
			
		}
		

		
		
	}

}
