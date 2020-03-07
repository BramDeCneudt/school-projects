package domain;

import domain.schepen.Schip;

public class VakObject {
	private boolean geraakt;
	private Schip schip = null;

	public void setSchip(Schip schip) {
		this.schip = schip;
	}

	public Schip getSchip() {
		return this.schip;
	}

	public boolean bevatSchip() {
		return schip != null;
	}
	
	public boolean isSchipKapot(){
		return (this.schip.getHealth() == 0);
	}

	public boolean isVakGeraakt() {
		return geraakt;
	}

	public void hit() {
		this.geraakt = true;
		if (this.bevatSchip()) {
			this.schip.hit();
		}
	}
	
	public void reset(){
		this.schip = null;
		this.geraakt = false;
	}
}
