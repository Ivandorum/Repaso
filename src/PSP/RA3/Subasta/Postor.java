package PSP.RA3.Subasta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringJoiner;

public class Postor {

    private static final int IDCLIENTE = 1;
    private static final int IDLOTEMIN = 1;
    private static final int IDLOTEMAX = 3;
    private static final int PRECIOMIN = 1;
    private static final int PRECIOMAX = 9999;

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 5000)) {
            try(PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                Random r = new Random();
                StringJoiner joiner = new StringJoiner("#");
                for (int i = 0; i < 3; i++) {
                    int idLote = r.nextInt(IDLOTEMIN, IDLOTEMAX + 1);
                    int precio = r.nextInt(PRECIOMIN, PRECIOMAX);
                    joiner.add(String.valueOf(idLote));
                    joiner.add(String.valueOf(IDCLIENTE));
                    joiner.add(String.valueOf(precio));
                    output.println(joiner);
                    String res = input.readLine();
                    if (res.equals("200")){
                        break;
                    }
                }
            }

        }catch (IOException e) {
            System.err.println();
        }
    }
}
