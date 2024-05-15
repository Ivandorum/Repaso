package PSP.RA3.UDP.race;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class ClientRace {
    private static final int ID_PARTICIPANTE= 1;
    private static final double LATITUD_MIN = 0;
    private static final double LATITUD_MAX = 99;
    private static final double LONGITUD_MIN = 0;
    private static final double LONGITUD_MAX = 99;
    public static void main(String[] args) {
        Random r = new Random();
        try(DatagramSocket socket = new DatagramSocket()) {
            while(true) {
                StringJoiner joiner = new StringJoiner(";");
                double lat = r.nextDouble(LATITUD_MIN, LATITUD_MAX);
                double lon = r.nextDouble(LONGITUD_MIN, LONGITUD_MAX);
                joiner.add(String.valueOf(ID_PARTICIPANTE));
                joiner.add(String.valueOf(lat));
                joiner.add(String.valueOf(lon));

                byte[] buffer = joiner.toString().getBytes();
                //paquete
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 5000);
                socket.send(packet);
                Thread.sleep(2000);
            }
        } catch (SocketException e) {
            System.err.println("");
        } catch (UnknownHostException e) {
            System.err.println("");
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
