package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Shop;
import ui.view.ShowedRentalPriceView;

public class ShowedRentalPriceListerner implements ActionListener{
	private JFrame parentFrame;
	private JFrame childFrame;
	private Shop shop;
	private JTextField id;
	private JTextField dagen;
	
	public ShowedRentalPriceListerner(JFrame parentFrame,JFrame childFrame, Shop shop, JTextField id, JTextField dagen){
		this.parentFrame = parentFrame;
		this.childFrame = childFrame;
		this.shop = shop;
		this.id = id;
		this.dagen = dagen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String idString = id.getText();
		int dagenInt = Integer.parseInt(dagen.getText());
		double price = 0;
		try{
		price = shop.getPrice(idString, dagenInt);
		}catch(Exception foutboodschap){
			JOptionPane.showMessageDialog(childFrame, foutboodschap.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		ShowedRentalPriceView panel = new ShowedRentalPriceView(parentFrame,childFrame, price);
		childFrame.dispose();
		panel.setVisible(true);
		
	}
}
