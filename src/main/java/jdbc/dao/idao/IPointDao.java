package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

public interface IPointDao extends IDao<Point,PointDaoException>{

    public static final int COLUMN_POSITION_POINT_ID = 1;
    public static final int COLUMN_POSITION_NAME = 2;
    public static final int COLUMN_POSITION_X_COR = 3;
    public static final int COLUMN_POSITION_Y_COR = 4;

    public static final String COLUMN_POINT_ID = "point.point_id";
    public static final String COLUMN_NAME = "point.name";
    public static final String COLUMN_X_COR = "point.x_cor";
    public static final String COLUMN_Y_COR = "point.y_cor";

    public  Point[] findWhereNameEquals(String name) throws PointDaoException;

    public  Point[] findWhereXCorEquals(Float xCor) throws PointDaoException;

    public  Point[] findWhereYCorEquals(Float yCor) throws PointDaoException;

    public int countWhereNameEquals(String name) throws PointDaoException;

    public int countWhereXCorEquals(Float xCor) throws PointDaoException;

    public int countWhereYCorEquals(Float yCor) throws PointDaoException;

}
