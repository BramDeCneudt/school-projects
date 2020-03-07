package domain;

import java.util.List;
import java.util.Observer;

import javax.swing.JPanel;

import domain.ai.AI;
import domain.schepen.Schip;
import domain.states.Gestopt;
import domain.states.State;

public class Facade {
	SchipFactory schipFactory;
	ZeeSlagSpel spel;
	AI ai = new AI(this);

	public Facade() {
		init();
	}
	
	public void init(){
		schipFactory = new SchipFactory();
		spel = new ZeeSlagSpel();
	}
										//speler 1 = nummer 0!!!!!!
	public void zetSchip(String schipType, Positie positie, String richting, int speler) {
		//factory in facade of in ZeeSlagSpel?
		Schip teZettenSchip = schipFactory.getSchip(schipType);
		Richting richtingEnum = Richting.valueOf(richting.toUpperCase());
	
		spel.zetSchip(teZettenSchip, positie, richtingEnum, speler);
	}
	
	public List<Positie> getSchepenPositiesSpeler1(){
		return this.spel.getSchepenPositiesSpeler1();
	}

	public List<Positie> getSchepenPositiesSpeler2(){
		return this.spel.getSchepenPositiesSpeler2();
	}
	
	public List<Positie> getHitsSpeler1(){
		return this.spel.getHitsSpeler1();
	}
	
	public List<Positie> getHitsSpeler2(){
		return this.spel.gethitsSpeler2();
	}
	
	public int getBerekendeScoreSpeler1() {
		return this.spel.getBerekendeScoreSpeler1();
	}
	public int getBerekendeScoreSpeler2() {
		return this.spel.getBerekendeScoreSpeler2();
	}
	
	public void setSpelers(String naam1, String naam2) {
		
		ZeeslagSpeler speler1 = new ZeeslagSpeler(naam1);
		ZeeslagSpeler speler2 = new ZeeslagSpeler(naam2);
		
		spel.setSpelers(speler1, speler2);
	}
	
	public String getSpeler1Naam() {
		return spel.getSpeler(0).getNaam();
	}
	
	public String getSpeler2Naam() {
		return spel.getSpeler(1).getNaam();
	}

	public void raakVakjeSpeler1(Positie positie) {
		spel.setHitsSpeler1(positie);
	}

	public void AIPlaatsSchip(){
		ai.plaatsSchepen();
	}
	
	public void startSpel() {
		
		spel.getState().setGestart();
		
	}
	
	public State getState() {
		
		return spel.getState();
		
	}
	

	public boolean isShipKapot(Positie pos, int speler) {
		return this.spel.getSpeler(speler).isSchipKapot(pos);
	}

	public void raakVakjeSpeler2(Positie pos) {
		spel.setHitsSpeler2(pos);
	}

	public void AIValAan() {
		if(!isSpelGestopt()){
			ai.valAan();
		}
	}

	public ZeeslagSpeler isSpelGedaan() {
		return spel.isGedaan();
	}
	public boolean isSpelGestopt() {
		return spel.getState() instanceof Gestopt;
	}
	
	public void reset(){
		this.spel.reset();
	}

	public void addObserver(Observer view) {
		this.spel.addObserver(view);
	}

	public void setSpelGestopt() {
		this.spel.getState().setGestopt();
		this.spel.notifyAllObservers();
	}
	
	public void setNieuwSpel(){
		this.reset();
		this.ai.reset();
		this.spel.getState().setNieuw();
	}
}
