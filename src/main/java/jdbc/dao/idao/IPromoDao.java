package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

/**
  * DAO for the <b>promo</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: promo
  * ----------------------------------------------
  *     column: promo_id        INT
  *     column: name        VARCHAR
  *     column: conditions        VARCHAR
  *     column: cost_multiplier        FLOAT
  *
  * Primary Key(s):  promo_id
  * </pre>
  *
  */
public interface IPromoDao extends IDao<Promo,PromoDaoException>{

    public static final int COLUMN_POSITION_PROMO_ID = 1;
    public static final int COLUMN_POSITION_NAME = 2;
    public static final int COLUMN_POSITION_CONDITIONS = 3;
    public static final int COLUMN_POSITION_COST_MULTIPLIER = 4;

    public static final String COLUMN_PROMO_ID = "promo.promo_id";
    public static final String COLUMN_NAME = "promo.name";
    public static final String COLUMN_CONDITIONS = "promo.conditions";
    public static final String COLUMN_COST_MULTIPLIER = "promo.cost_multiplier";

    public  Promo[] findWhereNameEquals(String name) throws PromoDaoException;

    public  Promo[] findWhereConditionsEquals(String conditions) throws PromoDaoException;

    public  Promo[] findWhereCostMultiplierEquals(Float costMultiplier) throws PromoDaoException;

    public int countWhereNameEquals(String name) throws PromoDaoException;

    public int countWhereConditionsEquals(String conditions) throws PromoDaoException;

    public int countWhereCostMultiplierEquals(Float costMultiplier) throws PromoDaoException;

}
