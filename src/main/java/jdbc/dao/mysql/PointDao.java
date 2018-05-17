package jdbc.dao.mysql;

import jdbc.dao.idao.IPointDao;
import jdbc.dto.*;
import jdbc.exception.*;
import java.util.*;
import java.sql.*;

public class PointDao extends Dao<Point> implements IPointDao {

    private static final String SQL_SELECT = "SELECT point.point_id, point.name, point.x_cor, point.y_cor FROM point ";
    private static final String SQL_INSERT = "INSERT INTO point (point_id, name, x_cor, y_cor) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE point SET point_id = ?, name = ?, x_cor = ?, y_cor = ? WHERE point.point_id = ?";
    private static final String SQL_DELETE = "DELETE FROM point WHERE point.point_id = ?";
    private static final String SQL_COUNT = "SELECT count(point) from point ";

    protected void fillPreparedStatement(Point dto, PreparedStatement ps, boolean pkFill) throws SQLException {
        if (dto.getPointId() != null && pkFill) {
            ps.setInt(1, dto.getPointId());
        } else {
            ps.setNull(1, 4);
        }
        if (dto.getName() != null) {
            ps.setString(2,  dto.getName());
        } else {
            ps.setNull(2, 12);
        }
        if (dto.getXCor() != null) {
            ps.setFloat(3,  dto.getXCor());
        } else {
            ps.setNull(3, 7);
        }
        if (dto.getYCor() != null) {
            ps.setFloat(4,  dto.getYCor());
        } else {
            ps.setNull(4, 7);
        }
    }


    public Point insert(Point dto) throws PointDaoException {
        try {
            dto.setPointId(insert(dto, SQL_INSERT));
            return dto;
        }catch (SQLException e) {
            throw new PointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int update(Integer pk, Point dto) throws PointDaoException {
        try {
            return update(pk, dto, SQL_UPDATE);
        } catch (SQLException e) {
            throw new PointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public int delete(Integer pk) throws PointDaoException {
        try {
            return delete(pk, SQL_DELETE);
        } catch (SQLException e) {
            throw new PointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Point[] findAll() throws PointDaoException {
        return findByWhere(null,null);
    }

    public Point[] findByWhere(String where, Object[] sqlParams) throws PointDaoException {
        try {
            return findByWhere(SQL_SELECT, where, sqlParams, null);
        } catch (SQLException e) {
            throw new PointDaoException("SQLException: " + e.getMessage(), e);
        }
    }

    public Point findByPrimaryKey(Integer point) throws PointDaoException {
        return findByWhere("point.point_id = ?", new Object[]{point})[0];
    }

    public int countAll() throws PointDaoException {
        return countByWhere(null,null);
    }

    public int countByWhere(String where, Object[] sqlParams) throws PointDaoException {
        try {
            return countByWhere(SQL_COUNT, where, sqlParams);
        } catch (SQLException e) {
            throw new PointDaoException("SQLException: " + e.getMessage(), e);
        }

    }


    public  Point[] findWhereNameEquals(String name) throws PointDaoException{
        return findByWhere("point.name = ?", new Object[]{name});
    }

    public  Point[] findWhereXCorEquals(Float xCor) throws PointDaoException{
        return findByWhere("point.x_cor = ?", new Object[]{xCor});
    }

    public  Point[] findWhereYCorEquals(Float yCor) throws PointDaoException{
        return findByWhere("point.y_cor = ?", new Object[]{yCor});
    }

    public int countWhereNameEquals(String name) throws PointDaoException{
        return countByWhere("name = ?", new Object[]{name});
    }

    public int countWhereXCorEquals(Float xCor) throws PointDaoException{
        return countByWhere("x_cor = ?", new Object[]{xCor});
    }

    public int countWhereYCorEquals(Float yCor) throws PointDaoException{
        return countByWhere("y_cor = ?", new Object[]{yCor});
    }


    protected  Point[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<Point> results = new ArrayList<>();
        while (rs.next()) {
            Point dto = new Point();
            populateDto(dto, rs);
            results.add(dto);
        }
        Point retValue[] = new Point[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    protected  Point fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Point dto = new Point();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    private static void populateDto(Point dto, ResultSet rs) throws SQLException {
        dto.setPointId(rs.getInt(COLUMN_POSITION_POINT_ID));
        if (rs.wasNull()) dto.setPointId(null);
        dto.setName(rs.getString(COLUMN_POSITION_NAME));
        if (rs.wasNull()) dto.setName(null);
        dto.setXCor(rs.getFloat(COLUMN_POSITION_X_COR));
        if (rs.wasNull()) dto.setXCor(null);
        dto.setYCor(rs.getFloat(COLUMN_POSITION_Y_COR));
        if (rs.wasNull()) dto.setYCor(null);
    }


}
