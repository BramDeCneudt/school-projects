package domain;

public class Message {
	
	private String message;
	private Person person;
	
	public Message(String message, Person person) {
		setMessage(message);
		setPerson(person);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if (message == null) {
			throw new DomainException("error");
		}
		this.message = message;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if (person == null) {
			throw new DomainException("error");
		}
		this.person = person;
	}

}
