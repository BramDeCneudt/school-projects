package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Klant;
import domain.MailService;
import domain.Shop;

public class UnsubscribedCustomerListerner implements ActionListener {

	private JFrame parentFrame;
	private JFrame childFrame;
	private Shop shop;
	private MailService mailService;
	private JTextField email;
	
	public UnsubscribedCustomerListerner(JFrame parentFrame, JFrame childFrame, Shop shop,JTextField emailTextField, MailService mailService) {
		this.parentFrame = parentFrame;
		this.childFrame = childFrame;
		this.shop = shop;
		this.mailService = mailService;
		this.email = emailTextField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean fout = false;
		
		String emailString = email.getText();
		Klant klant;
		try{
			klant = shop.getKlantWithEmail(emailString);
			mailService.removeKlant(klant);
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
