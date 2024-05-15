package PSP.RA3.TPV;

import PSP.RA3.UDP.race.InformeCarrera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerTPV {
    public static void main(String[] args) {
        final Inventory inventory = new Inventory();
        try(ServerSocket serverSocket = new ServerSocket(5500)){
            while(true) {
                Socket socket = serverSocket.accept();
                Thread hilo = new Thread(new TPVHandler(socket,inventory));
                hilo.start();
            }
        } catch (IOException e) {
            System.err.println();
        }
    }
}

class TPVHandler implements Runnable {

    private final Socket socket;
    private Inventory inventory;

    public TPVHandler(Socket socket, Inventory inventory){
        this.socket = socket;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                String message = input.readLine();
                String[] list = message.split(";");
                String product = list[0];
                int cantidad = Integer.parseInt(list[1]);
                inventory.almacenUpdate(cantidad,product);

        } catch (IOException e) {
            System.err.println();
        }
    }
}

class Inventory{

    private Map<String, Integer> almacen;

    public Inventory (){
        almacen = new HashMap<>();
    }

    public synchronized void almacenUpdate(int cantidad, String producto){
        if(almacen.containsKey(producto)) {
            Integer cantidadActual = almacen.get(producto);
            cantidadActual += cantidad;
            almacen.put(producto, cantidadActual);
        }else{
            almacen.put(producto,cantidad);
        }
    }
}