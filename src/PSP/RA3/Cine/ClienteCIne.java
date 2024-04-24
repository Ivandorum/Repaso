package PSP.RA3.Cine;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringJoiner;

public class ClienteCIne {

    public static void main(String[] args) {
        final String id = "1331";
        try(Socket socket = new Socket(InetAddress.getLocalHost(),5000)) {
            try(PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                String res;
                int numSeat = 1;
                do {
                    StringJoiner s  = new StringJoiner("#");
                    s.add(id);
                    s.add(String.valueOf(numSeat));
                    numSeat++;
                    pw.println(s);
                    res = input.readLine();
                }while(!res.equals("200"));
            }

        }catch (IOException e) {
            System.err.println("Error al conectar al servidor");
        }
    }
}
