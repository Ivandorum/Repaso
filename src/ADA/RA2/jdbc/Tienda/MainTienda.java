package ADA.RA2.jdbc.Tienda;

import javax.sound.midi.Soundbank;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MainTienda {

    private static final String selectSql = "SELECT COUNT(*) FROM membership_card WHERE id = ?";
    private static final String url = "jdbc:mysql://localhost:33060/mydb";
    private static final String user = "root";
    private static final String pass = "admin";
    private static final String updateSql = "UPDATE membership_card SET card_number = ? WHERE id = ?";
    private static final String cardCheckSql = "SELECT customer_id FROM membership_card WHERE id = ";
    private static final String insertSql = "INSERT INTO membership_card (card_number, expire_date) VALUES (?,?)";
    private static final String insertWithCustomerSql = "INSERT INTO membership_card (card_number, expire_date,customer_id) VALUES (?,?,?)";
    private static final String customerCheckSql = "SELECT COUNT(*) FROM membership_card WHERE customer_id = ?";

    //Recuerda el Set no acepta duplicados
    public static int[] renewMembershipBatch(Set<MembershipCard> data){
        try(final Connection conn = DriverManager.getConnection(url,user,pass)) {
            conn.setAutoCommit(false);
            try (PreparedStatement prSelect = conn.prepareStatement(selectSql);
                PreparedStatement prUpdate = conn.prepareStatement(updateSql);
                PreparedStatement prCardCheck = conn.prepareStatement(cardCheckSql);
                PreparedStatement prInsert = conn.prepareStatement(insertSql);
                PreparedStatement prCustomerCheck = conn.prepareStatement(customerCheckSql);
                PreparedStatement prInsertWithCustomer = conn.prepareStatement(insertWithCustomerSql)) {
                for (MembershipCard card :data) {
                    prSelect.setInt(1,card.getId());
                    ResultSet rs = prSelect.executeQuery();
                    if(rs.next()){ //existe tarjeta
                        prUpdate.setString(1,card.getCardNumber());
                        prUpdate.setInt(2,card.getId());
                        prUpdate.executeUpdate();
                    } else {
                        prCardCheck.setInt(1,card.getId());
                        ResultSet rsIdCustomer = prCardCheck.executeQuery();
                        int idCustomer = 0;
                        if(rsIdCustomer.next()){
                            idCustomer = rsIdCustomer.getInt(1);
                        }

                        if(idCustomer == 0){ //Devuelve 0 si no tiene cliente asociado
                            prInsert.setString(1,card.getCardNumber());
                            prInsert.setTimestamp(2, Timestamp.valueOf(card.getExpireDate()));
                            prInsert.executeUpdate();
                            System.out.println("Se ha creado la tarjeta " + card.getCardNumber() + " con fecha de expiracion " + card.getExpireDate() + " sin cliente asociado");
                        }else{ //En caso de que tenga un cliente asociado
                            prCustomerCheck.setInt(1,card.getCustomerId());
                            ResultSet rsCustomer = prCustomerCheck.executeQuery();
                            int flag = 0;
                            if(rsCustomer.next()){
                                flag = rsCustomer.getInt(1);
                            }
                            if(flag > 0){ //Si el cliente ya tiene una tarjeta asociada
                                System.out.println("El cliente ya tiene una tarjeta asociada");
                                conn.rollback();
                                return new int[]{-1};
                            }else{ //Si el cliente no tiene una tarjeta asociada
                                prInsertWithCustomer.setString(1,card.getCardNumber());
                                prInsertWithCustomer.setTimestamp(2, Timestamp.valueOf(card.getExpireDate()));
                                prInsertWithCustomer.setInt(3,card.getCustomerId());
                                prInsertWithCustomer.executeUpdate();
                                System.out.println("Se ha creado la tarjeta " + card.getCardNumber() + " con fecha de espiración " + card.getExpireDate() + " para el cliente con id " + card.getCustomerId());
                            }
                        }

                    }
                }
                conn.setAutoCommit(true);
            }catch (SQLException e){
                conn.rollback();
                System.err.println();
            }
        } catch (SQLException e) {
            System.err.println();
        }
    }

    public static void main(String[] args) {
        Set<MembershipCard> data = new HashSet<>();
        data.add(new MembershipCard("1234FE", LocalDateTime.now().plusDays(7)));
        data.add(new MembershipCard("0000EE", LocalDateTime.now()));
        renewMembershipBatch(data);
    }
}
