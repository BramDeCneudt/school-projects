package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import domain.observer.Observer;
import domain.observer.Subject;

public class Shop implements Subject {
	
	private Set<Observer> observers;
	private Map<String,Klant> klanten;
	private Map<String, Product> producten;

	public Shop() {
		observers = new HashSet<>();
		producten = new HashMap<>();
		klanten = new HashMap<>();
	}
	
	public Product getProduct(String id) {
		
		if (id == null || id.trim().isEmpty()) {
			
			throw new DomainException("getProduct - id: " + id);
			
		}
		
		return producten.get(id);
		
	}
	
	public Map<String,Product> getProductsMap() {
		
		return this.producten;
		
	}
	
	public Collection<Product> getProducts() {
		
		return this.producten.values();
		
	}
	
	public void addProduct(Product product) {
		
		if (product == null || !(product instanceof Product) )  {
			
			throw new IllegalArgumentException("shit");
			
		}
		producten.put(product.getId(), product);
		this.notifyObservers(product);
		
	}
	
	
	public double getPrice(String id, int dagen) {
		
		Product product = producten.get(id);
		return product.getPrice(dagen);
		
	}

	@Override
	public void registerObserver(Observer observer) {
		
		if (observer == null || observers.contains(observer)) {
			
			throw new IllegalArgumentException("fout in observer " + observer);
			
		}

		observers.add(observer);
		
	}

	@Override
	public void removeObserver(Observer observer) {
		
		if (observer == null) {
			
			throw new IllegalArgumentException("fout in observer " + observer);
			
		}
		
		observers.remove(observer);
		
	}


	@Override
	public void notifyObservers(Product product) {
		String message = "Er is een nieuw product: " + product.getTitel();
		
		for (Observer observer : observers) {
			
			observer.update(message);
			
		}
		
	}

	public void addCustomer(Klant klant) {
		if (klant == null || !(klant instanceof Klant))  {
			throw new IllegalArgumentException("shit");
		}	
		klanten.put(klant.getKlantenNummer(), klant);
	}
	
	public Klant getKlantWithEmail(String email){
		for(Klant k: klanten.values()){
			if(k.getEmail().equals(email)){
				return k;
			}
		}
		return null;
	}
	
	public void remove(Product product) {
		
		this.producten.remove(product.getId(), product);
		
	}
}
