package microstack.services;

public class PersonalizacionSpringException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonalizacionSpringException() {
        super();
    }

    public PersonalizacionSpringException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PersonalizacionSpringException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonalizacionSpringException(String message) {
        super(message);
    }

    public PersonalizacionSpringException(Throwable cause) {
        super(cause);
    }

}
