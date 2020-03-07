package domain;

import java.util.Observable;

import domain.states.State;

public abstract class Spel extends Observable {

	protected ZeeslagSpeler[] spelers;
	protected State currentState;

	public ZeeslagSpeler getSpeler(int nummer) {
		return spelers[nummer];
	}
	
	public abstract ZeeslagSpeler isGedaan();
	public abstract void reset();
}