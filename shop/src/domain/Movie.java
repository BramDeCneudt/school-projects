package domain;

import java.util.Observable;

public class Movie extends Product {

	public Movie(String titel, String id) {
		super(titel, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double getPrice(int dagen) {
		double prijs = 0;
		int dagenLeft = dagen - 3;
		if (dagenLeft > 0) {
			
			prijs += (dagenLeft*2);
			
		}
		
		return prijs;
	}



	

}
