package domain.schepen;

public class Schip {
	private int health;
	private int length;
	private String type;
	
	public Schip(String type, int lengte){
		this.type = type;
		this.setLength(lengte);
	}
	
	public int getHealth() {
		return this.health;
	}
	
	private void setHealth(int health) {
		this.health = health;
	}
	
	private void setLength(int length){
		this.length = length;
		this.setHealth(length);
	}
	
	public void hit(){
		this.health--;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public String getType(){
		return this.type;
	}

	public boolean isKapot() {
		if(this.health == 0){
			return true;
		}
		return false;
	}
}
