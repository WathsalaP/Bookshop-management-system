package frontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/pahana_edu_db"; // replace with your DB name
    private static final String USER = "root";
    private static final String PASSWORD = "your_password_here"; // use the password you set

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
