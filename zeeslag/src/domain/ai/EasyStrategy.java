package domain.ai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import domain.Facade;
import domain.Positie;

public class EasyStrategy implements GameStrategy {

	private HashMap<String, Positie> posities = new HashMap();

	@Override
	public void valAan(Facade facade) {

		Random r = new Random();
		int x = r.nextInt(10);
		int y = r.nextInt(10);
		Positie pos = new Positie(x, y);

		while (posities.containsKey(Integer.toString(pos.getX()) + Integer.toString(pos.getY()))) {
			r = new Random();
			x = r.nextInt(10);
			y = r.nextInt(10);
			pos = new Positie(x, y);
		}
		posities.put(Integer.toString(pos.getX()) + Integer.toString(pos.getY()), pos);
		facade.raakVakjeSpeler2(pos);
	}

	@Override
	public void reset() {
		posities = new HashMap<>();
	}
	
}
