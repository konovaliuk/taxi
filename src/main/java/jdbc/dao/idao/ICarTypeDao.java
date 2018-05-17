package jdbc.dao.idao;

import jdbc.dto.CarType;
import jdbc.exception.CarTypeDaoException;

/** 
  * DAO for the <b>car_type</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: car_type
  * ----------------------------------------------
  *     column: car_type_id        INT 
  *     column: name        VARCHAR 
  *     column: cost_multiplier        FLOAT 
  * 
  * Primary Key(s):  car_type_id
  * </pre>
  * 
  */ 
public interface ICarTypeDao extends IDao<CarType,CarTypeDaoException>{

    public static final int COLUMN_POSITION_CAR_TYPE_ID = 1;
    public static final int COLUMN_POSITION_NAME = 2;
    public static final int COLUMN_POSITION_COST_MULTIPLIER = 3;

    public static final String COLUMN_CAR_TYPE_ID = "car_type.car_type_id";
    public static final String COLUMN_NAME = "car_type.name";
    public static final String COLUMN_COST_MULTIPLIER = "car_type.cost_multiplier";


    public  CarType[] findWhereNameEquals(String name) throws CarTypeDaoException;

    public  CarType[] findWhereCostMultiplierEquals(Float costMultiplier) throws CarTypeDaoException;


    public int countWhereNameEquals(String name) throws CarTypeDaoException;

    public int countWhereCostMultiplierEquals(Float costMultiplier) throws CarTypeDaoException;

}
