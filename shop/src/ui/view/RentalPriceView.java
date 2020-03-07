package ui.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Shop;
import ui.listerner.GoBackMainListerner;
import ui.listerner.ShowedRentalPriceListerner;

public class RentalPriceView extends JFrame{
	
	private JPanel content = new JPanel();
	private JFrame parentFrame;
	private Shop shop;
	
	public RentalPriceView(JFrame parentFrame, Shop shop){
		this.parentFrame = parentFrame;
		this.shop = shop;
		
		setContentPane(content);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(6, 1));
		
		JLabel idLabel = new JLabel("Enter the id");
		JTextField idTextField = new JTextField();
		JLabel dagenLabel = new JLabel("Enter the amount of days");
		JTextField dagenTextField = new JTextField();
		JButton ShowRentalPriceButton = new JButton("Show rental price");
		JButton goBack = new JButton("Go Back");
		
		this.add(idLabel);
		this.add(idTextField);
		
		this.add(dagenLabel);
		this.add(dagenTextField);
		
		this.add(ShowRentalPriceButton);
		this.add(goBack);

		ShowRentalPriceButton.addActionListener(new ShowedRentalPriceListerner(parentFrame,this,shop,idTextField,dagenTextField));
		goBack.addActionListener(new GoBackMainListerner(parentFrame, this));
	}

}
