package service;

import domain.Chat;
import domain.Person;
import domain.Topic;

import java.util.List;

/**
 * Created by Bram on 02/03/2017.
 */
public interface Service {
	
	void init();
	
    void addPerson(Person person);

    void removePerson(String username);

    void editPerson(String username, Person person);

    Person getPerson(String username);

    List<Person> getPersons();
    
    boolean authenticateUser(String username, String password);
    
	void addTopic(Topic topic);
	void removeTopic(int id);
	Topic getTopic(int id);
	List<Topic> getTopics();
	
	Chat getChat(String id);
	void startNewChat(Chat chat, List<Person> persons);
	void removeChat(String id);
	List<Chat> getChatsFromPerson(String username);
	Chat getChat(Person owner, List<Person> persons);
	void addChat(Chat chat);
}
