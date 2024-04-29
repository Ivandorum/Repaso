package Simulacros.ADA;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PrintThings {

    public static void main(String[] args) {
        Path p = Path.of(".");
        printDirectoryTree(p);
    }

    public static void printDirectoryTree(Path path){
        try(Stream<Path> list = Files.walk(path,Integer.MAX_VALUE)){
            list.forEach(path1 ->{
                int depth = path1.getNameCount();
                String arbol = "";
                for (int i = 0; i < depth; i++) {
                    arbol+="\t";
                }
                arbol+=path1.getFileName().toString();
                System.out.println(arbol);
            });
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}

