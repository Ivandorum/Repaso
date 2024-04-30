package Simulacros.ADA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LecturaFIcheros {
    public static void main(String[] args) {
        Path p = Path.of("files/file.txt");
        findTheMostRepeatedWord(p);
    }

    public static void findTheMostRepeatedWord(Path path){
        if(Files.isRegularFile(path) && Files.exists(path)){
            Pattern p = Pattern.compile("\\s");
            try {
                List<String> list = Files.readAllLines(path)
                        .stream()
                        .flatMap(s -> p.splitAsStream(s)).toList();
                Map<String, Integer> map = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    if(map.containsKey(list.get(i))){
                        map.put(list.get(i),map.get(list.get(i))+1);
                    }else{
                        map.put(list.get(i),1);
                    }
                }
                System.out.println(map);
                map.entrySet().stream().sorted((o1, o2) -> o2.getValue()- o1.getValue())
                        .limit(1)
                        .forEach(System.out::println);
            } catch (IOException e) {
                System.err.println();
            }
        }

    }
}
