package jdbc.dao.mysql;

import jdbc.dao.idao.IRoutePointDao;
import jdbc.dto.*;
import jdbc.exception.*;
import java.util.*;
import java.sql.*;

public class RoutePointDao extends Dao<RoutePoint> implements IRoutePointDao {

    private static final String SQL_SELECT = "SELECT route_point.route_point_id, route_point.points_id, route_point.order_id, route_point.type, route_point.visiting_order FROM route_point ";
    private static final String SQL_INSERT = "INSERT INTO route_point (route_point_id, points_id, order_id, type, visiting_order) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE route_point SET route_point_id = ?, points_id = ?, order_id = ?, type = ?, visiting_order = ? WHERE route_point.route_point_id = ?";
    private static final String SQL_DELETE = "DELETE FROM route_point WHERE route_point.route_point_id = ?";
    private static final String SQL_COUNT = "SELECT count(route_point) from route_point ";

    protected void fillPreparedStatement(RoutePoint dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getRoutePointId() != null && pkFill) {
            ps.setInt(1, dto.getRoutePointId());
        } else {
            ps.setNull(1, 4);
        }
        if (dto.getPointsId() != null) {
            ps.setInt(2,  dto.getPointsId());
        } else {
            ps.setNull(2, 4);
        }
        if (dto.getOrderId() != null) {
            ps.setInt(3,  dto.getOrderId());
        } else {
            ps.setNull(3, 4);
        }
        if (dto.getType() != null) {
            ps.setString(4,  dto.getType());
        } else {
            ps.setNull(4, 12);
        }
        if (dto.getVisitingOrder() != null) {
            ps.setInt(5,  dto.getVisitingOrder());
        } else {
            ps.setNull(5, 4);
        }
    }


    public RoutePoint insert(RoutePoint dto) throws RoutePointDaoException {
        try {
            dto.setRoutePointId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new RoutePointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, RoutePoint dto) throws RoutePointDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new RoutePointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws RoutePointDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new RoutePointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public RoutePoint[] findAll() throws RoutePointDaoException {
        return findByWhere(null,null);
    }

    public RoutePoint[] findByWhere(String where, Object[] sqlParams) throws RoutePointDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new RoutePointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public RoutePoint findByPrimaryKey(Integer routePoint) throws RoutePointDaoException {
        return findByWhere("route_point.route_point = ?", new Object[]{routePoint})[0];
    }

    public int countAll() throws RoutePointDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws RoutePointDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new RoutePointDaoException("SQLException: " + e.getMessage(), e);
        }

    }

    
    public  RoutePoint[] findWherePointsIdEquals(Integer pointsId) throws RoutePointDaoException {
        return findByWhere("route_point.points_id = ?", new Object[]{pointsId});
    }

    public  RoutePoint[] findWhereOrderIdEquals(Integer orderId) throws RoutePointDaoException {
        return findByWhere("route_point.order_id = ?", new Object[]{orderId});
    }

    public  RoutePoint[] findWhereTypeEquals(String type) throws RoutePointDaoException {
        return findByWhere("route_point.type = ?", new Object[]{type});
    }

    public  RoutePoint[] findWhereVisitingOrderEquals(Integer visitingOrder) throws RoutePointDaoException {
        return findByWhere("route_point.visiting_order = ?", new Object[]{visitingOrder});
    }

    public int countWherePointsIdEquals(Integer pointsId) throws RoutePointDaoException  {
        return countByWhere("points_id = ?", new Object[]{pointsId});
    }

    public int countWhereOrderIdEquals(Integer orderId) throws RoutePointDaoException {
        return countByWhere("order_id = ?", new Object[]{orderId});
    }

    public int countWhereTypeEquals(String type) throws RoutePointDaoException {
        return countByWhere("type = ?", new Object[]{type});
    }

    public int countWhereVisitingOrderEquals(Integer visitingOrder) throws RoutePointDaoException {
        return countByWhere("visiting_order = ?", new Object[]{visitingOrder});
    }


    protected  RoutePoint[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<RoutePoint> results = new ArrayList<>();
        while (rs.next()) {
            RoutePoint dto = new RoutePoint();
            populateDto(dto, rs);
            results.add(dto);
        }
        RoutePoint retValue[] = new RoutePoint[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  RoutePoint fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            RoutePoint dto = new RoutePoint();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(RoutePoint dto, ResultSet rs) throws SQLException {
        dto.setRoutePointId(rs.getInt(COLUMN_POSITION_ROUTE_POINT_ID));
        if (rs.wasNull()) dto.setRoutePointId(null);
        dto.setPointsId(rs.getInt(COLUMN_POSITION_POINTS_ID));
        if (rs.wasNull()) dto.setPointsId(null);
        dto.setOrderId(rs.getInt(COLUMN_POSITION_ORDER_ID));
        if (rs.wasNull()) dto.setOrderId(null);
        dto.setType(rs.getString(COLUMN_POSITION_TYPE));
        if (rs.wasNull()) dto.setType(null);
        dto.setVisitingOrder(rs.getInt(COLUMN_POSITION_VISITING_ORDER));
        if (rs.wasNull()) dto.setVisitingOrder(null);
    }
}
