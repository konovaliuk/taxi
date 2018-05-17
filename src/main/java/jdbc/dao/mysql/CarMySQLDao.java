package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dao.idao.ICarDao;
import jdbc.dto.*;
import jdbc.exception.*;
import java.util.*;
import java.sql.*;

public final class CarMySQLDao extends MySQLDao<Car> implements ICarDao {

    private static final String SQL_SELECT = "SELECT car.car_id, car.type, car.state, car.reg_number, car.model FROM car ";
    private static final String SQL_INSERT = "INSERT INTO car (car_id, type, state, reg_number, model) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE car SET car_id = ?, type = ?, state = ?, reg_number = ?, model = ? WHERE car.car_id = ?";
    private static final String SQL_DELETE = "DELETE FROM car WHERE car.car_id = ?";
    private static final String SQL_COUNT = "SELECT count(car_id) from car";


    private static volatile CarMySQLDao instance;

    private CarMySQLDao(DataSource dataSource) {
        super(dataSource);
    }

    public static CarMySQLDao getInstance(DataSource dataSource){
        if (instance == null){
            synchronized (CarMySQLDao.class) {
                if (instance == null) {
                    instance = new CarMySQLDao(dataSource);
                }
            }
        }
        return instance;
    }


    protected void fillPreparedStatement(Car dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getCarId() != null && pkFill) {
            ps.setInt(1, dto.getCarId());
        }else
            ps.setNull(1,  4);
        if (dto.getType() != null) {
            ps.setInt(2,  dto.getType());
        } else {
            ps.setNull(2, 4);
        }
        if (dto.getState() != null) {
            ps.setString(3,  dto.getState());
        } else {
            ps.setNull(3, 12);
        }
        if (dto.getRegNumber() != null) {
            ps.setString(4,  dto.getRegNumber());
        } else {
            ps.setNull(4, 12);
        }
        if (dto.getModel() != null) {
            ps.setString(5,  dto.getModel());
        } else {
            ps.setNull(5, 12);
        }
    }


    public Car insert(Car dto) throws CarDaoException {
        try {
            dto.setCarId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new CarDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, Car dto) throws CarDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new CarDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws CarDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new CarDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Car[] findAll() throws CarDaoException {
        return findByWhere(null,null);
    }

    public Car[] findByWhere(String where, Object[] sqlParams) throws CarDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new CarDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Car findByPrimaryKey(Integer car) throws CarDaoException {
        return findByWhere("car.car_id = ?", new Object[]{car})[0];
    }

    public int countAll() throws CarDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws CarDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new CarDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  Car[] findWhereTypeEquals(Integer type) throws CarDaoException {
        return findByWhere("car.type = ?", new Object[]{type});
    }

    public  Car[] findWhereStateEquals(String state) throws CarDaoException {
        return findByWhere("car.state = ?", new Object[]{state});
    }

    public  Car[] findWhereRegNumberEquals(String regNumber) throws CarDaoException {
        return findByWhere("car.reg_number = ?", new Object[]{regNumber});
    }

    public  Car[] findWhereModelEquals(String model) throws CarDaoException {
        return findByWhere("car.model = ?", new Object[]{model});
    }


    public int countWhereTypeEquals(Integer type) throws CarDaoException {
        return countByWhere("car.type = ?", new Object[]{type});
    }

    public int countWhereStateEquals(String state) throws CarDaoException {
        return countByWhere("car.state = ?", new Object[]{state});
    }

    public int countWhereRegNumberEquals(String regNumber) throws CarDaoException {
        return countByWhere("car.reg_number = ?", new Object[]{regNumber});
    }

    public int countWhereModelEquals(String model) throws CarDaoException {
        return countByWhere("car.model = ?", new Object[]{model});
    }


    protected  Car[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<Car> results = new ArrayList<>();
        while (rs.next()) {
            Car dto = new Car();
            populateDto(dto, rs);
            results.add(dto);
        }
        Car retValue[] = new Car[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  Car fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Car dto = new Car();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(Car dto, ResultSet rs) throws SQLException {
            dto.setCarId(rs.getInt(COLUMN_POSITION_CAR_ID));
            if (rs.wasNull()) dto.setCarId(null);
            dto.setType(rs.getInt(COLUMN_POSITION_TYPE));
            if (rs.wasNull()) dto.setType(null);
            dto.setState(rs.getString(COLUMN_POSITION_STATE));
            if (rs.wasNull()) dto.setState(null);
            dto.setRegNumber(rs.getString(COLUMN_POSITION_REG_NUMBER));
            if (rs.wasNull()) dto.setRegNumber(null);
            dto.setModel(rs.getString(COLUMN_POSITION_MODEL));
            if (rs.wasNull()) dto.setModel(null);
    }
}
