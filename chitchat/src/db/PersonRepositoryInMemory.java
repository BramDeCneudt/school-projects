package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.DomainException;
import domain.Person;

public class PersonRepositoryInMemory implements PersonRepository {
	
	private static final HashMap<String, Person> persons = new HashMap<>();;
	
	public PersonRepositoryInMemory() {
		
	}
	
	
	@Override
	public void addPerson(Person person) {
		if (person == null) {
			throw new DomainException("error: " + person);
		}
		if (persons.containsKey(person.getUsername())) {
			throw new DomainException("persoon bestaat al!");
		}
		persons.put(person.getUsername(), person);
	}

	@Override
	public void removePerson(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new DomainException("Error");
		}
		
		persons.remove(username);
	}

	@Override
	public void editPerson(String username, Person person) {
		if (username == null || username.trim().isEmpty()) {
			throw new DomainException("Error");
		}
		this.persons.put(username, person);
	}

	@Override
	public Person getPerson(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new DomainException("Error");
		}
		return this.persons.get(username);
	}

	@Override
	public List<Person> getPersons() {
		return new ArrayList<>(persons.values());
	}

	
	
	

}
