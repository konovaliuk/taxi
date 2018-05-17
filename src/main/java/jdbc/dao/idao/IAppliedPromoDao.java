package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.AppliedPromoDaoException;

public interface IAppliedPromoDao extends IDao<AppliedPromo,AppliedPromoDaoException>{

    public static final int COLUMN_POSITION_APPLIED_PROMO_ID = 1;
    public static final int COLUMN_POSITION_PROMO_ID = 2;
    public static final int COLUMN_POSITION_ORDERS_ORDER_ID = 3;

    public static final String COLUMN_APPLIED_PROMO = "applied_promo.applied_promo_id";
    public static final String COLUMN_PROMO_ID = "applied_promo.promo_id";
    public static final String COLUMN_ORDERS_ORDER_ID = "applied_promo.orders_order_id";

    public  AppliedPromo[] findWhereOrdersOrderIdEquals(Integer ordersOrderId) throws AppliedPromoDaoException;

    public  AppliedPromo[] findWherePromoIdEquals(Integer promoId) throws AppliedPromoDaoException;

    public int countWhereOrdersOrderIdEquals(Integer ordersOrderId) throws AppliedPromoDaoException;

    public int countWherePromoIdEquals(Integer promoId) throws AppliedPromoDaoException;

}
