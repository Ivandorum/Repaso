package PSP.RA2.Syncronize.Coffe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TazaCafe: Identificador, tipo cafe. Con metodo Beber --> imprime el mensaje: El cliente necesita odiando el cafe (poner el tipo)
 * Cafeteria: almacenara una lista de tazas de cafe pedidas. Es compartido :w:
 *      Metodos: pedirCafe() le pasamos una taza de cafe,
 *      servirCafe() --> servimos el último que se ha pedido
 *
 *     ClienteCafeteria: es un hilo y realiza un pedido de cafe (id y tipo) --> random: espresso, solo, latte,
 *     cortado, capuccino, descaffeinado, americano.
 *
 *     Barista: solo sirve cafes, una vez servido imprime el mensaje de beber
 */

public class MainCoffee {
    public static void main(String[] args) {
        final Cafeteria cafeteria = new Cafeteria();
        for (int i = 0; i <5; i++) {
            Thread ivan = new Thread( new Cliente(cafeteria));
            ivan.start();
        }

        Thread camarero = new Thread(new Barista(cafeteria));
        camarero.start();

    }
}

class Barista implements Runnable{

    private final Cafeteria cafeteria;

    public Barista(Cafeteria cafeteria) {
        this.cafeteria = cafeteria;
    }

    @Override
    public void run() {
        TazaCafe cafe = cafeteria.servirCafe();
        cafe.beber();
    }
}

class Cliente implements Runnable{
    private static final String[] tipos = {"espresso", "solo", "latte", "cortado"," capuccino", "descafeinado", "americano"};
    private static final String[] nombres = {"espresso", "solo", "latte", "cortado"," capuccino", "descafeinado", "americano"};
    private final Cafeteria cafeteria;

    public Cliente(Cafeteria cafeteria) {
        this.cafeteria = cafeteria;
    }

    @Override
    public void run() {
        Random r = new Random();
        TazaCafe cup = new TazaCafe(tipos[r.nextInt(0,7)],r.nextInt(1,10000));
        cafeteria.pedirCafe(cup);
    }
}
class Cafeteria{
    private List<TazaCafe> menu;

    public Cafeteria(){
        this.menu = new ArrayList<>();
    }

    public void pedirCafe(TazaCafe cafe){
        synchronized (this) {
            while (menu.size() >= 3) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            menu.add(cafe);
            notifyAll();
        }
    }
    public TazaCafe servirCafe(){
        synchronized (this){
            while (menu.size()<=0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            TazaCafe taza =this.menu.get(menu.size()-1);
            notifyAll();
            return taza;
       }
    }
}

class TazaCafe{
    private String tipo;
    private int id;

    public TazaCafe(String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }

    public void beber(){
        System.out.println("El cliente no le gusta el cafe " + tipo);
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }
}