package jdbc.exception;

public class CarTypeDaoException extends DaoException {

    public CarTypeDaoException(String message) {
        super(message);
    }

    public CarTypeDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
