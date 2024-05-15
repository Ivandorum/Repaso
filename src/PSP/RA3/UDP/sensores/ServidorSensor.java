package PSP.RA3.UDP.sensores;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorSensor {

    //Multihilo, compartido, 24x7
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(5000)){
            final InformeSuceso informe = new InformeSuceso();
            while(true) {
                byte[] buffer = new byte[30];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                Thread hilo = new Thread(new HiloSensor(packet,informe));
                hilo.start();
            }
        } catch (SocketException e) {
            System.err.println("");
        } catch (IOException e) {
            System.err.println("");
        }
    }
}

class HiloSensor implements  Runnable{

    private final DatagramPacket packet;
    private final InformeSuceso informe;

    public HiloSensor(DatagramPacket packet, InformeSuceso informe) {
        this.packet = packet;
        this.informe = informe;
    }

    @Override
    public void run() {
        String message = new String(packet.getData(),0,packet.getLength());
        String[] text = message.split("#");
        double temp = Double.parseDouble(text[1]);
        if(temp>40 || temp<-10){
            String escribir = informe.addSuccesoALInforme(message);
            try {
                PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Path.of("files/infomesTemp.txt")),true);
                pw.println(escribir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
