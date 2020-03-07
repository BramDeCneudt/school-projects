package ui.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Movie;
import domain.Product;
import ui.listerner.GoBackMainListerner;

public class ShowProductsView extends JFrame{
	
	private JPanel content = new JPanel();
	private JFrame parentJFrame;
	
	private Collection<Product> products;
	
	public ShowProductsView(JFrame parentJFrame, Collection<Product> products){
		this.parentJFrame = parentJFrame;
		this.products = products;
		setContentPane(content);
		setSize(new Dimension(500,1000));
		// 1 voor de koppen van de tabel en 1 voor de goBack button
		this.setLayout(new GridLayout(products.size()+2, 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titelTabel = new JLabel("Titels");
		JLabel idTabel = new JLabel("ID\'s");
		JLabel typeTabel = new JLabel("Type");
		
		this.add(titelTabel);
		this.add(idTabel);
		this.add(typeTabel);
		
		JButton goBack = new JButton("Go Back");
		
		for (Product product : products) {
			
			JLabel titel = new JLabel(product.getTitel());
			JLabel id = new JLabel(product.getId());
			JLabel type;
			
			if (product instanceof Movie) {
				
				type = new JLabel("Movie");
				
			} else {
				
				type = new JLabel("Game");
				
			}
			
			this.add(titel);
			this.add(id);
			this.add(type);
			
		}

		goBack.addActionListener(new GoBackMainListerner(parentJFrame, this));
		content.add(goBack);
		
	}
}
