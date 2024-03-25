package ADA.RA1.ficheros;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Pattern;

public class Ficheros {
    public static void main(String[] args) {
        Path p = Path.of("files/file1.txt");
        try {
            if(!Files.exists(p)) {
                Files.createFile(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = Files.newBufferedReader(p);
             BufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)) {
            writer.newLine();
            writer.write("Seguimos escribiendo");

            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
