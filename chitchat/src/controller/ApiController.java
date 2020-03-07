package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Person;
import service.ChatService;
import service.Service;

/**
 * Servlet implementation class ApiController
 */
@WebServlet("/api/v1/*")
public class ApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApiController() {
		super();
		service = new ChatService();
		service.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathUrl = request.getPathInfo();
		String output = "empty";

		if (pathUrl != null) {
			String[] actionArray = pathUrl.split("/");
			String action = actionArray[1];

			if (actionArray.length >= 2) {
				switch (action) {
				case "getpersons":
					output = this.getJSON(service.getPersons());
					break;
				case "addperson":
					output = this.addPerson(request, response);
					break;
				}

			}
			
			// setting the response type to json
			response.setContentType("application/json");
			// setting the CORS request, CrossOriginResourceSharing
			// so that this url/response is accessible in another domain (angular application for example) 
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(output);
		}

	}
	
	private String addPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String isMaleString = request.getParameter("isMale");
		String email = request.getParameter("email");
		String birthdateString = request.getParameter("birthdate");
		
		
		Boolean isMale = new Boolean(isMaleString);
		Integer birthdate = new Integer(birthdateString);
		
		Person person = new Person(username, password, email, isMale, birthdate);
		this.service.addPerson(person);
		return "operation received";
	}

	private String getJSON(Object message) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(message);
	}

}
