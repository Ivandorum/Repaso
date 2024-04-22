package ADA.RA1.ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjerFichero {
    public static void main(String[] args) {

        Path p = Path.of("files/file1.txt");
/*
        try {
            List<String> linea = Files.readAllLines(p)
                    .stream()
                    .filter(s -> s.contains("LOG"))
                    .map(s -> s.substring(s.indexOf("LOG")))
                    .collect(Collectors.toList());

            Files.write(Path.of("files/res.txt"),linea);

        } catch (IOException e) {
            e.printStackTrace();
        }

 */
        Path count = Path.of("files");
        countLinesInFIles(count);
    }

    public static void countLinesInFIles(Path path){


        try(Stream<Path> list = Files.find(path,Integer.MAX_VALUE,(path1, att) -> att.isRegularFile() && path.getFileName().toString().endsWith(".txt"))){
            int total = list.mapToInt(path1 -> {
                try {
                    return Files.readAllLines(path1).size();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).sum();
            System.out.println(total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
