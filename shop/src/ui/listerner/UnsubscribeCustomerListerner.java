package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import domain.MailService;
import domain.Shop;
import ui.view.UnsubscribeCustomerView;

public class UnsubscribeCustomerListerner implements ActionListener {

	private JFrame jframe;
	private Shop shop;
	private MailService mailService;
	
	
	public UnsubscribeCustomerListerner(JFrame jFrame, Shop shop, MailService mailService) {
		this.jframe = jFrame;
		this.shop = shop;
		this.mailService = mailService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		UnsubscribeCustomerView panel = new UnsubscribeCustomerView(jframe, shop, mailService);
		panel.setVisible(true);

	}

}
