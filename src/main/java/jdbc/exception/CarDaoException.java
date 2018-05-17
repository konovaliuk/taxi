package jdbc.exception;

public class CarDaoException extends DaoException {

    public CarDaoException(String message) {
        super(message);
    }

    public CarDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
