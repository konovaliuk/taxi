package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dao.idao.ILoyaltyDiscountDao;
import jdbc.dto.*;
import jdbc.exception.*;
import java.util.*;
import java.sql.*;

public final class LoyaltyDiscountMySQLDao extends MySQLDao<LoyaltyDiscount> implements ILoyaltyDiscountDao {

    private static final String SQL_SELECT = "SELECT loyalty_discount.loyalty_discount_id, loyalty_discount.orders_threshold, loyalty_discount.cost_multiplier FROM loyalty_discount ";
    private static final String SQL_INSERT = "INSERT INTO loyalty_discount (loyalty_discount_id, orders_threshold, cost_multiplier) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE loyalty_discount SET loyalty_discount_id = ?, orders_threshold = ?, cost_multiplier = ? WHERE loyalty_discount.loyalty_discount_id = ?";
    private static final String SQL_DELETE = "DELETE FROM loyalty_discount WHERE loyalty_discount.loyalty_discount_id = ?";
    private static final String SQL_COUNT = "SELECT count(loyalty_discount) from loyalty_discount ";


    private static volatile LoyaltyDiscountMySQLDao instance;

    private LoyaltyDiscountMySQLDao(DataSource dataSource) {
        super(dataSource);
    }

    public static LoyaltyDiscountMySQLDao getInstance(DataSource dataSource){
        if (instance == null){
            synchronized (LoyaltyDiscountMySQLDao.class) {
                if (instance == null) {
                    instance = new LoyaltyDiscountMySQLDao(dataSource);
                }
            }
        }
        return instance;
    }


    protected void fillPreparedStatement(LoyaltyDiscount dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getLoyaltyDiscountId() != null && pkFill) {
            ps.setInt(1, dto.getLoyaltyDiscountId());
        } else {
            ps.setNull(1, 4);
        }

        if (dto.getOrdersThreshold() != null) {
            ps.setInt(2,  dto.getOrdersThreshold());
        } else {
            ps.setNull(2, 4);
        }
        if (dto.getCostMultiplier() != null) {
            ps.setFloat(3,  dto.getCostMultiplier());
        } else {
            ps.setNull(3, 7);
        }
    }


    public LoyaltyDiscount insert(LoyaltyDiscount dto) throws LoyaltyDiscountDaoException {
        try {
            dto.setLoyaltyDiscountId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new LoyaltyDiscountDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, LoyaltyDiscount dto) throws LoyaltyDiscountDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new LoyaltyDiscountDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws LoyaltyDiscountDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new LoyaltyDiscountDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public LoyaltyDiscount[] findAll() throws LoyaltyDiscountDaoException {
        return findByWhere(null,null);
    }

    public LoyaltyDiscount[] findByWhere(String where, Object[] sqlParams) throws LoyaltyDiscountDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new LoyaltyDiscountDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public LoyaltyDiscount findByPrimaryKey(Integer loyaltyDiscount) throws LoyaltyDiscountDaoException {
        return findByWhere("loyalty_discount.loyalty_discount = ?", new Object[]{loyaltyDiscount})[0];
    }

    public int countAll() throws LoyaltyDiscountDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws LoyaltyDiscountDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new LoyaltyDiscountDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  LoyaltyDiscount[] findWhereOrdersThresholdEquals(Integer ordersThreshold) throws LoyaltyDiscountDaoException {
        return findByWhere("loyalty_discount.orders_threshold = ?", new Object[]{ordersThreshold});
    }

    public  LoyaltyDiscount[] findWhereCostMultiplierEquals(Float costMultiplier) throws LoyaltyDiscountDaoException {
        return findByWhere("loyalty_discount.cost_multiplier = ?", new Object[]{costMultiplier});
    }

    public int countWhereOrdersThresholdEquals(Integer ordersThreshold) throws LoyaltyDiscountDaoException {
        return countByWhere("orders_threshold = ?", new Object[]{ordersThreshold});
    }

    public int countWhereCostMultiplierEquals(Float costMultiplier) throws LoyaltyDiscountDaoException {
        return countByWhere("cost_multiplier = ?", new Object[]{costMultiplier});
    }


    protected  LoyaltyDiscount[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<LoyaltyDiscount> results = new ArrayList<>();
        while (rs.next()) {
            LoyaltyDiscount dto = new LoyaltyDiscount();
            populateDto(dto, rs);
            results.add(dto);
        }
        LoyaltyDiscount retValue[] = new LoyaltyDiscount[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  LoyaltyDiscount fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            LoyaltyDiscount dto = new LoyaltyDiscount();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(LoyaltyDiscount dto, ResultSet rs) throws SQLException {
        dto.setLoyaltyDiscountId(rs.getInt(COLUMN_POSITION_LOYALTY_DISCOUNT_ID));
        if (rs.wasNull()) dto.setLoyaltyDiscountId(null);
        dto.setOrdersThreshold(rs.getInt(COLUMN_POSITION_ORDERS_THRESHOLD));
        if (rs.wasNull()) dto.setOrdersThreshold(null);
        dto.setCostMultiplier(rs.getFloat(COLUMN_POSITION_COST_MULTIPLIER));
        if (rs.wasNull()) dto.setCostMultiplier(null);
    }
}
