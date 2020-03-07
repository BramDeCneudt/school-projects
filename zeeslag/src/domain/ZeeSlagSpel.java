package domain;

import java.util.List;

import domain.schepen.Schip;
import domain.states.Gestart;
import domain.states.Gestopt;
import domain.states.Nieuw;
import domain.states.State;

public class ZeeSlagSpel extends Spel {

	private State gestart;
	private State nieuw;
	private State gestopt;

	public ZeeSlagSpel() {
		this.init();
		this.gestart = new Gestart(this);
		this.nieuw = new Nieuw(this);
		this.gestopt = new Gestopt(this);
		this.currentState = nieuw;
	}

	public void reset() {
		for (ZeeslagSpeler zeeslagSpeler : spelers) {
			zeeslagSpeler.reset();
		}
		setChanged();
		notifyObservers();
	}

	public void init() {
		this.spelers = new ZeeslagSpeler[2];
	}

	public void setSpelers(ZeeslagSpeler speler1, ZeeslagSpeler speler2) {
		spelers[0] = speler1;
		spelers[1] = speler2;
	}

	public void zetSchip(Schip schip, Positie positie, Richting richting, int speler) {
		if (spelers[speler].getSchepen().size() < SchipSoorten.getTotaalAantal()) {
			if (spelers[speler].getAantalSchepenVanSoort(schip.getType()) < SchipSoorten.valueOf(schip.getType())
					.getAantal()) {
				this.spelers[speler].zetSchip(positie, schip, richting);
			} else {
				throw new DomainException("Je heb geen schepen meer van dit type");
			}
		} else {
			throw new AlleSchepenGeplaatstException("Alle schepen zijn al geplaatst.");
		}
		setChanged();
		notifyObservers();
	}

	public List<Positie> getSchepenPositiesSpeler1() {
		return this.getSpeler(0).getSchipVakjes();
	}

	public List<Positie> getSchepenPositiesSpeler2() {
		return this.getSpeler(1).getSchipVakjes();
	}

	public List<Positie> getHitsSpeler1() {
		return this.getSpeler(0).getGeraakteVakjes();
	}

	public List<Positie> gethitsSpeler2() {
		return this.getSpeler(1).getGeraakteVakjes();
	}

	public int getScoreSpeler1() {
		return this.getSpeler(0).getScore();
	}

	public int getScoreSpeler2() {
		return this.getSpeler(1).getScore();
	}

	public int getBerekendeScoreSpeler1() {
		return this.getSpeler(0).getBerekendeScore();
	}

	public int getBerekendeScoreSpeler2() {
		return this.getSpeler(1).getBerekendeScore();
	}

	public void setHitsSpeler1(Positie positie) {
		this.getSpeler(1).setHits(positie);
		setChanged();
		notifyObservers();
	}

	public State getNieuwState() {

		return this.nieuw;

	}

	public State getGestarteState() {

		return this.gestart;

	}
	public State getGestoptState() {

		return this.gestopt;

	}

	public void setState(State state) {

		if (state == null) {

			throw new DomainException("fout in zeeslagSpel");

		}

		this.currentState = state;
	}

	public State getState() {

		return this.currentState;

	}

	public void setHitsSpeler2(Positie pos) {
		this.getSpeler(0).setHits(pos);
		this.setChanged();
		this.notifyObservers();
	}

	public ZeeslagSpeler isGedaan() {
		if (this.getSpeler(0).isKapot()) {
			return this.getSpeler(1);
		}
		if (this.getSpeler(1).isKapot()) {
			return this.getSpeler(0);
		}
		return null;
	}
	
	public void notifyAllObservers(){
		this.setChanged();
		this.notifyObservers();
	}
}