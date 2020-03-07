package ui.listerner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import domain.MailService;
import domain.Shop;
import ui.view.AddCustomerView;
import ui.view.SubscribeCustomerView;

public class SubscribeCustomerListerner implements ActionListener {

	private JFrame jFrame;
	private Shop shop;
	private MailService mailService;
	
	public SubscribeCustomerListerner(JFrame jFrame, Shop shop,MailService mailService) {
		this.jFrame = jFrame;
		this.shop = shop;
		this.mailService = mailService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jFrame.setVisible(false);
		
		SubscribeCustomerView panel = new SubscribeCustomerView(jFrame, shop,mailService);
		panel.setVisible(true);
	}

}
