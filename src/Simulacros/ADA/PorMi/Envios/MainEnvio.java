package Simulacros.ADA.PorMi.Envios;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class MainEnvio {

    private static final String URL = "jdbc:mysql://localhost:33060/mydb";
    private static final String USER = "root";
    private static final String PASS = "admin";

    private static final String INSERT = "INSERT INTO pedido (client_id,order_date,status,quantity,product,id) VALUES (?,?,?,?,?)";

    public static void makeOrder(List<Pedido> pedidos){
        try(final Connection conn = DriverManager.getConnection(URL,USER,PASS)){
            try(PreparedStatement prInsert = conn.prepareStatement(INSERT)){
                for (Pedido pedido:pedidos) {
                    prInsert.setInt(1,pedido.getClientId());
                    prInsert.setTimestamp(2, Timestamp.valueOf(pedido.getOrderDate()));
                    prInsert.setBoolean(3,pedido.isStatus());
                    prInsert.setInt(4,pedido.getQuantity());
                    prInsert.setInt(5,pedido.getProducId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
