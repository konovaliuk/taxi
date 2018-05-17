package jdbc.exception;

public class LoyaltyDiscountDaoException extends DaoException {

    public LoyaltyDiscountDaoException(String message) {
        super(message);
    }

    public LoyaltyDiscountDaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

}
