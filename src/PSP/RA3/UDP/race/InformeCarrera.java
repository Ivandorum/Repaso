package PSP.RA3.UDP.race;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class InformeCarrera {

    private StringBuilder texto;

    public InformeCarrera() {
        texto = new StringBuilder();
    }

    public synchronized void actualizarInforme(String datosRecibidos){
        texto.append(datosRecibidos)
                .append(";")
                .append(LocalDateTime.now().toString());
        try {
            BufferedWriter bw = Files.newBufferedWriter(Path.of("files/informe_carrera.txt"), StandardOpenOption.APPEND,StandardOpenOption.CREATE);
            bw.write(texto.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.err.println("");
        }
        /*
        PrintWriter pw = new PrintWriter(new FileWriter("informe_carreta.txt),)
        pw.println(texto.toString());
         */
        /*
        BufferedWriter bw = new BufferedWriter(new FileWriter("files/informe_carrera.txt));
        bw.write(texto.toString());
        bw.newLine();
        bw.flush();
         */
    }
}
