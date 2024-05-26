package ADA.RA2.jdbc.Tienda;

import javax.sound.midi.Soundbank;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MainTienda {


    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String pass = "root";

    private static final String selectSql = "SELECT COUNT(*) FROM tarjeta WHERE id = ?";
    private static final String updateSql = "UPDATE tarjeta SET card_number = ? WHERE id = ?";
    private static final String insertSql = "INSERT INTO tarjeta (card_number, expire_date) VALUES (?,?)";
    private static final String insertWithCustomerSql = "INSERT INTO tarjeta (card_number, expire_date,customer_id) VALUES (?,?,?)";
    private static final String customerCheckSql = "SELECT COUNT(*) FROM tarjeta WHERE customer_id = ?";

    //Recuerda el Set no acepta duplicados
    public static int[] renewMembershipBatch(Set<MembershipCard> data){
        int[] resultado = new int[2];
        try(final Connection conn = DriverManager.getConnection(url,user,pass)) {
            conn.setAutoCommit(false);
            try (PreparedStatement prSelect = conn.prepareStatement(selectSql);
                PreparedStatement prUpdate = conn.prepareStatement(updateSql);
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
                        resultado[0]++;
                    } else {
                        if(card.getCustomerId() == 0){ //Devuelve 0 si no tiene cliente asociado
                            prInsert.setString(1,card.getCardNumber());
                            prInsert.setTimestamp(2, Timestamp.valueOf(card.getExpireDate()));
                            prInsert.executeUpdate();
                            System.out.println("Se ha creado la tarjeta " + card.getCardNumber() + " con fecha de expiracion " + card.getExpireDate() + " sin cliente asociado");
                            resultado[1]++;
                        }else{ //En caso de que tenga un cliente asociado
                            prCustomerCheck.setInt(1,card.getCustomerId());
                            ResultSet rsCustomer = prCustomerCheck.executeQuery();
                            if(rsCustomer.next()){ //Si el cliente ya tiene una tarjeta asociada
                                System.out.println("El cliente ya tiene una tarjeta asociada");
                                conn.rollback();
                                resultado[0] = -1;
                                resultado[1] = -1;
                                return resultado;
                            }else{ //Si el cliente no tiene una tarjeta asociada
                                prInsertWithCustomer.setString(1,card.getCardNumber());
                                prInsertWithCustomer.setTimestamp(2, Timestamp.valueOf(card.getExpireDate()));
                                prInsertWithCustomer.setInt(3,card.getCustomerId());
                                prInsertWithCustomer.executeUpdate();
                                System.out.println("Se ha creado la tarjeta " + card.getCardNumber() + " con fecha de espiración " + card.getExpireDate() + " para el cliente con id " + card.getCustomerId());
                                resultado[1]++;
                            }
                        }
                    }
                }
                conn.commit();
                conn.setAutoCommit(true);
                return resultado;
            }catch (SQLException e){
                conn.rollback();
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Set<MembershipCard> data = new HashSet<>();
        data.add(new MembershipCard("1234FE", LocalDateTime.now().plusDays(7)));
        data.add(new MembershipCard("0000EE", LocalDateTime.now()));
        int[] nums = renewMembershipBatch(data);
        System.out.println(nums[0] + "" + nums[1]);
    }
}
