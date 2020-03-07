package ui.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.MailService;
import domain.Shop;
import ui.listerner.AddCustomerListerner;
import ui.listerner.AddProductListerner;
import ui.listerner.RentalPriceListerner;
import ui.listerner.ShowProductListerner;
import ui.listerner.ShowProductsListerner;
import ui.listerner.SubscribeCustomerListerner;
import ui.listerner.UnsubscribeCustomerListerner;

public class MainView extends JFrame{
	private JPanel content = new JPanel();
	private Shop shop = new Shop();
	private MailService mailService = new MailService(shop);
	
	public MainView(){
		setContentPane(content);
		setSize(new Dimension(200,300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton addProduct = new JButton("Add product");
		JButton showProduct = new JButton("Show product");
		JButton showProducts = new JButton("Show products");
		JButton rentalPrice = new JButton("Show rental price");
		JButton addCustomer = new JButton("Add customer");
		JButton subscribe = new JButton("Subscribe to newsletter");
		JButton unsubscribe = new JButton("Unsubscribe");
		
		content.add(addProduct);
		content.add(showProduct);
		content.add(showProducts);
		content.add(rentalPrice);
		content.add(addCustomer);
		content.add(subscribe);
		content.add(unsubscribe);
		
		addProduct.addActionListener(new AddProductListerner(this, shop));
		showProducts.addActionListener(new ShowProductsListerner(this, shop));
		showProduct.addActionListener(new ShowProductListerner(this, shop));
		rentalPrice.addActionListener(new RentalPriceListerner(this,shop));
		addCustomer.addActionListener(new AddCustomerListerner(this,shop));
		subscribe.addActionListener(new SubscribeCustomerListerner(this,shop,mailService));
		unsubscribe.addActionListener(new UnsubscribeCustomerListerner(this,shop,mailService));
	}
}
