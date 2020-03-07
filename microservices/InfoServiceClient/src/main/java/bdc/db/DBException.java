package bdc.db;

public class DBException extends RuntimeException {

    public DBException(String message) {
        super(message);
    }

    public DBException() {
        super();
    }

}
