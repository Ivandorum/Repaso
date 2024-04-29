package Simulacros.ADA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Oculto {
    private static int inicio,end;

    public static void main(String[] args) {
        try(RandomAccessFile file = new RandomAccessFile("files/file.txt","rw")) {
            //List<String> list = new ArrayList<>();
            StringBuilder msgOculto = new StringBuilder();
            String line;
            //List<Integer> listWordLen = new ArrayList<>();

            while ((line = file.readLine()) != null) {
                int lineSize = line.length();
                int firstNum = lineSize -4;
                int secondNum = lineSize -2;
                int inicio = Integer.parseInt(line.substring(firstNum, firstNum +2));
                int end = Integer.parseInt(line.substring(secondNum));
                msgOculto.append(line.substring(inicio,end));
            }
            System.out.println(msgOculto);
            /*
            int wordLeng;
            file.seek(0);
            for (String line : list) {
                wordLeng = line.length() - 4;
                listWordLen.add(wordLeng);
            }

            file.seek(0);
            int sum = 0;
            for (int i = 0; i < listWordLen.size(); i++) {
                file.seek(listWordLen.get(i) + sum);
                System.out.println(file.readLine());
                sum += 11;
            }

             */

        }catch (IOException e) {
            System.err.println();
        }
    }
}
