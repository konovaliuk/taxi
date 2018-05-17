package jdbc.tmp;

import logging.LoggerLoader;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DBDriverMysql extends DBDriverBase{
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final Logger logger = LoggerLoader.getLogger(DBDriverMysql.class);

    public DBDriverMysql() throws ClassNotFoundException {
        super();
    }

    @Override
    protected String getDriverClass() {
        return DRIVER_CLASS;
    }

    @Override
    protected boolean registerDriver() {
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            logger.error(e, e);
            return false;
        }
        return true;
    }

    
}
