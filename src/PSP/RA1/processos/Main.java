package PSP.RA1.processos;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String[] comandos = {"bash"};
        ProcessBuilder builder = new ProcessBuilder(comandos);

        //Escribes el comando a mano. Necesario tener un BufferedReader, hace la funcion de buefferedWritter
        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);

        //Muestra en el intellij la salida del proceso, necesario tener un BUfferedWriter, hace la funcion de BUfferedReader
        //builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        try {
            Process process = builder.start();



            /*try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write("ifconfig");
                writer.newLine();
                writer.flush();
            }

             */



           try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String linea = reader.readLine();
                while (linea != null){
                    System.out.println(linea);
                    linea = reader.readLine();
                }
            }




            /*try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))){
                String linea = reader.readLine();
                while (linea != null){
                    System.out.println(linea);
                    linea = reader.readLine();
                }
            }
             */
            //int code = process.waitFor();
            //System.out.println(code);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




    public static void main2(String[] args) {
        /**
         * cmd, konsole: interprete de comandos (la consola)
         * /c: indica a la consola que ejecute el comando que lellega y se cierre
         * - dir: comparando para listar el direcotrio
         */
        String[] commandos = {"bash","-c","ls"};
        try {
            //1ar forma
            //Process process = Runtime.getRuntime().exec(commandos);

            //2 forma. Usar esta
            ProcessBuilder builder = new ProcessBuilder(commandos);
            builder.directory(new File("/home/ALU2K")); // SIRVE PARA INDICAR EL DIRECTORIO
            Process process1 = builder.start();

            /**
             * Process tiene 3 formas de entrada
             * - INputStream, leo lo que muestra
             * - OutpuStram, escribo en el proceso
             * - ErrorInputStream, leer los errores que muestra
             */

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process1.getInputStream()))){
                //0 = todo flama
                //1 = algo mal troncoç
                //waitFor -> Comprueba que funciona
                int code = process1.waitFor();
                String linea = reader.readLine();
                System.out.println(code);
                if (code == 0){
                    while (linea != null){
                        System.out.println(linea);
                        linea = reader.readLine();
                    }
                }
            }
            try(BufferedReader errorReader = new BufferedReader(new InputStreamReader(process1.getErrorStream()))){
                String linea = errorReader.readLine();
                while (linea != null){
                    System.out.println(linea);
                    linea = errorReader.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
