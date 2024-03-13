package PSP.RA1.processos;

import java.io.*;
import java.util.Scanner;

public class MainOtro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1 creo el proceso
        ProcessBuilder builder = new ProcessBuilder("nslookup");

        /**
        * Hereda todo
         */
        //builder.inheritIO();

        //Hereda la entrada del proceso el output Necesita leer
        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        //Hereda la salida del proceso el input. Necesita esribir
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        try {
            Process process = builder.start();

            /*
            //escribimos en el proceso y leemos
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                PrintWriter writer = new PrintWriter(process.getOutputStream(),true)){
                writer.println("www.unicamp.es");
                String line = reader.readLine();
                while(line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
             */

            System.out.println(process.waitFor());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
