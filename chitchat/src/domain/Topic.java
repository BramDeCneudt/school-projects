package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Topic {
	
	private int id;
	private String naam;
	private String description;
	private List<Reaction> reactions;
	
	public Topic(String naam, String description) {
		setNaam(naam);
		setDescription(description);
		reactions = new ArrayList<>();
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.trim().isEmpty()) {
			throw new DomainException("error");
		}
		this.naam = naam;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || naam.trim().isEmpty()) {
			throw new DomainException("error");
		}
		this.description = description;
	}
	
	public void addReaction(Reaction reaction) {
		reactions.add(reaction);
	}
	
	public List<Reaction> getReactions() {
		return reactions;
	}
	
	public void setId(int id) {
		if (id < 0) {
			throw new DomainException("error");
		}
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

}
