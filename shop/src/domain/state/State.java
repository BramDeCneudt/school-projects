package domain.state;

public interface State {
	
	
	public void verwijderd();
	
	public void herstel();
	
	public void leenUit();
	
	public void brengTerug(boolean isBeschadigd);
	
	public void save();

	@Override
	String toString();

}
