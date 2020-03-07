package db;

import java.util.HashMap;
import java.util.List;

import domain.Person;

public interface PersonRepository {
	
	void addPerson(Person person);
	void removePerson(String username);
	void editPerson(String username, Person person);
	
	Person getPerson(String username);
	List<Person> getPersons();
	
}
