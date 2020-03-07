package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.DomainException;
import domain.Reaction;
import domain.Topic;

public class TopicRepositoryInMemory implements TopicRepository {
	
	private static final Map<Integer, Topic> topics = new HashMap<>();
	
	public TopicRepositoryInMemory() {
	}

	@Override
	public void addTopic(Topic topic) {
		if (topic == null || topics.containsKey(topic.getId())) {
			throw new DomainException("error");
		}
		topic.setId(generateId());
		topics.put(topic.getId(), topic);
		
	}

	@Override
	public void removeTopic(int id) {
		topics.remove(id);
		
	}

	@Override
	public Topic getTopic(int id) {
		return topics.get(id);
		
	}
	
	private int generateId() {
		ArrayList<Topic> topics = new ArrayList<>(TopicRepositoryInMemory.topics.values());
		
		return topics.size() == 0 ? 1 : topics.get(topics.size()-1).getId()+1; 
	}

	@Override
	public List<Topic> getTopics() {
		return new ArrayList<Topic>(topics.values());
	}

}
