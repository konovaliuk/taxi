package jdbc.exception;

public class AppliedPromoDaoException extends DaoException {

    public AppliedPromoDaoException(String message) {
        super(message);
    }

    public AppliedPromoDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
