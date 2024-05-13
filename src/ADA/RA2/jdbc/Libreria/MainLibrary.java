package ADA.RA2.jdbc.Libreria;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MainLibrary {

    private static final String URL = "jdbc:mysql://localhost:33060/mydb";
    private static final String USER = "root";
    private static final String PASS = "admin";
    private static final int CANTIDAD_MAX = 10;


    private static final String updateSql = "UPDATE book SET price = price + (price * 0.5) WHERE id = ?";
    private static final String insertSql = "INSERT INTO book (title, price, quantity, autor_id) VALUES (?,?,?,?)";
    private static final String selectSql = "SELECT title, price, quantity FROM book WHERE id = ?";


    public static void processAuthorBooks(Map<Integer, Book[]> data){
        try(Connection conn = DriverManager.getConnection(URL,USER,PASS)) {
            conn.setAutoCommit(false);
            int contadorLibros = 0;
            try(PreparedStatement insertPr = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement updatePr = conn.prepareStatement(updateSql);
                PreparedStatement selectPr = conn   .prepareStatement(selectSql)){
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

                        ResultSet rs = insertPr.getGeneratedKeys(); //Para usar el id del libro insertado
                        if(rs.next()) {
                            int id = rs.getInt(1);
                            if (book.getQuantity() >= CANTIDAD_MAX) { //Actualizamos el precio si la cantidad es mayor a 10
                                updatePr.setInt(1, id);
                                updatePr.executeUpdate();
                            }
                            selectPr.setInt(1,id);
                            ResultSet rsSelect = selectPr.executeQuery();
                            if(rsSelect.next()){
                                String title = rsSelect.getString(1);
                                 double price = rsSelect.getDouble(2);
                                int quantity = rsSelect.getInt(3);
                                System.out.println("Libro " + title + " con precio " + price + " y cantidad " + quantity + " se ha añadido");
                              }
                        }
                    }
                    System.out.println("Se han incluido " + contadorLibros + " libros para el autor " + autor);
                    contadorLibros = 0;
                }
                conn.commit();
                conn.setAutoCommit(true);
            }catch (SQLException e){
                conn.rollback();
                System.err.println("Error al insertar");
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Fallo en la conexion");
        }
    }

    public static void main(String[] args) {
        Map<Integer, Book[]> data = new HashMap<>();
        Book libro1 = new Book("El rey Arturo", 12.4, 12);
        Book libro2 = new Book("Sword Art Online", 10.4, 5);
        Book libro3 = new Book("Your Lie in April", 8.4, 40);
        Book[] autor1 = {libro1,libro3};
        Book[] autor2 = {libro2};
        data.put(1,autor1);
        data.put(2,autor2);
        processAuthorBooks(data);
    }
}


/**
 *  private static final String selectSql = "SELECT title, price, quantity FROM book WHERE id = ?";
 *
 *  PreparedStatement selectPr = conn   .prepareStatement(selectSql))
 *
 *  ResultSet rsSelect = selectPr.executeQuery();
 *                             if(rsSelect.next()){
 *                                 String title = rsSelect.getString(1);
 *                                 double price = rsSelect.getDouble(2);
 *                                 int quantity = rsSelect.getInt(3);
 *                             }
 */