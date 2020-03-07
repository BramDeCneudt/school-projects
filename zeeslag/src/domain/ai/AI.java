package domain.ai;

import java.util.Properties;

import domain.DomainException;
import domain.Facade;
import domain.PropertiesReader;
import domain.SchipFactory;
import domain.Speler;
import domain.ZeeslagSpeler;

public class AI {
	private PlaceStrategy placestrategy;
	private GameStrategy gameStrategy;
	protected ZeeslagSpeler AI;
	protected SchipFactory schipFactory;
	protected Facade facade;
	
	public AI(Facade facade){
		this.facade = facade;
		this.setPlaceStrategy();
		this.setGameStrategy();
	}
	
	public void plaatsSchepen(){
		placestrategy.zetSchepen(facade);
	}
	
	private void setPlaceStrategy(){
		PropertiesReader propReader = new PropertiesReader();
		Properties props = propReader.getProperties();
		String placeStrategyString = ("domain.ai.") + props.getProperty("plaatsAi");
		try {
			Class pS =  Class.forName(placeStrategyString);
			Object psObject = pS.newInstance();
			this.placestrategy = (PlaceStrategy) psObject;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public void valAan(){
		gameStrategy.valAan(facade);
	}
	
	private void setGameStrategy(){
		PropertiesReader propReader = new PropertiesReader();
		Properties props = propReader.getProperties();
		String gameStrategyString = ("domain.ai.") + props.getProperty("gameStrategyAi");
		try {
			Class gS =  Class.forName(gameStrategyString);
			Object gsObject = gS.newInstance();
			this.gameStrategy = (GameStrategy) gsObject;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new DomainException(e.getMessage());
		}
	}
	
	public void reset() {
		this.gameStrategy.reset();
	}
	
}
