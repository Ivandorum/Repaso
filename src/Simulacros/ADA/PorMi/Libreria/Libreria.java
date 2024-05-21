package Simulacros.ADA.PorMi.Libreria;

import java.sql.*;
import java.util.*;

public class Libreria {

    private static final String URL = "jdbc:mysql://localhost:33060/mydb";
    private static final String USER = "root";
    private static final String PASS = "admin";

    private static final String INSERT = "INSERT INTO book (title, price, quantity, autor_id) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE book SET price = price * 1.5 WHERE id = ?";

    public static void processBooks(Map<Integer,List<Book>> map){
        try(Connection conn = DriverManager.getConnection(URL,USER,PASS)){
            conn.setAutoCommit(false);
            try(PreparedStatement prInsert = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
                PreparedStatement prUpdate = conn.prepareStatement(UPDATE)) {
                for (Map.Entry<Integer, List<Book>> list : map.entrySet()) {
                    int contadorBook = 0;
                    for (Book book : list.getValue()) {
                        prInsert.setString(1,book.getTitle());
                        prInsert.setDouble(2,book.getPrice());
                        prInsert.setInt(3,book.getQuantity());
                        prInsert.setInt(4,list.getKey());
                        prInsert.executeUpdate();
                        contadorBook++;
                        ResultSet rsInsert = prInsert.getGeneratedKeys();
                        int idInsertado;
                        if(rsInsert.next()){
                            idInsertado = rsInsert.getInt(1);
                            if(book.getQuantity() >= 10) {
                                prUpdate.setInt(1, idInsertado);
                                prUpdate.executeUpdate();
                            }
                        }
                    }
                    System.out.println("Se han incluido " + contadorBook + " libros para el autor " + list.getKey());
                }
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        final Map<Integer, List<Book>> map = new HashMap<>();
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        books1.add(new Book("No hay 2 sin 3", 12.5,10));
        books1.add(new Book("El regreso de los gachas", 15,8));
        books2.add(new Book("La llegada del examen", 8,23));
        map.put(1,books1);
        map.put(2,books2);
        processBooks(map);
    }
}
