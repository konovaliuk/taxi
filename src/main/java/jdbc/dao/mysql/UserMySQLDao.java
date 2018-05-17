package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dao.idao.IUserDao;
import jdbc.dto.*;
import jdbc.exception.*;
import java.util.*;
import java.sql.*;

public final class UserMySQLDao extends MySQLDao<User> implements IUserDao {

    private static final String SQL_SELECT = "SELECT user.user_id, user.type, user.login, user.password FROM user ";
    private static final String SQL_INSERT = "INSERT INTO user (user_id, type, login, password) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET user_id = ?, type = ?, login = ?, password = ? WHERE user.user_id = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE user.user_id = ?";
    private static final String SQL_COUNT = "SELECT count(user) from user ";


    private static volatile UserMySQLDao instance;

    private UserMySQLDao(DataSource dataSource) {
        super(dataSource);
    }

    public static UserMySQLDao getInstance(DataSource dataSource){
        if (instance == null){
            synchronized (UserMySQLDao.class) {
                if (instance == null) {
                    instance = new UserMySQLDao(dataSource);
                }
            }
        }
        return instance;
    }


    protected void fillPreparedStatement(User dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getUserId() != null && pkFill) {
            ps.setInt(1, dto.getUserId());
        } else {
            ps.setNull(1, 4);
        }
        if (dto.getType() == null)
            ps.setNull(2, 12);
        else
            ps.setString(2,  dto.getType());
        if (dto.getLogin() == null)
            ps.setNull(3, 12);
        else
            ps.setString(3,  dto.getLogin());
        if (dto.getPassword() == null)
            ps.setNull(4, 12);
        else
            ps.setString(4,  dto.getPassword());
    }


    public User insert(User dto) throws UserDaoException {
        try {
            dto.setUserId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new UserDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, User dto) throws UserDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new UserDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws UserDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new UserDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public User[] findAll() throws UserDaoException {
        return findByWhere(null,null);
    }

    public User[] findByWhere(String where, Object[] sqlParams) throws UserDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new UserDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public User findByPrimaryKey(Integer user) throws UserDaoException {
        return findByWhere("user.user = ?", new Object[]{user})[0];
    }

    public int countAll() throws UserDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws UserDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new UserDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  User[] findWhereTypeEquals(String type) throws UserDaoException {
        return findByWhere("user.type = ?", new Object[]{type});
    }

    public  User[] findWhereLoginEquals(String login) throws UserDaoException {
        return findByWhere("user.login = ?", new Object[]{login});
    }

    public  User[] findWherePasswordEquals(String password) throws UserDaoException {
        return findByWhere("user.password = ?", new Object[]{password});
    }

    public int countWhereTypeEquals(String type) throws UserDaoException {
        return countByWhere("type = ?", new Object[]{type});
    }

    public int countWhereLoginEquals(String login) throws UserDaoException {
        return countByWhere("login = ?", new Object[]{login});
    }

    public int countWherePasswordEquals(String password) throws UserDaoException {
        return countByWhere("password = ?", new Object[]{password});
    }


    protected  User[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<User> results = new ArrayList<>();
        while (rs.next()) {
            User dto = new User();
            populateDto(dto, rs);
            results.add(dto);
        }
        User retValue[] = new User[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  User fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            User dto = new User();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(User dto, ResultSet rs) throws SQLException {
        dto.setUserId(rs.getInt(COLUMN_POSITION_USER_ID));
        if (rs.wasNull()) dto.setUserId(null);
        dto.setType(rs.getString(COLUMN_POSITION_TYPE));
        if (rs.wasNull()) dto.setType(null);
        dto.setLogin(rs.getString(COLUMN_POSITION_LOGIN));
        if (rs.wasNull()) dto.setLogin(null);
        dto.setPassword(rs.getString(COLUMN_POSITION_PASSWORD));
        if (rs.wasNull()) dto.setPassword(null);
    }
}
