package Simulacros.ADA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LecturaFIcheros {

    public static void findTheMostRepeatedWord(Path path){
        if(Files.isRegularFile(path) && Files.exists(path)){
            try {
                List<String> list = Files.readAllLines(path);
                for (String line: list) {
                    String[] words = line.split("//s");
                    int count = 0;
                    String wordMore;
                    for (int i = 0; i < words.length; i++) {
                        if(words[i].equals(words[i+1])){
                            count++;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
