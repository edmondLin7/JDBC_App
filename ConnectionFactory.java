package JdbcProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection = null;

    private ConnectionFactory() {

    }

    // Establish connection
    public static Connection getConnection() {
        if (connection == null) {
            // ResourceBundle bundle = ResourceBundle.getBundle("config");
            // cant resolve path of config.properties will resolve later
            String url = "jdbc:mysql://localhost:3306/demo"; //bundle.getString("url");
            String username = "root"; //bundle.getString("username");
            String password = "edmondlin"; //bundle.getString("password");
            try {
                connection = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
