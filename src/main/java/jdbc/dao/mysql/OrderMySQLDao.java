package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dao.idao.IOrderDao;
import jdbc.dto.Order;
import jdbc.exception.OrderDaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class OrderMySQLDao extends MySQLDao<Order> implements IOrderDao {

    private static final String SQL_SELECT = "SELECT order.order_id, order.client_id, order.car_id, order.loyalty_discount, order.order_state, order.distance_cost FROM order ";
    private static final String SQL_INSERT = "INSERT INTO order (order_id, client_id, car_id, loyalty_discount, order_state, distance_cost) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE order SET order_id = ?, client_id = ?, car_id = ?, loyalty_discount = ?, order_state = ?, distance_cost = ? WHERE order.order_id = ?";
    private static final String SQL_DELETE = "DELETE FROM order WHERE order.order_id = ?";
    private static final String SQL_COUNT = "SELECT count(order) from order ";


    private static volatile OrderMySQLDao instance;

    private OrderMySQLDao(DataSource dataSource) {
        super(dataSource);
    }

    public static OrderMySQLDao getInstance(DataSource dataSource){
        if (instance == null){
            synchronized (OrderMySQLDao.class) {
                if (instance == null) {
                    instance = new OrderMySQLDao(dataSource);
                }
            }
        }
        return instance;
    }


    protected void fillPreparedStatement(Order dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getOrderId() != null && pkFill) {
            ps.setInt(1, dto.getOrderId());
        } else {
            ps.setNull(1, 4);
        }

        if (dto.getClientId() != null) {
            ps.setInt(2,  dto.getClientId());
        } else {
            ps.setNull(2, 4);
        }
        if (dto.getCarId() != null) {
            ps.setInt(3,  dto.getCarId());
        } else {
            ps.setNull(3, 4);
        }
        if (dto.getLoyaltyDiscount() != null) {
            ps.setInt(4,  dto.getLoyaltyDiscount());
        } else {
            ps.setNull(4, 4);
        }
        if (dto.getOrderState() != null) {
            ps.setString(5,  dto.getOrderState());
        } else {
            ps.setNull(5, 12);
        }
        if (dto.getDistanceCost() != null) {
            ps.setFloat(6,  dto.getDistanceCost());
        } else {
            ps.setNull(6, 7);
        }
    }


    public Order insert(Order dto) throws OrderDaoException {
        try {
            dto.setOrderId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new OrderDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, Order dto) throws OrderDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new OrderDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws OrderDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new OrderDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Order[] findAll() throws OrderDaoException {
        return findByWhere(null,null);
    }

    public Order[] findByWhere(String where, Object[] sqlParams) throws OrderDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new OrderDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Order findByPrimaryKey(Integer order) throws OrderDaoException {
        return findByWhere("order.order_id = ?", new Object[]{order})[0];
    }

    public int countAll() throws OrderDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws OrderDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new OrderDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  Order[] findWhereClientIdEquals(Integer clientId) throws OrderDaoException{
        return findByWhere("order.client_id = ?", new Object[]{clientId});
    }

    public  Order[] findWhereCarIdEquals(Integer carId) throws OrderDaoException{
        return findByWhere("order.car_id = ?", new Object[]{carId});
    }

    public  Order[] findWhereLoyaltyDiscountEquals(Integer loyaltyDiscount) throws OrderDaoException{
        return findByWhere("order.loyalty_discount = ?", new Object[]{loyaltyDiscount});
    }

    public  Order[] findWhereOrderStateEquals(String orderState) throws OrderDaoException{
        return findByWhere("order.order_state = ?", new Object[]{orderState});
    }

    public  Order[] findWhereDistanceCostEquals(Float distanceCost) throws OrderDaoException{
        return findByWhere("order.distance_cost = ?", new Object[]{distanceCost});
    }

    public int countWhereClientIdEquals(Integer clientId) throws OrderDaoException {
        return countByWhere("client_id = ?", new Object[]{clientId});
    }

    public int countWhereCarIdEquals(Integer carId) throws OrderDaoException {
        return countByWhere("car_id = ?", new Object[]{carId});
    }

    public int countWhereLoyaltyDiscountEquals(Integer loyaltyDiscount) throws OrderDaoException {
        return countByWhere("loyalty_discount = ?", new Object[]{loyaltyDiscount});
    }

    public int countWhereOrderStateEquals(String orderState) throws OrderDaoException {
        return countByWhere("order_state = ?", new Object[]{orderState});
    }

    public int countWhereDistanceCostEquals(Float distanceCost) throws OrderDaoException {
        return countByWhere("distance_cost = ?", new Object[]{distanceCost});
    }


    protected  Order[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<Order> results = new ArrayList<>();
        while (rs.next()) {
            Order dto = new Order();
            populateDto(dto, rs);
            results.add(dto);
        }
        Order retValue[] = new Order[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  Order fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Order dto = new Order();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(Order dto, ResultSet rs) throws SQLException {
        dto.setOrderId(rs.getInt(COLUMN_POSITION_ORDER_ID));
        if (rs.wasNull()) dto.setOrderId(null);
        dto.setClientId(rs.getInt(COLUMN_POSITION_CLIENT_ID));
        if (rs.wasNull()) dto.setClientId(null);
        dto.setCarId(rs.getInt(COLUMN_POSITION_CAR_ID));
        if (rs.wasNull()) dto.setCarId(null);
        dto.setLoyaltyDiscount(rs.getInt(COLUMN_POSITION_LOYALTY_DISCOUNT));
        if (rs.wasNull()) dto.setLoyaltyDiscount(null);
        dto.setOrderState(rs.getString(COLUMN_POSITION_ORDER_STATE));
        if (rs.wasNull()) dto.setOrderState(null);
        dto.setDistanceCost(rs.getFloat(COLUMN_POSITION_DISTANCE_COST));
        if (rs.wasNull()) dto.setDistanceCost(null);
    }
}
