package ui.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Movie;
import domain.Product;
import ui.listerner.BrengTerugListerner;
import ui.listerner.GoBackMainListerner;
import ui.listerner.LeenUitListerner;
import ui.listerner.VerwijderListerner;

public class ShowProductView extends JFrame{
	private JPanel content = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel totalPanel = new JPanel();
	private Product product;
	private JFrame parentJFrame;
	
	public ShowProductView(JFrame parentJFrame, Product product){
		this.parentJFrame = parentJFrame;
		this.product = product;
		setContentPane(totalPanel);
		setSize(new Dimension(200,300));
		this.setLayout(new GridLayout(2,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titelKolom = new JLabel("titel");
		JLabel idKolom = new JLabel("id");
		JLabel typeKolom = new JLabel("type");
		JLabel stateKolom = new JLabel("state");
		
		content.setLayout(new GridLayout(2,4));
		
		content.add(titelKolom);
		content.add(idKolom);
		content.add(typeKolom);
		content.add(stateKolom);
		
		JLabel titel = new JLabel(product.getTitel());
		JLabel id = new JLabel(product.getId());
		JLabel state = new JLabel(product.getState().toString());
		
		JLabel type;
		
		if (product instanceof Movie) {
			
			type = new JLabel("movie");
			
		} else {
			
			type = new JLabel("game");
			
		}
		
		content.add(titel);
		content.add(id);
		content.add(type);
		content.add(state);

		JButton goBack = new JButton("Go Back");
		JButton leenUit = new JButton("Leen Uit");
		JButton brengTerug = new JButton("Breng Terug");
		JButton herstel = new JButton("Herstel");
		JButton verwijder = new JButton("Verwijder");
		goBack.addActionListener(new GoBackMainListerner(parentJFrame, this));
		buttonPanel.setLayout(new GridLayout(1,5));
		buttonPanel.add(goBack);
		buttonPanel.add(leenUit);
		buttonPanel.add(brengTerug);
		buttonPanel.add(herstel);
		buttonPanel.add(verwijder);
		
		
		leenUit.addActionListener(new LeenUitListerner(parentJFrame, this,product));
		brengTerug.addActionListener(new BrengTerugListerner(parentJFrame,this,product));
		verwijder.addActionListener(new VerwijderListerner(parentJFrame, this,product));
		
		this.add(content);
		this.add(buttonPanel);
		
	}
}
