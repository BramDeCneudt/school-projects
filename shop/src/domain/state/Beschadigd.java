package domain.state;

import domain.Product;

public class Beschadigd implements State {
	
	Product product;
	
	public Beschadigd(Product product) {
		this.product = product;
	}

	@Override
	public void verwijderd() {
		
		product.setState(product.getVerwijderd());

	}

	@Override
	public void herstel() {
		product.setState(product.getUitleenbaar());

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

	}

	@Override
	public String toString() {
		return "Beschadigd";
	}

}
