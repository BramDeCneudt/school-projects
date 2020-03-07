package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"password", "friends", "chats"})
public class Person {
	
	private String username;
	private String password;
	private String status; 
	private String email;
	private boolean isMale;
	public 	int birthyear;
	private HashSet<Person> friends;
	private HashSet<Chat> chats;
	private boolean online;
	
	public Person(String username, String password) {
		setUsername(username);
		setPassword(password);
		setStatus("offline");
		friends = new HashSet<>();
		chats = new HashSet<>();
	}
	
	public Person(String username, String password, String email, boolean isMale, int birthyear) {
		this(username, password);
		setEmail(email);
		setMale(isMale);
		setBirthyear(birthyear);
		
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status == null || status.trim().isEmpty()) {
			throw new DomainException(status);
		}
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new DomainException(password);
		}
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new DomainException("error:" + username);
		}
		this.username = username;
	}
	
	public void addFriend(Person person) {
		if (this.equals(person)) {
			throw new DomainException("Je mag niet uw eigen toevoegen, silly!");
		}
		friends.add(person);
		if (!person.hasFriend(this)) {
			person.addFriend(this);
		}
	}
	public void removeFriend(Person person) {
		friends.remove(person);
	}
	
	public List<Person> getFriends() {
		return new ArrayList<>(friends);
	}
	public boolean hasFriend(Person person) {
		return friends.contains(person);
	}
	
	public boolean isCorrectPassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new DomainException("Error");
		}
		return this.password.equals(password);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Person) ) {
			throw new DomainException("error");
		}
		Person otherPerson = (Person) obj;
		return this.username.equals(otherPerson.getUsername());
	}
	
	public List<Chat> getChats(){
		return new ArrayList<>(chats);
	}
	
	public void addChat(Chat chat) {
		if (chat == null) {
			throw new DomainException("error");
		}
		chats.add(chat);
	}
	public void removeChat(Chat chat) {
		if (chat == null) {
			throw new DomainException("error");
		}
		chats.remove(chat);
	}
	public boolean hasChat(Chat chat) {
		return chats.contains(chat);
	}


	public boolean isOnline() {
		return online;
	}


	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public Chat getRightChat(List<Person> persons) {
		for (Chat chat : chats) {
			if(chat.isRightChat(persons)) {
				return chat;
			}
		}
		return new Chat(persons);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public void setFriends(HashSet<Person> friends) {
		this.friends = friends;
	}

	public void setChats(HashSet<Chat> chats) {
		this.chats = chats;
	}
	
}
