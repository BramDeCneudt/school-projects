package ui.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.MailService;
import domain.Shop;
import ui.listerner.GoBackMainListerner;
import ui.listerner.SubscribedCustomerListerner;
import ui.listerner.UnsubscribedCustomerListerner;

public class UnsubscribeCustomerView extends JFrame{
	
	private JPanel content = new JPanel();
	private JFrame parentFrame;
	private Shop shop;
	private MailService mailService;

	public UnsubscribeCustomerView(JFrame jframe, Shop shop, MailService mailService) {
		this.parentFrame = jframe;
		this.shop = shop;
		this.mailService = mailService;
		
		setContentPane(content);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(4, 1));
		
		JLabel emailLabel = new JLabel("email: ");
		JTextField emailTextField = new JTextField();
		JButton unsubscribeCustomerButton = new JButton("Unsubscribe");
		JButton goBackButton = new JButton("Go Back");
		
		content.add(emailLabel);
		content.add(emailTextField);
		content.add(unsubscribeCustomerButton);
		content.add(goBackButton);
		
		unsubscribeCustomerButton.addActionListener(new UnsubscribedCustomerListerner(parentFrame,this,shop,emailTextField,mailService));
		goBackButton.addActionListener(new GoBackMainListerner(parentFrame,this));
		
	}

}
