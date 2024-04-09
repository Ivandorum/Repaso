package ADA.RA1.ficheros;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ficheros {
    public static void main(String[] args) {

        /*
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
         */

        /**
         * walk, find, list
         * list - No entra en subcarpetas
         * walk, find : Entra en subcarpetas
         */

        /*
        try(Stream<Path> list = Files.walk(Path.of("."), Integer.MAX_VALUE)){
           list.filter(path -> Files.isRegularFile(path))
                   .sorted((o1, o2) -> {
                       try {
                           return (int) (Files.size(o2) - Files.size(o1));
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }
                   })
                   .limit(1)
                   .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

         */

        /*
        try(Stream<Path> list = Files.list(Path.of("."))){
            long numCarpetas = list.filter(path -> Files.isDirectory(path))
                            .count();
            System.out.println(numCarpetas);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
/*
        //Me pasan un nombre de fichero y tengo que decir si existe el fichero
        // o no en el directorio actual y sus directorios
        String fichero = "file1.txt";
        try(Stream<Path> list = Files.find(Path.of("."),Integer.MAX_VALUE,(path, att) -> path.getFileName().toString().equals(fichero))){
            list.forEach(path -> {
                if (!path.toString().isEmpty()){
                    System.out.println("existe");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

 */
/*
        //mostrar el tamaño total de todos los ficheros en el directorio y subdirectorios
        try(Stream<Path> list = Files.walk(Path.of("."),Integer.MAX_VALUE)){
            List<Path> paths = list.filter(path -> Files.isRegularFile(path))
                    .collect(Collectors.toList());
            int total = 0;
            for (Path pa: paths) {
                total += Files.size(pa);
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }

 */

        //MOstrar para un directorio numero de carpetas, numero de fichero, tamaño total del directorio y subdirectorios

        /*
        try(Stream<Path> list = Files.walk(Path.of("./src"),Integer.MAX_VALUE)){
            long numFiles = list.filter(path -> Files.isRegularFile(path))
                    .count();
            System.out.println(numFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(Stream<Path> list = Files.walk(Path.of("./src"),Integer.MAX_VALUE)){
            long numCarpetas = list.filter(path -> Files.isDirectory(path))
                    .count();
            System.out.println(numCarpetas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Stream<Path> list = Files.walk(Path.of("./src"),Integer.MAX_VALUE)){
            List<Path> paths =
            list.collect(Collectors.toList());
            int total = 0;
            for (Path pa: paths) {
                total += Files.size(pa);
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        /*
        //CUantos ficheros modificados esta semana
        try(Stream<Path> list = Files.walk(Path.of("."),Integer.MAX_VALUE)){
            long num = list.filter(path -> Files.isRegularFile(path))
                    .filter(path -> {
                        try {
                            return Files.getLastModifiedTime(path).toInstant().isAfter(
                                    LocalDateTime.now().minusDays(7).toInstant(ZoneOffset.UTC)
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .count();
            System.out.println(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        /*
        //Borra todos los ficheros con tamaño 0 bytes.
        //Pero antes de borrarlos queremos pedir confirmación
        Scanner sc = new Scanner(System.in);
        try(Stream<Path> list = Files.find(Path.of("."),Integer.MAX_VALUE,(path, att) -> att.isRegularFile() && att.size() == 0)){
            list.forEach(path -> {
                        System.out.println("Quieres matar? (El fichero)");
                        String res = sc.nextLine();
                        if (res.equals("si")) {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        //Quiero mostrar los directorios inutiles (los vacios)
        try(Stream<Path> list = Files.find(Path.of("."),Integer.MAX_VALUE,(path, att) -> att.isDirectory())){
           list.filter(path -> {
               try {
                   return(Files.list(path).count() == 0);
               } catch (IOException e) {
                   throw new RuntimeException();
               }
           }).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Leemos un fichero en forma de lista en lineas (readAllLines)
        //Queremos sacar la media de todos los numeros de un fichero
        //Ejemplo del fichero: 1 85 74 96 5
    }
}
