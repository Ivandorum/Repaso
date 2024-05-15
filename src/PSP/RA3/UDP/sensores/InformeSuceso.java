package PSP.RA3.UDP.sensores;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class InformeSuceso {
    private StringBuilder texto;
    public  InformeSuceso(){
        this.texto = new StringBuilder();
    }

    public synchronized String addSuccesoALInforme(String succeso){
        texto.append(succeso).append("#").append(LocalDateTime.now()).append("\n");
        return texto.toString();
    }
}
