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

public class SubscribeCustomerView extends JFrame{
	
	private JPanel content = new JPanel();
	private JFrame parentFrame;
	private Shop shop;
	private MailService mailService;
	
	
	public SubscribeCustomerView(JFrame jFrame, Shop shop,MailService mailService) {
		this.parentFrame = jFrame;
		this.shop = shop;
		
		setContentPane(content);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(4, 1));
		
		JLabel emailLabel = new JLabel("email: ");
		JTextField emailTextField = new JTextField();
		JButton subscribeCustomerButton = new JButton("subscribe");
		JButton goBackButton = new JButton("Go Back");
		
		content.add(emailLabel);
		content.add(emailTextField);
		content.add(subscribeCustomerButton);
		content.add(goBackButton);
		
		subscribeCustomerButton.addActionListener(new SubscribedCustomerListerner(parentFrame,this,shop,emailTextField,mailService));
		goBackButton.addActionListener(new GoBackMainListerner(parentFrame,this));
	}

}
