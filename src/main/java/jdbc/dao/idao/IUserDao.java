package jdbc.dao.idao;

import jdbc.dto.*;
import jdbc.exception.*;

public interface IUserDao extends IDao<User,UserDaoException>{

    public static final int COLUMN_POSITION_USER_ID = 1;
    public static final int COLUMN_POSITION_TYPE = 2;
    public static final int COLUMN_POSITION_LOGIN = 3;
    public static final int COLUMN_POSITION_PASSWORD = 4;

    public static final String COLUMN_USER_ID = "user.user_id";
    public static final String COLUMN_TYPE = "user.type";
    public static final String COLUMN_LOGIN = "user.login";
    public static final String COLUMN_PASSWORD = "user.password";


    public  User[] findWhereTypeEquals(String type) throws UserDaoException;

    public  User[] findWhereLoginEquals(String login) throws UserDaoException;

    public  User[] findWherePasswordEquals(String password) throws UserDaoException;


    public int countWhereTypeEquals(String type) throws UserDaoException;

    public int countWhereLoginEquals(String login) throws UserDaoException;

    public int countWherePasswordEquals(String password) throws UserDaoException;

}
