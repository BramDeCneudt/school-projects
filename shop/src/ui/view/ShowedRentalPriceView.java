package ui.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.listerner.GoBackMainListerner;

public class ShowedRentalPriceView extends JFrame{

	private JPanel content = new JPanel();
	private JFrame parentFrame;
	private JFrame childFrame;
	private double price;
	
	public ShowedRentalPriceView(JFrame parentFrame,JFrame childFrame, double price) {
		this.parentFrame = parentFrame;
		this.childFrame = childFrame;
		this.price = price;
		
		setContentPane(content);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(3, 1));
		
		JLabel RentalPriceLabel = new JLabel("The rental price is: ");
		JLabel priceLabel = new JLabel(Double.toString(price));
		JButton goBackLabel = new JButton("Go back");
		
		content.add(RentalPriceLabel);
		content.add(priceLabel);
		content.add(goBackLabel);
		
		goBackLabel.addActionListener(new GoBackMainListerner(parentFrame,this));
	}
	
}
