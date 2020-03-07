package db;

import domain.Chat;

public interface ChatRepository {

	
	Chat getChat(String id);
	void addChat(Chat chat);
	void removeChat(String id);
	boolean chatExists(Chat chat);
	
}
