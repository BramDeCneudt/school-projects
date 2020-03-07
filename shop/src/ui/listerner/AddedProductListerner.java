package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JTextField;


import domain.Game;
import domain.Movie;
import domain.Product;
import domain.Shop;


public class AddedProductListerner implements ActionListener {
	private JFrame parentFrame;
	private JFrame childFrame;
	private Shop shop;
	private JTextField id;
	private JTextField titel;
	private ButtonGroup type;
	
	public AddedProductListerner(JFrame parentFrame, JFrame childFrame, Shop shop, JTextField id, JTextField titel, ButtonGroup typGroup) {
		
		this.parentFrame = parentFrame;
		this.childFrame = childFrame;
		this.shop = shop;
		this.id = id;
		this.titel = titel;
		this.type = typGroup;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean fout = false;
		
		
		
		
		String titelString = titel.getText();
		String idString = id.getText();
		String typeString = type.getSelection().getActionCommand();
		Product product;
		
		try {
			
			if (typeString.equals("game")) {
				
				product = new Game(titelString, idString);
				
			} else {
				
				product = new Movie(titelString, idString);
	
			}
			
			shop.addProduct(product);
			
		} catch(Exception foutboodschap) {
			
			fout = true;
			JOptionPane.showMessageDialog(childFrame, foutboodschap.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		if (!fout) {
			
			childFrame.dispose();
			parentFrame.setVisible(true);
			
		}

		
	}

}
