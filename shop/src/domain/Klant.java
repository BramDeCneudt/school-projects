package domain;

import java.util.Random;

public class Klant {
	public String klantenNummer;
	public String naam;
	public String voornaam;
	public String email;
	
	public Klant(String naam,String voornaam,String email){
		this.setKlantenNummer();
		this.setNaam(naam);
		this.setVoornaam(voornaam);
		this.setEmail(email);
	}

	private void setEmail(String email) {
		if(email == null || email.trim().isEmpty()){
			throw new IllegalArgumentException("no valid email given");
		}
		this.email = email;
	}

	private void setVoornaam(String voornaam) {
		if(voornaam == null || voornaam.trim().isEmpty()){
			throw new IllegalArgumentException("no valid first name given");
		}
		this.voornaam = voornaam;
	}

	private void setKlantenNummer() {
		Random random = new Random();
		String nummer ="";
	    for(int i = 0; i < 12;i++){
	    	nummer += Integer.toString((random.nextInt(10) + 0)); 
	    }
	    this.klantenNummer = nummer;
	}
	
	public void setNaam(String naam){
		if(naam == null || naam.trim().isEmpty()){
			throw new IllegalArgumentException("no valid last name given");
		}
		this.naam = naam;
	}
	
	public String getKlantenNummer(){
		return klantenNummer;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public String voornaam(){
		return voornaam;
	}
	
	public String getEmail(){
		return email;
	}
}
