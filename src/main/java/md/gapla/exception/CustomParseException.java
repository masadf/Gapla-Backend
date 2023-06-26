package md.gapla.exception;


import jakarta.persistence.PersistenceException;

public class CustomParseException extends PersistenceException {

    public CustomParseException(String message) {
        super(message);
    }

}
