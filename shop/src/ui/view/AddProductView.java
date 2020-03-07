package ui.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import domain.Shop;
import domain.enums.ProductTypes;
import ui.listerner.AddedProductListerner;
import ui.listerner.GoBackMainListerner;

public class AddProductView extends JFrame  {

	private JPanel content = new JPanel();
	private JFrame parentFrame;
	private Shop shop;
	
	public AddProductView(JFrame parentFrame, Shop shop) {
		this.shop = shop;
		this.parentFrame = parentFrame;
		
		setContentPane(content);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(8, 1));
		
		JLabel idLabel = new JLabel("id: ");
		JLabel titelLabel = new JLabel("Titel: ");
		
		JTextField idTextField = new JTextField();
		JTextField titelTextField = new JTextField();
		
		JButton addProductButton = new JButton("add product");
		JButton goBack = new JButton("Go Back");
		
		ButtonGroup group = new ButtonGroup();
		
		
		
		
		this.add(idLabel);
		this.add(idTextField);
		
		this.add(titelLabel);
		this.add(titelTextField);

		
		ProductTypes[] producttypes = ProductTypes.values();
		
		for (ProductTypes producttype : producttypes) {
			
			JRadioButton type = new JRadioButton(producttype.toString().toLowerCase());
			type.setActionCommand(producttype.toString().toLowerCase());
			
			group.add(type);
			
			this.add(type);
		}
		
		
		this.add(addProductButton);
		this.add(goBack);
		
		addProductButton.addActionListener(new AddedProductListerner(parentFrame, this, shop, idTextField, titelTextField, group));
		goBack.addActionListener(new GoBackMainListerner(parentFrame, this));
	}
	
	
}
