package ADA.RA2.jdbc.Tienda;

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

    //Recuerda el Set no acepta duplicados
    public static int[] renewMembershipBatch(Set<MembershipCard> data){
        try(Connection conn = DriverManager.getConnection(url,user,pass)) {
            conn.setAutoCommit(false);
            try (PreparedStatement prSelect = conn.prepareStatement(selectSql);
                PreparedStatement prUpdate = conn.prepareStatement(updateSql)) {
                for (MembershipCard card :data) {
                    prSelect.setInt(1,card.getId());
                    ResultSet rs = prSelect.executeQuery();
                    if(rs.next()){ //existe tarjeta
                        prUpdate.setString(1,card.getCardNumber());
                        prUpdate.setInt(2,card.getId());
                        prUpdate.executeUpdate();

                    }
                }

            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Set<MembershipCard> data = new HashSet<>();
        data.add(new MembershipCard("1234FE", LocalDateTime.now().plusDays(7)));
        data.add(new MembershipCard("0000EE", LocalDateTime.now()));
        renewMembershipBatch(data);
    }
}
