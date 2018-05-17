package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dao.idao.IAppliedPromoDao;
import jdbc.dto.AppliedPromo;
import jdbc.exception.AppliedPromoDaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class AppliedPromoMySQLDao extends MySQLDao<AppliedPromo> implements IAppliedPromoDao {

    private static final String SQL_SELECT = "SELECT applied_promo.applied_promo, applied_promo.promo_id, applied_promo.orders_order_id FROM applied_promo ";
    private static final String SQL_INSERT = "INSERT INTO applied_promo (applied_promo, promo_id, order_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE applied_promo SET applied_promo = ?, promo_id = ?, order_id = ? WHERE applied_promo.applied_promo = ?";
    private static final String SQL_DELETE = "DELETE FROM applied_promo WHERE applied_promo.applied_promo = ?";
    private static final String SQL_COUNT = "SELECT count(applied_promo) from applied_promo ";


    private static volatile AppliedPromoMySQLDao instance;

    private AppliedPromoMySQLDao(DataSource dataSource) {
        super(dataSource);
    }

    public static AppliedPromoMySQLDao getInstance(DataSource dataSource){
        if (instance == null){
            synchronized (AppliedPromoMySQLDao.class) {
                if (instance == null) {
                    instance = new AppliedPromoMySQLDao(dataSource);
                }
            }
        }
        return instance;
    }


    protected void fillPreparedStatement(AppliedPromo dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getAppliedPromoId() != null && pkFill) {
            ps.setInt(1, dto.getAppliedPromoId());
        } else {
            ps.setNull(1, 4);
        }

        if (dto.getPromoId() != null) {
            ps.setInt(2, dto.getPromoId());
        } else {
            ps.setNull(2, 4);
        }

        if (dto.getOrdersOrderId() != null) {
            ps.setInt(3, dto.getOrdersOrderId());
        } else {
            ps.setNull(3, 4);
        }
    }


    public AppliedPromo insert(AppliedPromo dto) throws AppliedPromoDaoException {
        try {
            dto.setAppliedPromoId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new AppliedPromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, AppliedPromo dto) throws AppliedPromoDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new AppliedPromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws AppliedPromoDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new AppliedPromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public AppliedPromo[] findAll() throws AppliedPromoDaoException {
        return findByWhere(null,null);
    }

    public AppliedPromo[] findByWhere(String where, Object[] sqlParams) throws AppliedPromoDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new AppliedPromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public AppliedPromo findByPrimaryKey(Integer appliedPromo) throws AppliedPromoDaoException {
        return findByWhere("applied_promo.applied_promo = ?", new Object[]{appliedPromo})[0];
    }

    public int countAll() throws AppliedPromoDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws AppliedPromoDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new AppliedPromoDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public AppliedPromo[] findWherePromoIdEquals(Integer promoId) throws AppliedPromoDaoException {
        return findByWhere("applied_promo.promo_id = ?", new Object[]{promoId});
    }

    public AppliedPromo[] findWhereOrdersOrderIdEquals(Integer ordersOrderId) throws AppliedPromoDaoException {
        return findByWhere("applied_promo.orders_order_id = ?", new Object[]{ordersOrderId});
    }

    public int countWherePromoIdEquals(Integer promoId) throws AppliedPromoDaoException {
        return countByWhere("promo_id = ?", new Object[]{promoId});
    }

    public int countWhereOrdersOrderIdEquals(Integer ordersOrderId) throws AppliedPromoDaoException {
        return countByWhere("orders_order_id = ?",new Object[]{ordersOrderId});
    }


    protected AppliedPromo[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<AppliedPromo> results = new ArrayList<>();
        while (rs.next()) {
            AppliedPromo dto = new AppliedPromo();
            populateDto(dto, rs);
            results.add(dto);
        }
        AppliedPromo retValue[] = new AppliedPromo[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected AppliedPromo fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            AppliedPromo dto = new AppliedPromo();
            populateDto(dto, rs);
            return dto;
        } else
            return null;
    }

    private static void populateDto(AppliedPromo dto, ResultSet rs) throws SQLException {
            dto.setAppliedPromoId(rs.getInt(1));
            if (rs.wasNull()) dto.setAppliedPromoId(null);
            dto.setPromoId(rs.getInt(2));
            if (rs.wasNull()) dto.setPromoId(null);
            dto.setOrdersOrderId(rs.getInt(3));
            if (rs.wasNull()) dto.setOrdersOrderId(null);
    }
}
