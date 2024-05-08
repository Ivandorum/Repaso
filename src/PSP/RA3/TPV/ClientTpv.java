package PSP.RA3.TPV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class ClientTpv {
    private static final int CANTIDAD_MAX = 50;
    private static final int CANTIDAD_MIN = 5;
    private static final String[] produc = {"leche", "pan", "donuts", "aceite", "chocolate", "jamon", "patata", "atun", "bonito"};

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 5500)) {
            try(PrintWriter output = new PrintWriter(socket.getOutputStream(),true)){
                Random r = new Random();
                while(true) {
                    int cantidad = r.nextInt(CANTIDAD_MIN, CANTIDAD_MAX + 1);
                    StringJoiner joiner = new StringJoiner(";");
                    joiner.add(produc[r.nextInt(0, produc.length)]);
                    joiner.add(String.valueOf(cantidad));
                    output.println(joiner);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println();
        }
    }
}
