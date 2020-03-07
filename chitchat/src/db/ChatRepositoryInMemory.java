package db;

import java.util.HashMap;

import domain.Chat;

public class ChatRepositoryInMemory implements ChatRepository {
	
	private static final HashMap<String, Chat> chats = new HashMap<>();
	
	public ChatRepositoryInMemory() {
	}
	
	@Override
	public Chat getChat(String id) {
		return chats.get(id);
	}

	@Override
	public void addChat(Chat chat) {
		chats.put(chat.getId(), chat);
		
	}

	@Override
	public void removeChat(String id) {
		chats.remove(id);
	}
//TODO
	@Override
	public boolean chatExists(Chat chat) {
		return false;
	}
	
}
