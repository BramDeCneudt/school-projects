package domain.states;

import domain.DomainException;
import domain.ZeeSlagSpel;

public class Gestart implements State {
	
	
	private ZeeSlagSpel zeeslagspel;
	
	public Gestart(ZeeSlagSpel zeeslagspel) {
		
		this.zeeslagspel = zeeslagspel;
		
	}

	@Override
	public void setGestart() {
		throw new DomainException("dit mag niet");
	}

	@Override
	public void setNieuw() {
		throw new DomainException("dit mag niet");
	}

	@Override
	public void setGestopt() {
		this.zeeslagspel.setState(zeeslagspel.getGestoptState());
	}

}
