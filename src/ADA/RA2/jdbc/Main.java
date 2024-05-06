package ADA.RA2.jdbc;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:33060/repaso";
        try(Connection conn = DriverManager.getConnection(url, "root","admin")){

            /*
            try(Statement stm = conn.createStatement()){
                String query = "select * from seat";
                ResultSet rs = stm.executeQuery(query);
                while(rs.next()){
                    System.out.println(rs.getInt("id") + " " + rs.getString(2) + " " +rs.getInt("aisle"));
                }
            }

             */

            /*
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

             */

            //Imprimir dado un numero de pasajero su asiento asignado
            //Si no tiene asiento, muestro sin asiento
            try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT s.number, s.aisle, p.nombre FROM " +
                    "passenger p JOIN seat s ON p.id=s.id WHERE p.number = ?")){
                preparedStatement.setString(1,"534");
                if(preparedStatement.execute()) {
                    ResultSet rs =  preparedStatement.getResultSet();
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                        System.out.println(rs.getString(2));
                        System.out.println(rs.getString(3));
                    }
                }
            }

            //Insertamos un pasajero
            try(PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO passenger (number, nombre, age, birth, seat_id) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1,"123");
                preparedStatement.setString(2,"Luis");
                preparedStatement.setInt(3,12);
                preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setInt(5, 5);
                int res = preparedStatement.executeUpdate(); //Devuelve 1 si funciona y 0 si esta vacio con esto en los metodos puedes devolver el valor y comprobar que funciona
                ResultSet idsGet = preparedStatement.getGeneratedKeys();
                if(idsGet.next()){
                    System.out.println(idsGet.getInt(1));
                }
            }


            //Queremos borrar la relación de un pasajero dado con asiento
            //que el pasajero no tenga asiento
            try(PreparedStatement preparedStatement = conn.prepareStatement("UPDATE passenger SET seat_id=NULL WHERE number=?")){
                preparedStatement.setString(1,"123");
                int colum = preparedStatement.executeUpdate();
                if(colum > 0){
                    System.out.println("Se ha borrado el asiento");
                }else{
                    System.out.println("Ya no esta reservado");
                }
            }

            //Borramos un asiento (que no tenga ningun pasajero) PD:Si tiene algun pasajero el asiento no te deja quitarlo.
            try(PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM seat WHERE id = ?")){
                preparedStatement.setInt(1,4);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.err.println();
        }

    }
}
