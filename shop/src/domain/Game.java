package domain;

import java.util.Observable;

public class Game extends Product {
	
	public Game(String titel, String id) {
		
		super(titel, id);
		
	}


	@Override
	protected double getPrice(int dagen) {
		double prijs = 0;
		
		prijs = dagen * 3;
		
		return prijs;
	}



	
}
