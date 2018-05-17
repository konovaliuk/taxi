package jdbc.exception;

public class PromoDaoException extends DaoException {

    public PromoDaoException(String message) {
        super(message);
    }

    public PromoDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
