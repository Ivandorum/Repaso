package PSP.RA3.UDP.sensores;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.StringJoiner;

public class Sensor {
    private static final int TEMP_MIN = -60;
    private static final int TEMP_MAX = 60;
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket()){
            Random r = new Random();
            while(true) {
                double temp = r.nextDouble(TEMP_MIN, TEMP_MAX+1);
                int area = r.nextInt(0, 9999);
                StringJoiner joiner = new StringJoiner("#");
                joiner.add(String.valueOf(area));
                joiner.add(String.valueOf(temp));
                byte[] buffer = joiner.toString().getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 5000);
                socket.send(packet);
                Thread.sleep(5000);
            }
        } catch (SocketException e) {
            System.err.println("");
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
        } catch (InterruptedException e) {
            System.err.println("");
        }
    }
}
