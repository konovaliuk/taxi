package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

public interface IRoutePointDao extends IDao<RoutePoint,RoutePointDaoException> {

    public static final int COLUMN_POSITION_ROUTE_POINT_ID = 1;
    public static final int COLUMN_POSITION_POINTS_ID = 2;
    public static final int COLUMN_POSITION_ORDER_ID = 3;
    public static final int COLUMN_POSITION_TYPE = 4;
    public static final int COLUMN_POSITION_VISITING_ORDER = 5;

    public static final String COLUMN_ROUTE_POINT_ID = "route_point.route_point_id";
    public static final String COLUMN_POINTS_ID = "route_point.points_id";
    public static final String COLUMN_ORDER_ID = "route_point.order_id";
    public static final String COLUMN_TYPE = "route_point.type";
    public static final String COLUMN_VISITING_ORDER = "route_point.visiting_order";

    public  RoutePoint[] findWherePointsIdEquals(Integer pointsId) throws RoutePointDaoException;

    public  RoutePoint[] findWhereOrderIdEquals(Integer orderId) throws RoutePointDaoException;

    public  RoutePoint[] findWhereTypeEquals(String type) throws RoutePointDaoException;

    public  RoutePoint[] findWhereVisitingOrderEquals(Integer visitingOrder) throws RoutePointDaoException;

    public int countWherePointsIdEquals(Integer pointsId) throws RoutePointDaoException;

    public int countWhereOrderIdEquals(Integer orderId) throws RoutePointDaoException;

    public int countWhereTypeEquals(String type) throws RoutePointDaoException;

    public int countWhereVisitingOrderEquals(Integer visitingOrder) throws RoutePointDaoException;

}
