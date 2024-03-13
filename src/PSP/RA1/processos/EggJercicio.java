package PSP.RA1.processos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Creamos un proceso con el comando cmd
 * Queremos que muestre la consola de intellij lo que devuelve el proceso
 * Vamos a enviar los siguientes comandos:
 * dir
 * ipconfig
 */

public class EggJercicio {
    public static void main(String[] args) {

        String[] comandos = {"ls", "ifconfig"};
        ProcessBuilder builder = new ProcessBuilder("bash");
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            Process process = builder.start();
            try(PrintWriter writer = new PrintWriter(process.getOutputStream(),true)){
                for (int i = 0; i < comandos.length; i++) {
                    writer.println(comandos[i]);
                }
            }
            System.out.println(process.waitFor());
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NftJercicio {
    /**
     * Pedimos al usuario que introduzca una utl para lanzar en el dns
     * Leeremos lo qeu ha introducido el usuario y se lo madamos al proceso
     *
     */
    public static void main(String[] args) {
        ProcessBuilder builder = new ProcessBuilder("nslookup");
        Scanner sc = new Scanner(System.in);
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        try {
            Process process = builder.start();

            System.out.println("INtroduce una pagina");
            String url = sc.nextLine();
            try(PrintWriter writer = new PrintWriter(process.getOutputStream(), true)){
                if(url != null){
                    writer.println(url);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}