package jdbc.tmp;

import logging.LoggerLoader;
import java.sql.*;
import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Query {

    private static final Logger logger = LoggerLoader.getLogger(Query.class);
    private Database conn;
    private Statement stmt;
    private String lastError;

    public Query(Database conn) throws SQLException {
        this.conn = conn;
        this.stmt = this.createStatement(conn.getConnection());
    }

    protected Statement createStatement(Connection conn) throws SQLException {
     return conn.createStatement();
    }

    public String getLastError() {
        return this.lastError;
    }
    /*
    protected void checkConnectionState() throws SQLException {
        if (conn.validate()) {
            return;
        }
        conn = JDBCConnectionPool.getInstance(conn.getDriver(), conn.getUrl(), conn.getLogin(), conn.getPassword()).getConnection();
        stmt = createStatement(conn.getConnection());
    }
     //*/
    public ResultSet executeQuery(String sql) throws SQLException {

        // If jdbc.connection was broken and we can not restore it we will fail.
//        checkConnectionState();
        ResultSet rs = this.stmt.executeQuery(sql);
        rs.setFetchSize(conn.getDriver().getFetchSize());
        return rs;

    }

    public int executeUpdate(String sql) throws SQLException {

        // If jdbc.connection was broken and we can not restore it we will fail.
//        checkConnectionState();
        return this.stmt.executeUpdate(sql);

    }

    public boolean close() {
        try {
            this.stmt.close();
        } catch (Exception e) {
            logger.error(e, e);
            return false;
        }
        return true;
    }

    public boolean isClosed() throws Exception {
        return this.stmt.isClosed();
    }

    public String processStringField(String fieldValue) {
        String thePattern = "\\\\";
        Pattern pattern = Pattern.compile(thePattern);
        Matcher matcher = pattern.matcher(fieldValue);
        fieldValue = matcher.replaceAll("\\\\\\\\");

        thePattern = "'";
        pattern = Pattern.compile(thePattern);
        matcher = pattern.matcher(fieldValue);
        fieldValue = matcher.replaceAll("\\\\'");
        return fieldValue;
    }

}
