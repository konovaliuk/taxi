package jdbc.exception;

public class UserDaoException extends DaoException {

    public UserDaoException(String message) {
        super(message);
    }

    public UserDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
