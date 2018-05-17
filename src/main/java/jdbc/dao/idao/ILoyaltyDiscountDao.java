package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

/** 
  * DAO for the <b>loyalty_discount</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: loyalty_discount
  * ----------------------------------------------
  *     column: loyalty_discount_id        INT 
  *     column: orders_threshold        INT 
  *     column: cost_multiplier        FLOAT 
  * 
  * Primary Key(s):  loyalty_discount_id
  * </pre>
  * 
  */ 
public interface ILoyaltyDiscountDao extends IDao<LoyaltyDiscount,LoyaltyDiscountDaoException> {

    public static final int COLUMN_POSITION_LOYALTY_DISCOUNT_ID = 1;
    public static final int COLUMN_POSITION_ORDERS_THRESHOLD = 2;
    public static final int COLUMN_POSITION_COST_MULTIPLIER = 3;

    public static final String COLUMN_LOYALTY_DISCOUNT_ID = "loyalty_discount.loyalty_discount_id";
    public static final String COLUMN_ORDERS_THRESHOLD = "loyalty_discount.orders_threshold";
    public static final String COLUMN_COST_MULTIPLIER = "loyalty_discount.cost_multiplier";

    public  LoyaltyDiscount[] findWhereOrdersThresholdEquals(Integer ordersThreshold) throws LoyaltyDiscountDaoException;

    public  LoyaltyDiscount[] findWhereCostMultiplierEquals(Float costMultiplier) throws LoyaltyDiscountDaoException;

    public int countWhereOrdersThresholdEquals(Integer ordersThreshold) throws LoyaltyDiscountDaoException;

    public int countWhereCostMultiplierEquals(Float costMultiplier) throws LoyaltyDiscountDaoException;

}
