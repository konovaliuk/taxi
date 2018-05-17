package jdbc.dao.mysql;

import jdbc.dao.idao.ICarTypeDao;
import jdbc.dto.*;
import jdbc.exception.*;
import java.util.*;
import java.sql.*;

public class CarTypeDao extends Dao<CarType> implements ICarTypeDao {

    private static final String SQL_SELECT = "SELECT car_type.car_type_id, car_type.name, car_type.cost_multiplier FROM car_type ";
    private static final String SQL_INSERT = "INSERT INTO car_type (car_type_id, name, cost_multiplier) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE car_type SET car_type_id = ?, name = ?, cost_multiplier = ? WHERE car_type.car_type_id = ?";
    private static final String SQL_DELETE = "DELETE FROM car_type WHERE car_type.car_type_id = ?";
    private static final String SQL_COUNT = "SELECT count(car_type) from car_type ";

    protected void fillPreparedStatement(CarType dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getCarTypeId() != null && pkFill) {
            ps.setInt(1, dto.getCarTypeId());
        } else {
            ps.setNull(1, 4);
        }
        if (dto.getName() != null) {
            ps.setString(2,  dto.getName());
        } else {
            ps.setNull(2, 12);
        }
        if (dto.getCostMultiplier() != null) {
            ps.setFloat(3,  dto.getCostMultiplier());
        } else {
            ps.setNull(3, 7);
        }
    }


    public CarType insert(CarType dto) throws CarTypeDaoException {
        try {
            dto.setCarTypeId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new CarTypeDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, CarType dto) throws CarTypeDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new CarTypeDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws CarTypeDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new CarTypeDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public CarType[] findAll() throws CarTypeDaoException {
        return findByWhere(null,null);
    }

    public CarType[] findByWhere(String where, Object[] sqlParams) throws CarTypeDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new CarTypeDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public CarType findByPrimaryKey(Integer carType) throws CarTypeDaoException {
        return findByWhere("car_type.car_type_id = ?", new Object[]{carType})[0];
    }

    public int countAll() throws CarTypeDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws CarTypeDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new CarTypeDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  CarType[] findWhereNameEquals(String name) throws CarTypeDaoException {
        return findByWhere("car_type.name = ?", new Object[]{name});
    }

    public  CarType[] findWhereCostMultiplierEquals(Float costMultiplier) throws CarTypeDaoException {
        return findByWhere("car_type.cost_multiplier = ?", new Object[]{costMultiplier});
    }

    public int countWhereNameEquals(String name) throws CarTypeDaoException {
        return countByWhere("name = ?", new Object[]{name});
    }

    public int countWhereCostMultiplierEquals(Float costMultiplier) throws CarTypeDaoException {
        return countByWhere("cost_multiplier = ?", new Object[]{costMultiplier});
    }


    protected  CarType[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<CarType> results = new ArrayList<>();
        while (rs.next()) {
            CarType dto = new CarType();
            populateDto(dto, rs);
            results.add(dto);
        }
        CarType retValue[] = new CarType[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  CarType fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            CarType dto = new CarType();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(CarType dto, ResultSet rs) throws SQLException {
        dto.setCarTypeId(rs.getInt(COLUMN_POSITION_CAR_TYPE_ID));
        if (rs.wasNull()) dto.setCarTypeId(null);
        dto.setName(rs.getString(COLUMN_POSITION_NAME));
        if (rs.wasNull()) dto.setName(null);
        dto.setCostMultiplier(rs.getFloat(COLUMN_POSITION_COST_MULTIPLIER));
        if (rs.wasNull()) dto.setCostMultiplier(null);
    }
}
