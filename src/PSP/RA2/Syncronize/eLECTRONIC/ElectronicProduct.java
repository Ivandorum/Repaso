package PSP.RA2.Syncronize.eLECTRONIC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ElectronicProduct {
    private String nombre;
    private double precio;

    public ElectronicProduct(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public void readyToDeliver(){
        System.out.println("El producto " + nombre +" ha sido empaquetado y esta listo para ser entregado");
    }

    @Override
    public String toString() {
        return "ElectronicProduct{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}

class ElectronicStore{
    private static final List<ElectronicProduct> productos = new ArrayList<>();
    private static final List<Integer> listCantidad = new ArrayList<>();
    private static final int MAX_CANTIDAD = 20;
    private static final int MIN_CANTIDAD = 0;
    private int cantidad = 0;

    public synchronized void placeOrder(ElectronicProduct product, int cantidad){
        while(this.cantidad >= MAX_CANTIDAD){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.add(product);
        listCantidad.add(cantidad);
        this.cantidad += cantidad;
        notifyAll();
    }

    public synchronized ElectronicProduct fulfillOrder(){
        while(cantidad<=MIN_CANTIDAD){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ElectronicProduct p = productos.remove(0);
        this.cantidad -= listCantidad.get(0);
        listCantidad.remove(0);
        notifyAll();
        return p;
    }
}

class Customer implements Runnable{
    private static final int MIN_CAN = 5;
    private static final int MAX_CAN = 15;
    private final String[] productos = {"Laptop", "Tablet", "Impresora 3D", "Monitor 4K", "Tarjeta de red"};
    private final ElectronicStore electronicStore;

    public Customer(ElectronicStore electronicStore) {
        this.electronicStore = electronicStore;
    }

    @Override
    public void run() {
        Random r = new Random();
        int cantidad = r.nextInt(MIN_CAN,MAX_CAN);
            ElectronicProduct product = new ElectronicProduct(productos[r.nextInt(0,5)],20.9);
            electronicStore.placeOrder(product,cantidad+1);
    }
}


class Packager implements Runnable{

    private final ElectronicStore store;

    public Packager(ElectronicStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        ElectronicProduct p = store.fulfillOrder();
        System.out.println("El pedido " + p + " ha sido enviado");
    }
}
class Main{
    public static void main(String[] args) {
        ElectronicStore store = new ElectronicStore();
        for (int i = 0; i < 2; i++) {
            Thread customer = new Thread(new Customer(store));
            customer.start();
        }
        for (int i = 0; i < 6; i++) {
            Thread packager = new Thread(new Packager(store));
            packager.start();
        }
    }
}