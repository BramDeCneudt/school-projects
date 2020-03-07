package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.DomainException;
import domain.Person;

public class FriendRepositoryInMemory implements FriendRepository {
	
	private static final Map<String, Person> friends = new HashMap<>();
	
	public FriendRepositoryInMemory() {
	}

	@Override
	public void addFriend(Person person) {
		if (person == null) {
			throw new DomainException("error");
		}
		friends.put(person.getUsername(), person);
		
	}

	@Override
	public void removeFriend(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new DomainException("error");
		}
		friends.remove(username);
	}

	@Override
	public List<Person> getFriends() {
		// TODO Auto-generated method stub
		return new ArrayList<>(friends.values());
	}

	@Override
	public Person getFriend(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new DomainException("Error");
		}
		return friends.get(username);
	}

	@Override
	public boolean checkFriend(String username) {
		return friends.containsKey(username);
	}

}
