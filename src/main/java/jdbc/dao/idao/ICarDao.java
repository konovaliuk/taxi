package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

public interface ICarDao extends IDao<Car,CarDaoException>{

    public static final int COLUMN_POSITION_CAR_ID = 1;
    public static final int COLUMN_POSITION_TYPE = 2;
    public static final int COLUMN_POSITION_STATE = 3;
    public static final int COLUMN_POSITION_REG_NUMBER = 4;
    public static final int COLUMN_POSITION_MODEL = 5;

    public static final String COLUMN_CAR_ID = "car.car_id";
    public static final String COLUMN_TYPE = "car.type";
    public static final String COLUMN_STATE = "car.state";
    public static final String COLUMN_REG_NUMBER = "car.reg_number";
    public static final String COLUMN_MODEL = "car.model";

    public  Car[] findWhereTypeEquals(Integer type) throws CarDaoException;

    public  Car[] findWhereStateEquals(String state) throws CarDaoException;

    public  Car[] findWhereRegNumberEquals(String regNumber) throws CarDaoException;

    public  Car[] findWhereModelEquals(String model) throws CarDaoException;

    public int countWhereTypeEquals(Integer type) throws CarDaoException;

    public int countWhereStateEquals(String state) throws CarDaoException;

    public int countWhereRegNumberEquals(String regNumber) throws CarDaoException;

    public int countWhereModelEquals(String model) throws CarDaoException;
}
