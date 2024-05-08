package PSP.RA3.Subasta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSubasta {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            final CasaSubasta casaSubasta = new CasaSubasta();
            while (true) {
                Socket socket = serverSocket.accept();
                Thread hilo = new Thread(new ManejadorSubasta(socket,casaSubasta));
                hilo.start();
            }
        } catch (IOException e) {
            System.err.println();
        }
    }
}

class ManejadorSubasta implements Runnable {

    private Socket socket;
    private CasaSubasta casaSubasta;

    public ManejadorSubasta(Socket socket, CasaSubasta casaSubasta) {
        this.socket = socket;
        this.casaSubasta = casaSubasta;
    }

    @Override
    public void run() {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true)){
            for (int i = 0; i < 3; i++) {
                String message = input.readLine();
                String[] list = message.split("#");
                int idLote = Integer.parseInt(list[0]);
                String idCLiente = list[1];
                int precio = Integer.parseInt(list[2]);
                if(casaSubasta.aceptarSubasta(idLote,precio,idCLiente)) {
                    output.println("200");
                    break;
                }else{
                    output.println("Pera");
                }

            }
        } catch (IOException e) {
            System.err.println();
        }
    }
}
