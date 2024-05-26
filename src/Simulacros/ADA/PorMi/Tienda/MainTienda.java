package Simulacros.ADA.PorMi.Tienda;

import java.net.Socket;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainTienda {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String pass = "root";

    public static final String select = "SELECT * FROM tarjeta WHERE id = ?";
    public static final String update = "UPDATE tarjeta SET card_number = ? WHERE id = ?";
    public static final String checkCliente = "SELECT COUNT(*) FROM tarjeta WHERE customer_id = ?";
    public static final String insertWithClient = "INSERT INTO tarjeta (card_number, expire_date, customer_id) VALUES (?,?,?)";
    public static final String insertWithoutCli = "INSERT INTO tarjeta (card_number, expire_date) VALUES (?,?)";

    public static int[] checkCard(List<Tarjeta> list){
        try(final Connection conn = DriverManager.getConnection(url,user,pass)){
            conn.setAutoCommit(false);
            try(PreparedStatement prSelect = conn.prepareStatement(select);
                PreparedStatement prUpdate = conn.prepareStatement(update);
                PreparedStatement prCheckCli = conn.prepareStatement(checkCliente);
                PreparedStatement prInsertWithCli = conn.prepareStatement(insertWithClient);
                PreparedStatement prInsertWithoutCli = conn.prepareStatement(insertWithoutCli)){
                int[] num = new int[2];
                int cardActu = 0;
                int cardInsert = 0;
                for (Tarjeta card: list){
                    prSelect.setInt(1,card.getId());
                    ResultSet rsSelect = prSelect.executeQuery();
                    if(rsSelect.next()){ // Si existe
                        prUpdate.setString(1,card.getCardNumber());
                        prUpdate.setInt(2,card.getId());
                        prUpdate.executeUpdate();
                        cardActu++;
                    }else{ // Si no existe
                        if(card.getCustomerId() != 0) { //Compruebo que tenga cliente asociado
                            prCheckCli.setInt(1, card.getCustomerId());
                            ResultSet rsCheck = prCheckCli.executeQuery();
                            int flag = 0;
                            if (rsCheck.next()) {
                                flag = rsCheck.getInt(1);
                            }
                            if (flag == 0) { // Si el cliente no tiene ninguna tarjeta
                                prInsertWithCli.setString(1, card.getCardNumber());
                                prInsertWithCli.setTimestamp(2, Timestamp.valueOf(card.getExpireDate()));
                                prInsertWithCli.setInt(3, card.getCustomerId());
                                prInsertWithCli.executeUpdate();
                                cardInsert++;
                                System.out.println("Se ha creado la tarjeta " + card.getId() + " con el usuario " + card.getCustomerId());
                            } else { // Si hay algun cliente asociado
                                System.out.println("Ese cliente ya tiene una tarjeta asociada");
                                conn.rollback();
                                num[0] = -1;
                                return num;
                            }
                        }else{ // Si viene sin cliente  la creo sin ninguno
                            prInsertWithoutCli.setString(1, card.getCardNumber());
                            prInsertWithoutCli.setTimestamp(2, Timestamp.valueOf(card.getExpireDate()));
                            prInsertWithoutCli.executeUpdate();
                            cardInsert++;
                            System.out.println("Se ha creado la tarjeta " + card.getId() + " sin un usuario asociado");
                        }
                    }
                }
                num[0] = cardActu;
                num[1] = cardInsert;
                conn.commit();
                conn.setAutoCommit(true);
                return num;
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        List<Tarjeta> list = new ArrayList<Tarjeta>();

        list.add(new Tarjeta("22E", LocalDateTime.now(),2));
        list.add(new Tarjeta("26D", LocalDateTime.now(),1));
        list.add(new Tarjeta("12B", LocalDateTime.now()));

        checkCard(list);
    }
}
