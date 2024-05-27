package Simulacros.PSP.Subasta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringJoiner;

public class Postores {

    private static final String IDCLIENTE = "12";

    public static void main(String[] args) {
        try(Socket socket = new Socket(InetAddress.getLocalHost(),5000)){
            try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(),true)){
                Random r = new Random();
                int lote;
                int precio;
                String linea;
                for (int i = 0; i < 3; i++) {
                    lote = r.nextInt(1,3);
                    precio = r.nextInt(1,99999);
                    StringJoiner joiner = new StringJoiner("#");
                    joiner.add(String.valueOf(lote));
                    joiner.add(IDCLIENTE);
                    joiner.add(String.valueOf(precio));
                    output.println(joiner);
                    if((linea = input.readLine()).equals("200")){
                        System.out.println("Subasta aceptada para el postor " + IDCLIENTE + " con precio " + precio);
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
