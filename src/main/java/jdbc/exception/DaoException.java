package jdbc.exception;

public class DaoException extends Exception {

    protected Throwable throwable;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    public Throwable getCause() {
        return throwable;
    }

}
