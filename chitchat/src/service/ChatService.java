package service;

import java.util.ArrayList;
import java.util.List;

import db.ChatRepository;
import db.ChatRepositoryInMemory;
import db.PersonRepository;
import db.PersonRepositoryInMemory;
import db.TopicRepositoryInMemory;
import domain.Chat;
import domain.DomainException;
import domain.Message;
import domain.Person;
import domain.Reaction;
import domain.Topic;

public class ChatService implements Service {
	
	public PersonRepository persons;
	public TopicRepositoryInMemory topics;
	public ChatRepositoryInMemory chats;
	public static boolean isInited = false;
	
	public ChatService() {
		persons = new PersonRepositoryInMemory();
		topics = new TopicRepositoryInMemory();
		chats = new ChatRepositoryInMemory();
	}
	
	@Override
	public void init() {
		
		if (isInited) {
			return;
		} else {
			isInited = true;
		}
		
		
		Person person = new Person("admin", "root");
		Person personA = new Person("brakke", "root");
		Person personB = new Person("yarno", "root");
		Person personC = new Person("farri", "root");
		
		this.addPerson(person);
		this.addPerson(personA);
		this.addPerson(personB);
		this.addPerson(personC);
		
		person.addFriend(personA);
		person.addFriend(personB);
		person.addFriend(personC);
		
		Chat chat = new Chat();
		Person person1 = persons.getPerson("brakke");
		Person person2 = persons.getPerson("admin");
		person1.addChat(chat);
		person2.addChat(chat);
		Message message = new Message("test", person1);
		chat.addMessage(message);
		chat.addPerson(person1);
		chat.addPerson(person2);
		chats.addChat(chat);
		
		Topic topic1 = new Topic("Mooi weer Vandaag", "Historische weer vandaag");
		Topic topic2 = new Topic("Ongeval op E40", "Sinds blablalballllalb");
		Topic topic3 = new Topic("tester", "geen idee");
		
		Reaction reactie = new Reaction("test", "reactie test");
		topic1.addReaction(reactie);
		
		this.addTopic(topic1);
		this.addTopic(topic2);
		this.addTopic(topic3);
		
		
	}
	@Override
	public void addPerson(Person person) {
		this.persons.addPerson(person);
	}
	@Override
	public void removePerson(String username) {
		this.persons.removePerson(username);
	}
	@Override
	public void editPerson(String username, Person person) {
		this.persons.editPerson(username, person);
	}
	@Override
	public Person getPerson(String username) {
		return this.persons.getPerson(username);
	}
	@Override
	public List<Person> getPersons() {
		return persons.getPersons();
	}
	//TODO refactoren
	@Override
	public boolean authenticateUser(String username, String password) {
		if (this.persons.getPerson(username) != null) {
		return this.persons.getPerson(username).isCorrectPassword(password);
		} else {return false;}
	}
	@Override
	public void addTopic(Topic topic) {
		topics.addTopic(topic);		
	}
	@Override
	public void removeTopic(int id) {
		topics.removeTopic(id);
		
	}
	@Override
	public Topic getTopic(int id) {
		return topics.getTopic(id);
	}
	@Override
	public List<Topic> getTopics() {
		return topics.getTopics();
	}
	@Override
	public Chat getChat(String id) {
		return chats.getChat(id);
	}
	@Override
	public void startNewChat(Chat chat, List<Person> persons) {
		for (Person person : persons) {
			chat.addPerson(person);
		}
		chats.addChat(chat);
	}
	@Override
	public void removeChat(String id) {
		chats.removeChat(id);
	}
	@Override
	public List<Chat> getChatsFromPerson(String username) {
		return persons.getPerson(username).getChats();
	}
	@Override
	public Chat getChat(Person owner, List<Person> persons) {
		return owner.getRightChat(persons);
	}
	@Override
	public void addChat(Chat chat) {
		chats.addChat(chat);
	}

}
