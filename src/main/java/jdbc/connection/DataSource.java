package jdbc.connection;

import com.mysql.jdbc.Connection;

public interface DataSource {
    Connection getConnection();
}
