package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

/** 
  * DAO for the <b>order</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: order
  * ----------------------------------------------
  *     column: order_id        INT 
  *     column: client_id        INT 
  *     column: car_id        INT 
  *     column: loyalty_discount        INT 
  *     column: order_state        VARCHAR 
  *     column: distance_cost        FLOAT 
  * 
  * Primary Key(s):  order_id
  * </pre>
  * 
  */ 
public interface IOrderDao extends IDao<Order,OrderDaoException>{

    public static final int COLUMN_POSITION_ORDER_ID = 1;
    public static final int COLUMN_POSITION_CLIENT_ID = 2;
    public static final int COLUMN_POSITION_CAR_ID = 3;
    public static final int COLUMN_POSITION_LOYALTY_DISCOUNT = 4;
    public static final int COLUMN_POSITION_ORDER_STATE = 5;
    public static final int COLUMN_POSITION_DISTANCE_COST = 6;

    public static final String COLUMN_ORDER_ID = "order.order_id";
    public static final String COLUMN_CLIENT_ID = "order.client_id";
    public static final String COLUMN_CAR_ID = "order.car_id";
    public static final String COLUMN_LOYALTY_DISCOUNT = "order.loyalty_discount";
    public static final String COLUMN_ORDER_STATE = "order.order_state";
    public static final String COLUMN_DISTANCE_COST = "order.distance_cost";

    public  Order[] findWhereClientIdEquals(Integer clientId) throws OrderDaoException;

    public  Order[] findWhereCarIdEquals(Integer carId) throws OrderDaoException;

    public  Order[] findWhereLoyaltyDiscountEquals(Integer loyaltyDiscount) throws OrderDaoException;

    public  Order[] findWhereOrderStateEquals(String orderState) throws OrderDaoException;

    public  Order[] findWhereDistanceCostEquals(Float distanceCost) throws OrderDaoException;


    public int countWhereClientIdEquals(Integer clientId) throws OrderDaoException;

    public int countWhereCarIdEquals(Integer carId) throws OrderDaoException;

    public int countWhereLoyaltyDiscountEquals(Integer loyaltyDiscount) throws OrderDaoException;

    public int countWhereOrderStateEquals(String orderState) throws OrderDaoException;

    public int countWhereDistanceCostEquals(Float distanceCost) throws OrderDaoException;

}
