package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.schepen.Schip;

public class ZeeslagSpeler extends Speler {
	private Vlootbord vloot;
	private List<Schip> schepen;
	private int score;

	public ZeeslagSpeler(String naam) {
		super(naam);
		schepen = new ArrayList<>();
		vloot = new Vlootbord(10);
		score = 19;
	}
	
	@Override
	public void reset() {
		this.score = 19;
		this.schepen = new ArrayList<>();
		vloot.reset();
	}
	
	public List<Schip> getSchepen() {
		return this.schepen;
	}

	public List<Positie> getSchipVakjes() {
		return vloot.getSchipVakjes();
	}

	public List<Positie> getGeraakteVakjes() {
		return vloot.getGeraakteVakjes();
	}
	
	public int getAantalGeraakteVakjes() {
		
		return vloot.getGeraakteVakjes().size();
		
	}
	
	public int getScore() {
		return score;
	}
	//berekend eerst scoren en zet die in iv scoren en geeft die dan terug
	public int getBerekendeScore() {
		this.score = berekenScore();
		return this.score;
	}
	
	private int berekenScore() {
		
		int counterGeraakteSchepen = 0;
		
		List<Positie> geraakteVakjes = vloot.getGeraakteVakjes();
		List<Positie> schepenPosities = vloot.getSchipVakjes();
		
		for (Positie positie : geraakteVakjes) {
			
			if (schepenPosities.contains(positie)) {
				counterGeraakteSchepen++;
			}
			
		}
		return 19 - counterGeraakteSchepen;
	}

	public void zetSchip(Positie positie, Schip schip, Richting richting) {
		vloot.zetSchip(positie, schip, richting);
		this.schepen.add(schip);
	}

	public int getAantalSchepenVanSoort(String soort) {
		int count = 0;
		for (Schip schip : schepen) {
			if (schip.getType().equals(soort)) {
				count++;
			}
		}
		return count;
	}

	public void setHits(Positie positie) {
		this.vloot.setHits(positie);
	}

	public boolean isSchipKapot(Positie pos) {
		return this.vloot.isSchipKapot(pos);
	}

	public boolean isKapot() {
		boolean isKapot = true;
		if(schepen.size() == 0){
			return false;
		}
		for(Schip s: schepen){
			if(!s.isKapot()){
				isKapot = false;
			}
		}
		return isKapot;
	}

}
