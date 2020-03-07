package domain;

public class Positie {
	private int x;
	private int y;
	
	public Positie(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Positie)){
			return false;
		}
		Positie pos = (Positie) obj;
		return (this.getX() == pos.getX() && this.getY() == pos.getY());
	}
	
	
}
