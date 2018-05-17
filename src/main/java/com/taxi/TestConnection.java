package com.taxi;

import jdbc.tmp.DBDriverBase;
import jdbc.tmp.DBTypes;
import jdbc.tmp.DBDriverFactory;
import jdbc.tmp.Database;
import jdbc.tmp.Query;
import java.sql.ResultSet;


public class TestConnection {


    public static void main(String[] args) throws Exception {
        DBDriverBase dbDriver = DBDriverFactory.createDriver(DBTypes.MYSQL);
        Database jdbcConn = new Database(dbDriver, "jdbc:mysql://localhost:3306/taxi_service", "root", "root");
        jdbcConn.connect();
        if (jdbcConn.validate()) {
            Query query = new Query(jdbcConn);
            ResultSet rs = query.executeQuery("select * from usersview");
            if (rs != null) {
                while (rs.next()) {
                    System.out.println("Login=" + rs.getString(2) + "; AccountStatus=" + rs.getString(3) + ";");
                }
            }
        } else {
            System.out.println("Database jdbc.connection error. See log file for more info.");
        }
    }

}
