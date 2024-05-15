package PSP.RA3.UDP.race;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorRace {
    public static void main(String[] args) {
        InformeCarrera infome = new InformeCarrera();
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                Thread hilo = new Thread(new HiloCarrera(packet,infome));
                hilo.start();
            }
        } catch (SocketException e) {
            System.err.println("Error al crear el socket");
        } catch (IOException e) {
            System.err.println("Error al recibir el paquete");
        }
    }
}

class HiloCarrera implements Runnable{

    private final DatagramPacket packet;
    private final InformeCarrera informe;

    public HiloCarrera(DatagramPacket packet, InformeCarrera informe){
        this.packet = packet;
        this.informe = informe;
    }

    @Override
    public void run() {
        String message = new String(packet.getData(), 0, packet.getLength());
        informe.actualizarInforme(message);
    }
}