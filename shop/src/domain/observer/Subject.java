package domain.observer;

import java.util.ArrayList;

import domain.Product;

public interface Subject {
	
	
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers(Product product);

}
