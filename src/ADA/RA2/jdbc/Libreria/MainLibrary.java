package ADA.RA2.jdbc.Libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainLibrary {

    private static final String URL = "jdbc:mysql://localhost:33060/mydb";
    private static final String USER = "root";
    private static final String PASS = "admin";

    private static final String insertSql = "INSERT INTO book (title, price, quantity, autor_id) VALUES (?,?,?,?)";

    public static void processAuthorBooks(Map<Integer, Book[]> data){
        try(Connection conn = DriverManager.getConnection(URL,USER,PASS)) {
            conn.setAutoCommit(false);
            int contadorLibros = 0;
            try(PreparedStatement insertPr = conn.prepareStatement(insertSql)){
                for (int autor:data.keySet()) {
                    Book[] booksList = data.get(autor);
                    for (int i = 0; i < booksList.length; i++) {
                        Book book = booksList[i];
                        insertPr.setString(1,book.getTitle());
                        insertPr.setDouble(2,book.getPrice());
                        insertPr.setInt(3,book.getQuantity());
                        insertPr.setInt(4,autor);
                        contadorLibros++;

                        insertPr.executeUpdate();
                    }
                    System.out.println("Se han incluido " + contadorLibros + " libros para el autor " + autor);
                    contadorLibros = 0;
                }
                conn.commit();
                conn.setAutoCommit(true);
            }catch (SQLException e){
                conn.rollback();
                System.out.println("Error al insertar");
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Fallo en la conexion");
        }
    }

    public static void main(String[] args) {
        Map<Integer, Book[]> data = new HashMap<>();
        Book libro1 = new Book("El rey Arturo", 12.4, 12,1);
        Book[] estanteria;
        data.put(1,)
        processAuthorBooks(data);
    }
}
