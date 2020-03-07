package db;

import java.util.List;

import domain.Person;

public interface FriendRepository {
	
	void addFriend(Person person);
	void removeFriend(String username);
	
	List<Person> getFriends();
	Person getFriend(String username);
	
	boolean checkFriend(String username);
	

}
