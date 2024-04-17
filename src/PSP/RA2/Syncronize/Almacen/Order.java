package PSP.RA2.Syncronize.Almacen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record Order(int id, String shoeType, int quantity) {

}

class ShoeWarehouse{
    private static final int MAX_ORDER = 10;
    private static final int MIN_ORDER = 0;
    private List<Order> orders = new ArrayList<>();



    public synchronized void receiveOrder(Order order){
        while(orders.size() >= MAX_ORDER) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        orders.add(order);
        notifyAll();
    }

    public synchronized Order fulfillOrder(){
        while(orders.size() <= MIN_ORDER) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
            Order order = orders.remove(0);
            notifyAll();
            return order;
    }
}

class Producer implements Runnable{
    private static final int MAX_RAN = 9999;
    private static final int MIN_RAN = 0;
    private static final int MIN_QUANTITY = 5;
    private static final int MAX_QUANTITY = 21;
    private static final String[] shoesType = {"Sneakers","Trainers","Sandals","Flip-Flops","Boots"};
    private ShoeWarehouse shoeWarehouse;

    public Producer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        Random r = new Random();

        int id = r.nextInt(MIN_RAN,MAX_RAN);
        String shoeType = shoesType[r.nextInt(MIN_RAN,shoesType.length)];
        int quantity = r.nextInt(MIN_QUANTITY,MAX_QUANTITY);

        Order order = new Order(id,shoeType,quantity);

        shoeWarehouse.receiveOrder(order);
    }
}

class Consumer implements Runnable{
    private final ShoeWarehouse shoeWarehouse;

    public Consumer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        Order order = shoeWarehouse.fulfillOrder();
        System.out.println("El pedido " + order + " ha sido completado");
    }
}

class Main {
    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        for (int i = 0; i < 3; i++) {
            Thread prod = new Thread(new Producer(shoeWarehouse));
            prod.start();
        }
        for (int i = 0; i < 3; i++) {
            Thread cons1 = new Thread(new Consumer(shoeWarehouse));
            cons1.start();
        }
    }
}