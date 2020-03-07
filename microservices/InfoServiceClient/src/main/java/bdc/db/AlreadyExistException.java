package bdc.db;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException() {
        super();
    }

}
