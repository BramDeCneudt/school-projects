package domain;



import domain.state.Beschadigd;
import domain.state.State;
import domain.state.Uitgeleend;
import domain.state.Uitleenbaar;
import domain.state.Verwijderd;

public abstract class Product implements State {

	private String titel;
	private String id;
	private State state;
	
	private State uitleenbaar;
	private State uitgeleend;
	private State beschadigd;
	private State verwijderd;
	
	
	
	public Product(String titel, String id) {
		
		setTitel(titel);
		setId(id);
		
		uitleenbaar = new Uitleenbaar(this);
		uitgeleend = new Uitgeleend(this);
		beschadigd = new Beschadigd(this);
		verwijderd = new Verwijderd(this);
		
		state = uitleenbaar;
	}

	public String getTitel() {
		return titel;
	}

	private void setTitel(String titel) {
		
		if (titel == null || titel.trim().isEmpty()) {
			
			throw new DomainException("product - setTitel titel is fout: " + titel);
			
		}
		
		this.titel = titel;
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		
		if (id == null || id.trim().isEmpty()) {
			
			throw new DomainException("product - setId id is fout: " + titel);
			
		}
		
		this.id = id;
	}
	
	protected abstract double getPrice(int dagen);

	@Override
	public boolean equals(Object obj) {
		
		Product product = (Product) obj;
		
		return this.id.equals(product.getId()) && this.titel.equals(product.getTitel());
		
	}
	
	public void setState(State state) {
		
		if (state == null ) {
			
			throw new IllegalStateException("state is null");
			
		}
		
		this.state = state;
		
	}
	
	public void leenUit() {
		
		state.leenUit();
	}
	
	public void brengTerug(boolean isBeschadigd) {
		
		state.brengTerug(isBeschadigd);
		
	}
	
	public void verwijderd() {
		
		state.verwijderd();
		
	}
	
	public void herstel() {
		
		state.herstel();
		
	}
	
	public State getLeenUit() {
		
		return uitgeleend;
		
	}
	
	public State getVerwijderd() {
		
		return verwijderd;
		
	}
	
	public State getBeschadigd() {
		
		return beschadigd;
		
	}
	
	public State getUitleenbaar() {
		
		return uitleenbaar;
		
	}
	
	
	public State getState() {
		
		return this.state;
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

		
	
}
