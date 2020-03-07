package domain.states;

import domain.DomainException;
import domain.ZeeSlagSpel;

public class Gestopt implements State {
	
	
	private ZeeSlagSpel zeeslagspel;
	
	public Gestopt(ZeeSlagSpel zeeslagspel) {
		
		this.zeeslagspel = zeeslagspel;
		
	}

	@Override
	public void setGestart() {
		throw new DomainException("dit mag niet");
	}

	@Override
	public void setNieuw() {
		this.zeeslagspel.setState(zeeslagspel.getNieuwState());
	}

	@Override
	public void setGestopt() {
		throw new DomainException("dit mag niet");
		
	}

}
