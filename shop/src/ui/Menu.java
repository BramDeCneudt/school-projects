//package ui;
//
//import javax.swing.JOptionPane;
//
//import domain.Game;
//import domain.Movie;
//import domain.Product;
//import domain.ShopTest;
//
//public class Menu {
//	
//	private ShopTest shop;
//	
//	public Menu() {
//		
//		
//		
//	}
//	
//	public void showMenu() {
//		
//		shop = new ShopTest();
//		
//		String menu = "1. Add product \n2. Show product \n3. Show rental price \n\n0. Quiter";
//		int keuze = -1;
//		
//		while (keuze != 0) {
//			
//			String keuzeString = JOptionPane.showInputDialog(menu);
//			keuze = Integer.parseInt(keuzeString);
//			
//			switch (keuze) {
//			case 1:
//				shop.addProduct(this.maakProduct());
//				break;
//			case 2:
//				String id = JOptionPane.showInputDialog("geef id");
//				JOptionPane.showMessageDialog(null, shop.getProduct(id).getTitel() );
//				break;
//			case 3:
//				String id1 = JOptionPane.showInputDialog("geef id");
//				String dagenString = JOptionPane.showInputDialog("geef aantal dagen");
//				int dagen = Integer.parseInt(dagenString);
//				JOptionPane.showMessageDialog(null, shop.getPrice(id1, dagen));
//				break;
//			}
//			
//		}
//		
//	}
//	
//	private Product maakProduct() {
//		
//		String titel = JOptionPane.showInputDialog("geef Titel");
//		String id = JOptionPane.showInputDialog("geef id");
//		String type = JOptionPane.showInputDialog("geef type \n(G)ame of (M)ovie");
//		
//		Product product = null;
//		
//		if (type.equals("G")) {
//			product = new Game(titel, id);
//			
//		}
//		
//		if (type.equals("M")) {
//			
//			product = new Movie(titel, id);
//			
//		}
//		
//		 return product;
//		
//	}
//
//}
