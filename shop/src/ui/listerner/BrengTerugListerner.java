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

public class BrengTerugListerner implements ActionListener {
	
	private JFrame parentJFrame;
	private JFrame childJFrame;
	private Product product;
	
	public BrengTerugListerner(JFrame parentJFrame, JFrame childJFrame, Product product) {
		
		this.parentJFrame = parentJFrame;
		this.childJFrame = childJFrame;
		this.product = product;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Boolean Beschadigd = Boolean.parseBoolean(JOptionPane.showInputDialog("Is het product beschadigd?"));
		try{
			product.brengTerug(Beschadigd);
		}catch(Exception foutboodschap){
			JOptionPane.showMessageDialog(childJFrame, foutboodschap.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		childJFrame.dispose();
		new ShowProductView(parentJFrame, product).setVisible(true);
	}

}
