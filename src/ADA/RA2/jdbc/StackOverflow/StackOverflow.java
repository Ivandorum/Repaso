package ADA.RA2.jdbc.StackOverflow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StackOverflow {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:33060/repaso";
        try(Connection conn = DriverManager.getConnection(url, "root","admin")){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
