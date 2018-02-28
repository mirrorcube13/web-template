package connection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrey on 10.01.2017.
 */
public class DbConnection {
    private static PoolProperties poolProperties;
    private static DataSource dataSource;
    static {
        poolProperties = new PoolProperties();
        poolProperties.setUrl("jdbc:mysql://localhost:3306/shop");
        poolProperties.setUsername("root");
        poolProperties.setPassword("root");
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource = new DataSource(poolProperties);
    }

    private DbConnection() {
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
