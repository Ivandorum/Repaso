package Simulacros.ADA.PorMi.Envios2;

import java.net.DatagramPacket;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final String url = "jdbc:mysql://localhost:33060/mydb";
    private static final String user = "root";
    private static final String pass = "admin";

    private static final String insertSql = "INSERT INTO pedido (client_id,order_date,status,quantity,produc_id) VALUES (?,?,?,?,?)";
    private static final String updateSql = "UPDATE inventario SET stock = stock - ? WHERE product_id = ?";
    private static final String selectSql = "SELECT stock FROM inventario WHERE product_id = ?";
    private static final String insertShipSql = "INSERT INTO shipping";

    public static void processPedido(List<Pedido> pedidos){
        try(final Connection conn = DriverManager.getConnection(url,user,pass)){
            conn.setAutoCommit(false);
            try(PreparedStatement prInsert = conn.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
                PreparedStatement prUpdate = conn.prepareStatement(updateSql);
                PreparedStatement prSelect = conn.prepareStatement(selectSql)){
                for (Pedido pedido:pedidos) {
                    prInsert.setInt(1,pedido.getClientId());
                    prInsert.setTimestamp(2, Timestamp.valueOf(pedido.getOrderDate()));
                    prInsert.setBoolean(3,pedido.isStatus());
                    prInsert.setInt(4,pedido.getQuantity());
                    prInsert.setInt(5, pedido.getProducId());
                    prInsert.executeUpdate();
                    int idInsert;
                    ResultSet rs = prInsert.getGeneratedKeys();
                    if(rs.next()){
                        idInsert = rs.getInt(1);
                    }
                    prSelect.setInt(1,pedido.getProducId());
                    ResultSet rsSelect = prSelect.executeQuery();
                    int stock = 0;
                    if(rsSelect.next()){
                        stock = rsSelect.getInt(1);
                    }
                    if(stock >= pedido.getQuantity()) {
                        prUpdate.setInt(1, pedido.getQuantity());
                        prUpdate.setInt(2, pedido.getProducId());
                        int checkUpdate = prUpdate.executeUpdate();
                        if (checkUpdate > 0) { //Han habido cambios

                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }
}
