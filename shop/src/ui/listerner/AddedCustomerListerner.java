package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Klant;
import domain.Shop;

public class AddedCustomerListerner implements ActionListener {

	private JFrame parentFrame;
	private JFrame childFrame;
	private Shop shop;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	
	public AddedCustomerListerner(JFrame parentFrame, JFrame childFrame, Shop shop,JTextField firstName, JTextField lastName, JTextField email) {
		this.parentFrame = parentFrame;
		this.childFrame = childFrame;
		this.shop = shop;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean fout = false;
		
		String firstNameString = firstName.getText();
		String lastNameString = lastName.getText();
		String emailString = email.getText();
		Klant klant;
		try{
			klant = new Klant(firstNameString,lastNameString,emailString);
			shop.addCustomer(klant);
		}catch(Exception foutboodschap){
			fout = true;
			JOptionPane.showMessageDialog(childFrame, foutboodschap.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(!fout){
			childFrame.dispose();
			parentFrame.setVisible(true);
		}

	}

}
