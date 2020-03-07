package domain;

public class Reaction {
	
	private String name;
	private String reaction;
	private int id;
	
	public Reaction() {
		
	}
	
	public Reaction(String name, String reaction) {
		setName(name);
		setReaction(reaction);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new DomainException("error");
		}
		
		if (name.trim().isEmpty()) {
			this.name = "anoniempje";
		}
		
		this.name = name;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		if (reaction == null || reaction.trim().isEmpty()) {
			throw new DomainException("error");
		}
		this.reaction = reaction;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

}
