package ADA.RA2.jdbc;


import java.sql.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:33060/repaso";
        try(Connection conn = DriverManager.getConnection(url, "root","admin")){
            try(Statement stm = conn.createStatement()){
                String query = "select * from seat";
                ResultSet rs = stm.executeQuery(query);
                while(rs.next()){
                    System.out.println(rs.getInt("id") + " " + rs.getString(2) + " " +rs.getInt("aisle"));
                }
            }

            //Imprimir la tabla passenger
            try(Statement stm = conn.createStatement()){
                String query = "select * from passenger";
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()){
                    LocalDateTime birth = rs.getTimestamp("birth").toLocalDateTime();
                    System.out.println(rs.getInt("id") + " " +
                            rs.getString("number") + " " +
                            rs.getString("nombre") + " " +
                            rs.getInt("age") + " " + birth + " " +
                            rs.getString("email") + " " +
                            rs.getInt("seat_id"));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
