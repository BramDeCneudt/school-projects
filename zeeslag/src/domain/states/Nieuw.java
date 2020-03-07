package domain.states;

import domain.DomainException;
import domain.ZeeSlagSpel;

public class Nieuw implements State {
	
	private ZeeSlagSpel zeeslagSpel;
	
	public Nieuw(ZeeSlagSpel zeeslagSpel) {
		
		this.zeeslagSpel = zeeslagSpel;
		
	}

	@Override
	public void setGestart() {
		zeeslagSpel.setState(zeeslagSpel.getGestarteState());
	}

	@Override
	public void setNieuw() {
		throw new DomainException("dit mag niet");
	}

	@Override
	public void setGestopt() {
		throw new DomainException("dit mag niet");
	}

}
