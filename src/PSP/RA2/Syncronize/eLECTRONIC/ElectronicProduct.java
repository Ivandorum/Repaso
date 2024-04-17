package PSP.RA2.Syncronize.eLECTRONIC;

import java.util.ArrayList;
import java.util.List;
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
    private static final int MAX_CANTIDAD = 20;
    private static final int MIN_CANTIDAD = 0;
    private int cantidad;

    public ElectronicStore(int cantidad) {
        this.cantidad = cantidad;
    }

    public synchronized void placeOrder(ElectronicProduct product, int cantidad){
        while(this.cantidad >= MAX_CANTIDAD){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.add(product);
        this.cantidad += cantidad;
        notifyAll();
    }

    public void fulfillOrder(){
        while(cantidad<=MIN_CANTIDAD){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        productos.remove(0);
        this.cantidad--;
        notifyAll();
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
            ElectronicProduct product = new ElectronicProduct(r.nextInt(0,5),20.9);
            electronicStore.placeOrder();
    }
}