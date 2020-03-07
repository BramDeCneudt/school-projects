package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Reaction;
import service.ChatService;
import service.Service;

@ServerEndpoint("/chitchatserver")
public class ChitChatServer {
	private static final Set<Session> sessions = new HashSet<>();
	private static final Service service = new ChatService();
	
	private ObjectMapper mapper;
	
	public ChitChatServer() {
		mapper = new ObjectMapper();
	}

	@OnOpen
	public void onOpen(Session session) {
		sessions.add(session);
		sendTopicsToSession(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		Reaction reaction = this.parseJSONToReaction(message);
		if (reaction != null) {
			service.getTopic(reaction.getId()).addReaction(reaction);
		}
		sendTopicsToAll();
	}
	
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
	}
	
	private void sendTopicsToAll() {
		String message = "Er is iets miss gegaan";
		
		for (Session session : sessions) {
			try {
				message = getJSON(service.getTopics());
				session.getBasicRemote().sendText(message);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void sendTopicsToSession(Session session) {
		String message = "Er is iets miss gegaan";
		
		try {
			message = getJSON(service.getTopics());
			session.getBasicRemote().sendText(message);
			
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getJSON(Object message) throws ServletException, IOException {

		return mapper.writeValueAsString(message);
	}
	
	private Reaction parseJSONToReaction(String message) {
		
		try {
			return mapper.readValue(message, Reaction.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
