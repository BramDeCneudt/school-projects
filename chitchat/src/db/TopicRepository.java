package db;

import java.util.List;

import domain.Topic;

public interface TopicRepository {
	
	void addTopic(Topic topic);
	void removeTopic(int id);
	Topic getTopic(int id);
	List<Topic> getTopics();

}
