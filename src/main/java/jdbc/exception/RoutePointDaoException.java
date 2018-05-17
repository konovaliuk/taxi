package jdbc.exception;

public class RoutePointDaoException extends DaoException {

    public RoutePointDaoException(String message) {
        super(message);
    }

    public RoutePointDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
