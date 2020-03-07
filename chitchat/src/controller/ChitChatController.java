package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Chat;
import domain.Message;
import domain.Person;
import service.ChatService;
import service.Service;

/**
 * Servlet implementation class ChitChatController
 */
@WebServlet("/chitchat")
public class ChitChatController extends HttpServlet {
	
	private Service chatService;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChitChatController() {
        super();
        chatService = new ChatService();
        chatService.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		

		if (action != null && !action.trim().isEmpty() && isAuthenticated(action, request, response)) {
			switch (action) {
			default: Home(request, response);
				break;
			case "getPersons" : getPersons(request, response);
				break;
			case "editstatus" : editStatus(request, response);
				break;
			case "login" : login(request, response);
				break;
			case "loginpage" : loginPage(request,response);
				break;
			case "addFriend" : addFriend(request, response);
				break;
			case "getPersonFromSession" : getPersonFromSessionJSON(request, response);
				break;
			case "logout" : logout(request, response);
				break;
			case "chats" : getChats(request, response);
				break;
			case "chat" : getChat(request, response);
				break;
			case "chatJSON" : getChatJSON(request, response);
				break;
			case "sendMessage" : sendMessage(request, response);
				break;
			case "getchatbox" : getChatBox(request,response);
				break;
			}
		} else {
			response.sendRedirect("chitchat?action=home");
		}
		

		
	}
	
	private void getChatBox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("id");
		Person person = this.chatService.getPerson(username);
		Person owner = this.getPersonFromSession(request, response);
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(owner);
		persons.add(person);
		Chat chat = owner.getRightChat(persons);
		
		if (this.chatService.getChat(chat.getId()) == null) {
			this.chatService.addChat(chat);
		}
		
		String chatJSON = this.getJSON(chat);
		response.getWriter().write(chatJSON);
		
	}

	private void getChat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Chat chat = chatService.getChat(id);
		
		request.setAttribute("chat", chat);
		request.setAttribute("username", this.getPersonFromSession(request, response).getUsername());
		
		forward(request, response, "chat.jsp");
		
	}
	
	private void getChatJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Chat chat = chatService.getChat(id);
		
		String message = this.getJSON(chat);
		
		response.getWriter().write(message);
		
	}
	
	private void sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Chat chat = chatService.getChat(id);
		String messageString = request.getParameter("message");
		
		Message message = new Message(messageString, getPersonFromSession(request, response));
		
		chat.addMessage(message);
		
	}

	protected void Home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Person person = this.getPersonFromSession(request, response);
		
		if (person != null) {
		request.setAttribute("person", person);
		request.setAttribute("persons", person.getFriends());
		}
		forward(request, response, "index.jsp");

	}
	
	protected void getPersons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> persons =  this.getPersonFromSession(request, response).getFriends();

		String personsJSON = this.getJSON(persons);
		response.getWriter().write(personsJSON);
	}
	
	private String getJSON(Object message) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(message);
	}
	
	protected void editStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person person = (Person) request.getSession().getAttribute("person");
		
		String status = request.getParameter("status");
		
		person.setStatus(status);
		
		chatService.editPerson(person.getUsername(), person);
		
	}
	
	protected void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (this.chatService.authenticateUser(username, password)) {
			Person person = chatService.getPerson(username);
			person.setStatus("online");
			person.setOnline(true);
			request.getSession().setAttribute("person", person);
			response.sendRedirect("chitchat?action=home");
		} else {
			response.sendRedirect("chitchat?action=loginpage");
		}
	}
	
	protected void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}
	
	protected void addFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		Person person = chatService.getPerson(username);
		Person personSession = this.getPersonFromSession(request, response);
		personSession.addFriend(person);
		person.addFriend(personSession);
	}
	
	private Person getPersonFromSession(HttpServletRequest request, HttpServletResponse response) {
		return (Person) request.getSession().getAttribute("person");
	}
	//checken voor loging en maakt session aan als het nodig is

	private boolean isAuthenticated(String action, HttpServletRequest request, HttpServletResponse response) {
		HashSet<String> all = new HashSet<>();
		
		all.add("login");
		all.add("home");
		
		if (getPersonFromSession(request, response) == null) {
			return all.contains(action);
		}
		
		return true;
	}
	
	protected void getPersonFromSessionJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person person = this.getPersonFromSession(request, response);
		String jsonPerson = this.getJSON(person);
		
		response.getWriter().write(jsonPerson);
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getPersonFromSession(request, response).setStatus("offline");
		this.getPersonFromSession(request, response).setOnline(false);
		request.getSession().invalidate();
		response.sendRedirect("chitchat?action=home");
	}
	
	protected void getChats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Chat> chats = this.chatService.getChatsFromPerson(this.getPersonFromSession(request, response).getUsername());
		request.setAttribute("chats", chats);
		request.setAttribute("friends", this.getPersonFromSession(request, response).getFriends());
		forward(request, response, "chats.jsp");
	}
	

	protected void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
