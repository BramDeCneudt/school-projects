package domain;

import domain.schepen.Schip;

public class SchipFactory {

	public Schip getSchip(String schipType) {

		if (schipType == null || schipType.trim().isEmpty()) {
			throw new DomainException("fout bij SchipFactory-getSchip-schipType: \n" + schipType);
		}
		schipType = schipType.toUpperCase();
		Schip schip = new Schip(schipType, SchipSoorten.valueOf(schipType).getLengte());
		return schip;
	}

}
