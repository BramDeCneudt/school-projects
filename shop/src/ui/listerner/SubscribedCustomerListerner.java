package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Klant;
import domain.MailService;
import domain.Shop;

public class SubscribedCustomerListerner implements ActionListener {
	
	private JFrame parentFrame;
	private JFrame childFrame;
	private Shop shop;
	private JTextField email;
	private MailService mailService;
	
	
	public SubscribedCustomerListerner(JFrame parentFrame, JFrame childFrame, Shop shop,JTextField emailTextField,MailService mailService) {
		this.parentFrame = parentFrame;
		this.childFrame = childFrame;
		this.shop = shop;
		this.email = emailTextField;
		this.mailService = mailService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean fout = false;
		String emailString = email.getText();
		Klant klant;
		try{
			klant = shop.getKlantWithEmail(emailString);
			mailService.addKlant(klant);
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
