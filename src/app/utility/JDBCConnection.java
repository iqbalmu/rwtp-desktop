package app.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection() {
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/db_rwt_3",
                    "root",
                    "123"
            );
        }catch (ClassNotFoundException | SQLException ext) {
            System.out.println(ext.getMessage());
        }

        return conn;
    }
}
