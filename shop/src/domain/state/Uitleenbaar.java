package domain.state;

import domain.Product;

public class Uitleenbaar implements State {
	
	Product product;
	
	public Uitleenbaar(Product product) {
		
		if (product == null) {
			
			throw new IllegalArgumentException("product is NULL");
			
		}
		
		this.product = product;
		
	}

	@Override
	public void verwijderd() {
		
		product.setState(product.getVerwijderd());
		
	}

	@Override
	public void herstel() {
		
		throw new IllegalStateException("dit kan niet hersteld worden");
		
	}

	@Override
	public void leenUit() {
		
		product.setState(product.getLeenUit());
		
	}

	@Override
	public void brengTerug(boolean isBeschadigd) {
		
		throw new IllegalStateException("dit kan niet teruggebracht worden");
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "uitleenbaar";
	}

}
