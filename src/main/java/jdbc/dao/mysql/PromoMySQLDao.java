package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dao.idao.IPromoDao;
import jdbc.dto.Promo;
import jdbc.exception.PromoDaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class PromoMySQLDao extends MySQLDao<Promo> implements IPromoDao {

    private static final String SQL_SELECT = "SELECT promo.promo_id, promo.name, promo.conditions, promo.cost_multiplier FROM promo ";
    private static final String SQL_INSERT = "INSERT INTO promo (promo_id, name, conditions, cost_multiplier) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE promo SET promo_id = ?, name = ?, conditions = ?, cost_multiplier = ? WHERE promo.promo_id = ?";
    private static final String SQL_DELETE = "DELETE FROM promo WHERE promo.promo_id = ?";
    private static final String SQL_COUNT = "SELECT count(promo) from promo ";


    private static volatile PromoMySQLDao instance;

    private PromoMySQLDao(DataSource dataSource) {
        super(dataSource);
    }

    public static PromoMySQLDao getInstance(DataSource dataSource){
        if (instance == null){
            synchronized (PromoMySQLDao.class) {
                if (instance == null) {
                    instance = new PromoMySQLDao(dataSource);
                }
            }
        }
        return instance;
    }


    protected void fillPreparedStatement(Promo dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getPromoId() != null && pkFill) {
            ps.setInt(1, dto.getPromoId());
        } else {
            ps.setNull(1, 4);
        }
        if (dto.getName() != null) {
            ps.setString(2,  dto.getName());
        } else {
            ps.setNull(2, 12);
        }
        if (dto.getConditions() != null) {
            ps.setString(3,  dto.getConditions());
        } else {
            ps.setNull(3, 12);
        }
        if (dto.getCostMultiplier() != null) {
            ps.setFloat(4,  dto.getCostMultiplier());
        } else {
            ps.setNull(4, 7);
        }
    }


    public Promo insert(Promo dto) throws PromoDaoException {
        try {
            dto.setPromoId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new PromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, Promo dto) throws PromoDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new PromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws PromoDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new PromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Promo[] findAll() throws PromoDaoException {
        return findByWhere(null,null);
    }

    public Promo[] findByWhere(String where, Object[] sqlParams) throws PromoDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new PromoDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Promo findByPrimaryKey(Integer promo) throws PromoDaoException {
        return findByWhere("promo.promo_id = ?", new Object[]{promo})[0];
    }

    public int countAll() throws PromoDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws PromoDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new PromoDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  Promo[] findWhereNameEquals(String name) throws PromoDaoException {
        return findByWhere("promo.name = ?", new Object[]{name});
    }

    public  Promo[] findWhereConditionsEquals(String conditions) throws PromoDaoException {
        return findByWhere("promo.conditions = ?", new Object[]{conditions});
    }

    public  Promo[] findWhereCostMultiplierEquals(Float costMultiplier) throws PromoDaoException {
        return findByWhere("promo.cost_multiplier = ?", new Object[]{costMultiplier});
    }

    public int countWhereNameEquals(String name) throws PromoDaoException {
        return countByWhere("name = ?", new Object[]{name});
    }

    public int countWhereConditionsEquals(String conditions) throws PromoDaoException {
        return countByWhere("conditions = ?", new Object[]{conditions});
    }

    public int countWhereCostMultiplierEquals(Float costMultiplier) throws PromoDaoException {
        return countByWhere("cost_multiplier = ?", new Object[]{costMultiplier});
    }


    protected  Promo[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<Promo> results = new ArrayList<>();
        while (rs.next()) {
            Promo dto = new Promo();
            populateDto(dto, rs);
            results.add(dto);
        }
        Promo retValue[] = new Promo[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  Promo fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Promo dto = new Promo();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(Promo dto, ResultSet rs) throws SQLException {
        dto.setPromoId(rs.getInt(COLUMN_POSITION_PROMO_ID));
        if (rs.wasNull()) dto.setPromoId(null);
        dto.setName(rs.getString(COLUMN_POSITION_NAME));
        if (rs.wasNull()) dto.setName(null);
        dto.setConditions(rs.getString(COLUMN_POSITION_CONDITIONS));
        if (rs.wasNull()) dto.setConditions(null);
        dto.setCostMultiplier(rs.getFloat(COLUMN_POSITION_COST_MULTIPLIER));
        if (rs.wasNull()) dto.setCostMultiplier(null);
    }
}
