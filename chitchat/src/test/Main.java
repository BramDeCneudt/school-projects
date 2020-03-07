package test;

import java.util.ArrayList;

import domain.Person;
import domain.Reaction;
import domain.Topic;

public class Main {

	public static void main(String[] args) {
		
		Topic topic = new Topic("test", "test");
		
		Reaction reaction = new Reaction("hello", "hello");
		
		topic.addReaction(reaction);
		
		ArrayList<Reaction> reactions = (ArrayList<Reaction>) topic.getReactions();

		System.out.println(reactions.get(0).getName());
	}

}
