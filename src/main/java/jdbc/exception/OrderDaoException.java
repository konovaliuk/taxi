package jdbc.exception;

public class OrderDaoException extends DaoException {

    public OrderDaoException(String message) {
        super(message);
    }

    public OrderDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
