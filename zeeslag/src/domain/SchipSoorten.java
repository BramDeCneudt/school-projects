package domain;

public enum SchipSoorten {
	
	VLIEGDEKSCHIP(1,5),
	SLAGSCHIP(2,4),
	ONDERZEEER(3,3),
	TORPEDOBOOTJAGER(3,3),
	PATROUILLESCHIP(4,2);
	
	private int aantal;
	private int lengte;
	
	private SchipSoorten(int aantal, int lengte) {
		this.aantal = aantal;
		this.lengte = lengte;
	}
	public static int getTotaalAantal(){
		return 5;
	}
	
	public int getAantal() {
		return this.aantal;
	}
	
	public int getLengte(){
		return this.lengte;
	}


}
