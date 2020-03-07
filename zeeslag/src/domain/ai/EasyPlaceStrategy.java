package domain.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.AlleSchepenGeplaatstException;
import domain.DomainException;
import domain.Facade;
import domain.Positie;
import domain.SchipFactory;
import domain.SchipSoorten;
import domain.Speler;
import domain.ZeeslagSpeler;
import domain.schepen.Schip;

public class EasyPlaceStrategy implements PlaceStrategy {

	@Override
	public void zetSchepen(Facade facade) {
		boolean magIkPlaatsen = true;
		while (magIkPlaatsen) {
			Random r = new Random();
			try {
				int x = r.nextInt(10);
				int y = r.nextInt(10);
				Positie waarTePlaatsen = new Positie(x, y);
				String schipSoort = getRandomSchipString();
				facade.zetSchip(schipSoort, waarTePlaatsen, getRandomRichtingString(), 1);
			} catch (AlleSchepenGeplaatstException a) {
				magIkPlaatsen = false;
			}catch (DomainException e){
				
			}
		}
	}

	private String getRandomSchipString() {
		SchipSoorten[] soorten = SchipSoorten.values();
		List<String> soortenString = new ArrayList<String>();
		for (SchipSoorten soort : soorten) {
			soortenString.add(soort.toString());
		}
		Random r = new Random();
		
		int randomGetal = r.nextInt(soortenString.size());
		String schipSoortRandom =  soortenString.get(randomGetal);

		
		return schipSoortRandom;
	}

	private String getRandomRichtingString() {
		Random r = new Random();
		int getal = r.nextInt(2);
		if (getal == 0) {
			return "horizontaal";
		} else {
			return "verticaal";
		}
	}

}
