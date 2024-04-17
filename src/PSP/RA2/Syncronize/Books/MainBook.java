package PSP.RA2.Syncronize.Books;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Book{
    private String isbn;
    private String title;
    private String cliente;

    public Book(String isbn, String title, String cliente) {
        this.isbn = isbn;
        this.title = title;
        this.cliente = cliente;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void read(){
        System.out.println("El cliente "+ cliente +" ha recibido el libro" + isbn + " " + title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

class BookCounter{
    private static final int MIN_CLI = 0;
    private static final int MAX_CLI = 4;
    private static final List<Book> libros = new ArrayList<>();

    public synchronized void orderBook(Book book){
        while(libros.size()>= MAX_CLI) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        libros.add(book);
        notifyAll();
    }
    public synchronized Book deliverBook(){
        while(libros.size()<= MIN_CLI) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Book book = libros.remove(0);
        notifyAll();
        return book;
    }
}

class BookCustomer implements Runnable{
    private static final String[] titulos = {"El puente del viejo", "No hay dos sin tres", "El robo a Carlos", "Misterio de lo Misterioso"};
    private static final String[] isbn = {"A-3749-374J", "L-4783-937J", "P-8345-ASDP", "1-S5F7-9862"};
    private static final int MIN_RAN = 0;
    private static final int MAX_RAN = 4;

    private final BookCounter bookCounter;
    private String nombre;

    public BookCustomer(String nombre, BookCounter bookCounter) {
        this.bookCounter = bookCounter;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random r = new Random();
        Book book = new Book(isbn[r.nextInt(MIN_RAN,MAX_RAN)],titulos[r.nextInt(MIN_RAN, titulos.length)],nombre);
        bookCounter.orderBook(book);
        System.out.println("Se ha solicitado el libro por pedido del cliente " + nombre);
    }
}

class BookEmployee implements Runnable{
    private final BookCounter bookCounter;

    public BookEmployee(BookCounter bookCounter) {
        this.bookCounter = bookCounter;
    }

    @Override
    public void run() {
        Book book = bookCounter.deliverBook();
        book.read();

    }
}

public class MainBook{
    public static void main(String[] args) {
        final BookCounter bookCounter = new BookCounter();
        for (int i = 0; i < 5; i++) {
            Thread cliente = new Thread(new BookCustomer("Luis",bookCounter));
            cliente.start();
        }
        for (int i = 0; i < 2; i++) {
            Thread empleado = new Thread(new BookEmployee(bookCounter));
            empleado.start();
        }
    }
}
