package domain;

import java.util.HashMap;
import java.util.Map;

import domain.observer.Observer;

public class MailService implements Observer  {
	
	private Map<String,Klant> klanten;
	private Shop shop;
	
	public MailService(Shop shop){
		this.klanten = new HashMap<>();
		this.shop = shop;
		shop.registerObserver(this);
	}
	

	public void send(String message) {
		for(Klant k: klanten.values()){
			System.out.println("Beste "+ k.getNaam() + ":\n" + message);
		}
		
	}


	@Override
	public void update(String message) {
		
		this.send(message);
		
	}


	public void addKlant(Klant klant) {
		if(klant == null || !(klant instanceof Klant)){
			throw new IllegalArgumentException("Klant is not valid");
		}
		klanten.put(klant.getKlantenNummer(), klant);
	}


	public void removeKlant(Klant klant) {
		if(klant == null || !(klant instanceof Klant)){
			throw new IllegalArgumentException("Klant is not valid");
		}
		klanten.remove(klant.getKlantenNummer());
	}

}
