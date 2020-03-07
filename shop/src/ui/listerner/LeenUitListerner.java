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

public class LeenUitListerner implements ActionListener {

	private JFrame parentJFrame;
	private JFrame childJFrame;
	private Product product;

	public LeenUitListerner(JFrame parentJFrame, JFrame childJFrame) {

		this.parentJFrame = parentJFrame;
		this.childJFrame = childJFrame;
	}

	public LeenUitListerner(JFrame parentJFrame, JFrame childJFrame, Product product) {
		this.parentJFrame = parentJFrame;
		this.childJFrame = childJFrame;
		this.product = product;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			product.leenUit();
		} catch (Exception foutboodschap) {
			JOptionPane.showMessageDialog(childJFrame, foutboodschap.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		childJFrame.dispose();
		new ShowProductView(parentJFrame, product).setVisible(true);
	}
}
