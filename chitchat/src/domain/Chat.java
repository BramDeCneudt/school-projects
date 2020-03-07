package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Chat {
	
	private String id;
	private String name;
	private HashSet<Person> persons;
	private Deque<Message> messages;
	//private HashMap<String, List<Message>> unreadMessages;
	
	public Chat() {
		id = UUID.randomUUID().toString();
		messages = new ArrayDeque<>();
		persons = new HashSet<>();
		
	}
	
	public Chat(List<Person> personsList) {
		this();
		for (Person person : personsList) {
			addPerson(person);
		}
	}
	
	public void addPerson(Person person) {
		if (!persons.contains(person)) {
			persons.add(person);
			person.addChat(this);
		}
	}
	
	public void removePerson(Person person) {
		if (persons.contains(person)) {
			persons.remove(person);
			person.removeChat(this);
		} 
	}
	
	public void addMessage(Message message) {
		messages.addLast(message);
	}
	
	public void removeMessage() {
		messages.removeFirst();
	}

	public String getId() {
		return id;
	}

	public List<Person> getPersons() {
		return new ArrayList<>(persons);
	}

	public List<Message> getMessages() {
		return new ArrayList<>(messages);
	}
	
	public String getName() {
		if (name == null || name.trim().isEmpty() ) {
			return getPersonsNames();
		}
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new DomainException("error");
		}
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Chat)) {
			return false;
		}
		Chat chat = (Chat) obj;
		return this.getId().equals(chat.getId());
	}
	
	/**
	 * @param person
	 * @return other person from the chatsession, if its not present returns null
	 */
	public List<Person> getOtherPersons(Person person) {
		
		if (persons.contains(person) ) {
			HashSet<Person> otherPersons = new HashSet<>(persons);
			otherPersons.remove(person);
			return new ArrayList<>(otherPersons);
		}
		return null;
		
	}

	
	/**
	 * @return string of all persons username seperated by comma
	 */
	private String getPersonsNames() {
		String names = "";
		for (Person person : persons) {
			names += person.getUsername() + ", ";
		}
		names = names.substring(0, names.length()-2);
		return names;
	}
	//TODO refactoren
	public boolean isRightChat(List<Person> personsList){

		if (personsList.size() == this.persons.size()) {
			for (Person person : personsList) {
				if (!(this.persons.contains(person))) {
					return false;
				}
			}
		} else {return false;}
		 return true;
	}
}

