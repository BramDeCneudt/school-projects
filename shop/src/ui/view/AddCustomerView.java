package ui.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Shop;
import ui.listerner.AddedCustomerListerner;
import ui.listerner.GoBackMainListerner;

public class AddCustomerView extends JFrame{

	private JPanel content = new JPanel();
	private JFrame parentFrame;
	private Shop shop;
	
	public AddCustomerView(JFrame parentFrame, Shop shop) {
		this.shop = shop;
		this.parentFrame = parentFrame;
		
		setContentPane(content);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(8, 1));
		
		JLabel firstNameLabel = new JLabel("Enter your first name: ");
		JLabel lastNameLabel = new JLabel("Enter your last name: ");
		JLabel emailLabel = new JLabel("Enter your email: ");
		JTextField firstNameTextField = new JTextField();
		JTextField lastNameTextField = new JTextField();
		JTextField emailTextField = new JTextField();
		JButton addCustomerButton = new JButton("Add customer");
		JButton goBackButton = new JButton("Go Back");
		
		content.add(firstNameLabel);
		content.add(firstNameTextField);
		
		content.add(lastNameLabel);
		content.add(lastNameTextField);
		
		content.add(emailLabel);
		content.add(emailTextField);
		
		content.add(addCustomerButton);
		content.add(goBackButton);
		
		addCustomerButton.addActionListener(new AddedCustomerListerner(parentFrame,this,shop, firstNameTextField,lastNameTextField,emailTextField));
		goBackButton.addActionListener(new GoBackMainListerner(parentFrame, this));
	}

}
