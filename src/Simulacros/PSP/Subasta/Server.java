package Simulacros.PSP.Subasta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            Subasta subasta = new Subasta();
            while(true) {
                Socket socket = serverSocket.accept();
                Thread hilo = new Thread(new HandlerSubasta(socket, subasta));
                hilo.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HandlerSubasta implements Runnable{

    private final Socket socket;

    private final Subasta subasta;

    public HandlerSubasta(Socket socket,Subasta subasta) {
        this.socket = socket;
        this.subasta = subasta;
    }

    @Override
    public void run() {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true)){
            for (int i = 0; i <3; i++) {
                String message = input.readLine();
                String[] list = message.split("#");
                int lote = Integer.parseInt(list[0]);
                int precio = Integer.parseInt(list[2]);
                if (subasta.realizarPuja(precio, lote)) { //true = si se acepto
                    output.println("200");
                    break;
                } else {
                    output.println("400");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}