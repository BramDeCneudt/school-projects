package domain;

import java.util.ArrayList;
import java.util.List;

import domain.schepen.Schip;

public class Vlootbord { // TODO eventueel abstracte bord klasse maken
	VakObject[][] rooster;

	public Vlootbord(int grootte) {
		rooster = new VakObject[grootte][grootte];
		for (int i = 0; i < grootte; i++) {
			for (int j = 0; j < grootte; j++) {
				rooster[i][j] = new VakObject();
			}
		}
	}

	public int getRoosterGrootte() {
		return rooster.length;
	}

	public List<Positie> getSchipVakjes() {
		List<Positie> posities = new ArrayList<Positie>();
		for (int i = 0; i < this.getRoosterGrootte(); i++) {
			for (int j = 0; j < this.getRoosterGrootte(); j++) {
				if (rooster[i][j].bevatSchip()) {
					posities.add(new Positie(i, j));
				}
			}
		}
		return posities;
	}

	public List<Positie> getGeraakteVakjes() {
		List<Positie> posities = new ArrayList<Positie>();
		for (int i = 0; i < this.getRoosterGrootte(); i++) {
			for (int j = 0; j < this.getRoosterGrootte(); j++) {
				if (rooster[i][j].isVakGeraakt()) {
					posities.add(new Positie(i, j));
				}
			}
		}
		return posities;
	}
	

	public void zetSchip(Positie positie, Schip schip, Richting richting) {
		if (zijnErGenoegVakjes(positie, schip, richting) && !ligtNaastAnderSchip(positie, schip, richting)) {
			if (richting == Richting.HORIZONTAAL) {
				for (int i = positie.getX(); i < positie.getX() + schip.getHealth(); i++) {
					rooster[i][positie.getY()].setSchip(schip);
				}
			}

			if (richting == Richting.VERTICAAL) {
				for (int i = positie.getY(); i < positie.getY() + schip.getHealth(); i++) {
					rooster[positie.getX()][i].setSchip(schip);
				}
			}
		} else {
			throw new DomainException("Dit is een ongeldige positie.");
		}
	}

	private boolean ligtNaastAnderSchip(Positie positie, Schip schip, Richting richting) {

		for (int i = 0; i < schip.getLength(); i++) {
			if (richting == Richting.HORIZONTAAL) {
				Positie tempPos = new Positie(positie.getX() + i, positie.getY());
				if (schipNaastVakje(tempPos)) {
					return true;
				}
			} else {
				Positie tempPos = new Positie(positie.getX(), positie.getY() + i);
				if (schipNaastVakje(tempPos)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean schipNaastVakje(Positie positie) {
		try {
			if (rooster[positie.getX() - 1][positie.getY() - 1].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX()][positie.getY() - 1].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX() + 1][positie.getY() - 1].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX() - 1][positie.getY()].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX() + 1][positie.getY()].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX() - 1][positie.getY() + 1].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX()][positie.getY() + 1].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (rooster[positie.getX() + 1][positie.getY() + 1].bevatSchip()) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return false;
	}

	private boolean zijnErGenoegVakjes(Positie positie, Schip schip, Richting richting) {
		int nodig = schip.getHealth();

		if (richting == Richting.HORIZONTAAL) {
			for (int i = positie.getX(); i < this.getRoosterGrootte(); i++) {
				if ((this.getRoosterGrootte() - positie.getX()) < nodig) {
					return false;
				}

				if (rooster[i][positie.getY()].bevatSchip()) {
					return false;
				}
			}
		}

		if (richting == Richting.VERTICAAL) {
			for (int i = positie.getY(); i < this.getRoosterGrootte(); i++) {
				if ((this.getRoosterGrootte() - positie.getY()) < nodig) {
					return false;
				}

				if (rooster[positie.getX()][i].bevatSchip()) {
					return false;
				}
			}
		}
		return true;
	}

	public void setHits(Positie positie) {
		rooster[positie.getX()][positie.getY()].hit();
	}

	public boolean isSchipKapot(Positie pos) {
		return rooster[pos.getX()][pos.getY()].isSchipKapot();
	}
	
	public void reset(){
		for (VakObject[] vakObjects : rooster) {
			for (VakObject vakObject : vakObjects) {
				vakObject.reset();
			}
		}
	}
}
