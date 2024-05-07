package ADA.RA2.jdbc.Envios;

import java.sql.*;
import java.time.LocalDateTime;

public class MainShipping {

    private static final String url = "jdbc:mysql://localhost:33060/mydb";
    private final static String user = "root";
    private final static String pass = "admin";

    private final static String insertSql = "INSERT INTO pedido (client_id, order_date, status, quantity, product_id) VALUES (?,?,?,?,?)";
    private final static String stockActual = "SELECT stock FROM inventory WHERE product_id = ?";
    private final static String updateSql = "UPDATE inventory SET stock = stock - ? WHERE stock >= ? AND product_id = ?";
    //private final static String updateSql = "UPDATE inventory SET stock =  ? WHERE product_id = ?";
    private final static String insertShipp = "INSERT INTO shipping (shipping_date, product_id, order_id) VALUES (?,?,?)";

    public static void processOrder(Pedido pedido){
        try(final Connection conn = DriverManager.getConnection(url,user,pass)){
            conn.setAutoCommit(false);

            try(PreparedStatement preparedStatement = conn.prepareStatement(insertSql,PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement selectPr = conn.prepareStatement(stockActual);
                PreparedStatement updatePr = conn.prepareStatement(updateSql);
                PreparedStatement insertPrs = conn.prepareStatement(insertShipp)) {

                preparedStatement.setInt(1, pedido.getClientId());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(pedido.getOrderDate()));
                preparedStatement.setBoolean(3, pedido.isStatus());
                preparedStatement.setInt(4, pedido.getQuantity());
                preparedStatement.setInt(5, pedido.getProductId());

                int row = preparedStatement.executeUpdate();
                ResultSet rsId = preparedStatement.getGeneratedKeys();
                if(rsId.next()){
                int idOrder = rsId.getInt(1);

                if (row > 0) {
                    /**
                     *

                    selectPr.setInt(1, pedido.getProductId());
                    ResultSet rs = selectPr.executeQuery();
                    if (rs.next()) {
                        int stockA = rs.getInt("stock");
                        int stockCan = stockA - pedido.getQuantity();
                        if (stockCan > 0) {
                             *
                             */
                            //updatePr.setInt(1, stockCan);
                            updatePr.setInt(1,pedido.getQuantity());
                            updatePr.setInt(2,pedido.getQuantity());
                            updatePr.setInt(3, pedido.getProductId());
                            int rowUpdate = updatePr.executeUpdate();
                            if (rowUpdate > 0) {
                                insertPrs.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                                insertPrs.setInt(2, pedido.getProductId());
                                insertPrs.setInt(3, idOrder);
                                insertPrs.executeUpdate();
                                conn.commit();
                                System.out.println("Pedido del producto " + pedido.getProductId() + " enviado");
                                return;
                            } else {
                                System.out.println("No hay suficente stock");
                            }
                    }
                    conn.rollback();
                }

            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }

            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Fallo al iniciar la base de datos");
        }
    }
    public static void main(String[] args) {
        processOrder(new Pedido(32,LocalDateTime.now(),false,20,1));
    }
}

