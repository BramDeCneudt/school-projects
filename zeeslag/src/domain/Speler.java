package domain;

public abstract class Speler {
	
	private String naam;
	
	public Speler(String naam) {
		setNaam(naam);
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.trim().isEmpty()) {
			throw new DomainException("Naam is leeg of null");
		}
		this.naam = naam;
	}
	
	public abstract void reset();
	
}
