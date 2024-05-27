package Simulacros.ADA.PorMi.Envios2;

import java.net.DatagramPacket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static final String url = "jdbc:mysql://localhost:33060/mydb";
    private static final String user = "root";
    private static final String pass = "admin";

    private static final String

    public static void processPedido(List<Pedido> pedidos){
        try(final Connection conn = DriverManager.getConnection(url,user,pass)){
            conn.setAutoCommit(false);
            try(PreparedStatement prInsert = conn.prepareStatement()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }
}
