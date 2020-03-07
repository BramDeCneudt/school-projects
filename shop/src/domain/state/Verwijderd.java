package domain.state;

import domain.Product;

public class Verwijderd implements State {
	
	Product product;
	
	public Verwijderd(Product product) {
		this.product = product;
	}

	@Override
	public void verwijderd() {
		throw new IllegalStateException("kan niet verwijderd worden");
		
	}

	@Override
	public void herstel() {
		throw new IllegalStateException("kan niet hersteld worden");
		
	}

	@Override
	public void leenUit() {
		throw new IllegalStateException("kan niet uitgeleend worden");
		
	}

	@Override
	public void brengTerug(boolean isBeschadigd) {
		throw new IllegalStateException("kan niet teruggebracht worden");
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "verwijderd";
	}

}
