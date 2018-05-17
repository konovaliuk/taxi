package jdbc.exception;

public class PointDaoException extends DaoException {

    public PointDaoException(String message) {
        super(message);
    }

    public PointDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
