package Controller.db_Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class to connect to the database
public class db_Connection {

    // Connection to the database
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Hj2vZuCL*<7DX#c/";

    // Method to connect to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
