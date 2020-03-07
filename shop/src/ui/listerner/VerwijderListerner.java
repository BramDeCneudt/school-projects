package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.Product;
import domain.Shop;
import ui.view.AddProductView;
import ui.view.MainView;
import ui.view.ShowProductView;

public class VerwijderListerner implements ActionListener {
	
	private JFrame parentJFrame;
	private JFrame childJFrame;
	private Shop shop;
	private Product product;
	
	public VerwijderListerner(JFrame parentJFrame, JFrame childJFrame, Product product) {
		
		this.parentJFrame = parentJFrame;
		this.childJFrame = childJFrame;
		this.shop = shop;
		this.product = product;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			product.verwijderd();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(childJFrame, "Product bestaat niet", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		childJFrame.dispose();
		childJFrame = new ShowProductView(parentJFrame, product);
		childJFrame.setVisible(true);
		
		
		
	}

}
