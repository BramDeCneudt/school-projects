package ui;

import java.util.List;
import java.util.Observer;

import javax.swing.JPanel;

import domain.Facade;
import domain.Positie;
import domain.ZeeslagSpeler;//TODO: refactoren naar facade
import ui.view.ZeeslagFrame;

public class Controller {
	
	Facade facade;
	
	public Controller() {
		
		this.facade = new Facade();
		
	}
	/*
	 * wijst schip toe aan vakobjecten
	 */
	public void setSchip(String schipString, String richtingString, Positie positie, int speler) {
			facade.zetSchip(schipString, positie, richtingString, speler);
	}
	
	public void getSchip(Positie positie) {
		
	}
	
	public List<Positie> getSchepen(int speler){
		if(speler == 0){
			return facade.getSchepenPositiesSpeler1();
		} else {
			return facade.getSchepenPositiesSpeler2();
		}
	}
	
	public void setSpelers(String naam1, String naam2) {
		
		facade.setSpelers(naam1, naam2);
	}
	
	public String getSpeler1Naam() {
		return facade.getSpeler1Naam();
	}
	
	public String getSpeler2Naam() {
		return facade.getSpeler2Naam();	
	}
	
	public void start() {
		
		ZeeslagFrame zeeslag = new ZeeslagFrame(this);
		zeeslag.setVisible(true);
	}
	
	public void AIPlaatsSchip(){
		facade.AIPlaatsSchip();
	}
	public void raakVakjeSpeler1(Positie positie) {
		facade.raakVakjeSpeler1(positie);
	}
	public List<Positie> getHitsSpeler1() {
		return facade.getHitsSpeler1();
	}
	
	public void startSpel() {
		facade.startSpel();
	}
	public List<Positie> getHitsSpeler2() {
		return facade.getHitsSpeler2();
	}
	public boolean isSchipKapot(Positie pos,int speler) {
		return facade.isShipKapot(pos, speler);
	}
	
	public void AIValAan(){
		facade.AIValAan();
	}
	
	public int getBerekendeScoreSpeler1() {
		return this.facade.getBerekendeScoreSpeler1();
	}
	public int getBerekendeScoreSpeler2() {
		return this.facade.getBerekendeScoreSpeler2();
	}
	
	public ZeeslagSpeler isSpelGedaan(){
		return facade.isSpelGedaan();
	}
	
	public void reset(){
		this.facade.reset();
	}
	public void addObserver(Observer view) {
		facade.addObserver(view);
	}
	public void setSpelGestopt() {
		facade.setSpelGestopt();
	}
	public boolean isSpelGestopt() {
		return facade.isSpelGestopt();
	}
	
	public void setNieuwSpel() {
		this.facade.setNieuwSpel();
	}
	
}
