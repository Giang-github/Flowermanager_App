package flowermanager.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Database {
    
    public static Connection connectDB() {
    Connection connect = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://localhost/flowersmanager", "root", "");
        System.out.println("Connected to database.");
    } catch (Exception e) {
        //System.out.println("Failed to connect to database.");
        e.printStackTrace();
    }
    return connect;
}
    
    public static void closeConnection(Connection conn) {
    try {
        if (conn != null) {
            conn.close();
            System.out.println("Disconnected from database.");
        }
    } catch (SQLException e) {
        System.out.println("Failed to close connection.");
        e.printStackTrace();
    }
}


    
}
