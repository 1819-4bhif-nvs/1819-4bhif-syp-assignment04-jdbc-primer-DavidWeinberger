package at.htl.vehicle;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class VehicleTest {
    public static final String DRIVER_STRING= "org.apache.derby.jdbc.ClientDriver";
    public static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    public static final String USER = "app";
    public static final String PASSWORT = "app";
    public static  Connection conn;

    @BeforeClass
    public static void initJDBC(){
        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING ,USER, PASSWORT);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Verbindung zur Datenbank nicht möglich! " + e.getMessage() + "");
            System.exit(1);
        }
    }

    @AfterClass
    public  static void teardownJdbc(){
        try {
            if(conn != null || !conn.isClosed())
            {
                conn.close();;
                System.out.println("Good Bye");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void ddl(){
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE vehicle (" +
                "ID INT CONSTRAINT vehicle_pk PRIMARY KEY," +
                "brand VARCHAR(255) NOT NULL," +
                "type VARCHAR(255) NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
