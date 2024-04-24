package PSP.RA3.Cine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerCine {
    public static void main(String[] args) {
        final Cine cine = new Cine();
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            while (true) {
                Socket socket = serverSocket.accept();
                Thread maneja = new Thread(new ManejadorReserva(socket,cine));
                maneja.start();
            }
        } catch (IOException e) {
            System.err.println("El servidor no furula");
        }
    }
}

class ManejadorReserva implements Runnable{

    private Socket socket;
    private Cine cine;

    public ManejadorReserva(Socket socket, Cine cine) {
        this.socket = socket;
        this.cine = cine;
    }

    @Override
    public void run() {
        try(PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            String receive;
            while((receive = input.readLine()) != null) {
                String[] list = receive.split("#");
                String pers = list[0];
                int asiento = Integer.parseInt(list[1]);
                if(asiento <= 30 && asiento >=1) {
                    int codigo = cine.reservarAsiento(asiento, pers);
                    if (codigo == -1) {
                        pw.println("401");
                    } else {
                        pw.println("200");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Cine{
    private final Map<Integer,String> asientos = new HashMap<>();

    public synchronized int reservarAsiento(int asiento, String pers){
        if(asientos.containsKey(asiento)){
            return -1;
        }else {
            asientos.put(asiento, pers);
            return 1;
        }
    }
}
