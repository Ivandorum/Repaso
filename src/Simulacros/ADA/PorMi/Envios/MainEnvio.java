package Simulacros.ADA.PorMi.Envios;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainEnvio {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static final String INSERT = "INSERT INTO pedido (client_id,order_date,status,quantity,product,id) VALUES (?,?,?,?,?)";
    private static final String SELECT = "SELECT stock FROM inventory WHERE product_id = ?";
    private static final String UPDATE = "UPDATE inventory SET stock = stock - ? WHERE product_id = ?";
    private static final String INSERTSHIP = "INSERT INTO shipping (shipping_date,product_id,order_id) VALUES (?,?,?)";

    public static void makeOrder(List<Pedido> pedidos){
        try(final Connection conn = DriverManager.getConnection(URL,USER,PASS)){
            conn.setAutoCommit(false);
            try(PreparedStatement prInsert = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement prSelect = conn.prepareStatement(SELECT);
                PreparedStatement prUpdate = conn.prepareStatement(UPDATE);
                PreparedStatement prInsertShip = conn.prepareStatement(INSERTSHIP)){
                for (Pedido pedido:pedidos) {
                    prInsert.setInt(1,pedido.getClientId());
                    prInsert.setTimestamp(2, Timestamp.valueOf(pedido.getOrderDate()));
                    prInsert.setBoolean(3,pedido.isStatus());
                    prInsert.setInt(4,pedido.getQuantity());
                    prInsert.setInt(5,pedido.getProducId());
                    prInsert.executeUpdate();
                    ResultSet rs = prInsert.getGeneratedKeys();
                    int idInsert;
                    if(rs.next()){
                        idInsert = rs.getInt(1);
                        prSelect.setInt(1,pedido.getProducId());
                        ResultSet rsSelect = prSelect.executeQuery();
                        if(rsSelect.next()){
                            int stockInv = rsSelect.getInt(1);
                            if(stockInv >= pedido.getQuantity()){
                                prUpdate.setInt(1,pedido.getQuantity());
                                prUpdate.setInt(2,pedido.getProducId());
                                int res = prUpdate.executeUpdate();
                                if(res > 0){
                                    prInsertShip.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                                    prInsertShip.setInt(2,pedido.getProducId());
                                    prInsertShip.setInt(3,idInsert);
                                    prInsertShip.executeUpdate();
                                    System.out.println("Pedido del producto " + pedido.getProducId() + " enviado");
                                }else{
                                    conn.rollback();
                                    return;
                                }
                            }
                        }
                    }
                }
                conn.commit();
                conn.setAutoCommit(true);
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(1,LocalDateTime.now(),true,34,1));
        pedidos.add(new Pedido(2,LocalDateTime.now(),true,120,2));
        pedidos.add(new Pedido(1,LocalDateTime.now(),false,80,3));
        makeOrder(pedidos);
    }
}
