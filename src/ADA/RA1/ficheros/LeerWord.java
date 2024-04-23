package ADA.RA1.ficheros;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class LeerWord {

    /*
    public static void main(String[] args) {
        String word = "username";
        long inicioLinea = 0;
        try(RandomAccessFile file = new RandomAccessFile("files/file1.txt","rw")){
            while(file.length() != file.getFilePointer()) {
                String line = file.readLine();
                if(line.contains(word)){
                    line = line.replace(word, word.toUpperCase());
                    file.seek(inicioLinea);
                    file.writeBytes(line);
                }
                inicioLinea = file.getFilePointer();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce palabra: ");
        String word = sc.nextLine();
        String sameLong;
        long inicioLinea = 0;
        do{
            System.out.println("Introduce otra palabra de la misma longitud: ");
            sameLong = sc.nextLine();
            if(word.length() != sameLong.length()){
                System.out.println("\nNo tienen la misma longitud\n");
            }
        }while(word.length() != sameLong.length());

        try(RandomAccessFile file = new RandomAccessFile("files/text.dat","rw")){
            while(file.length() != file.getFilePointer()) {
                String linea = file.readLine();
                if (linea.contains(word)) {
                    linea = linea.replace(word, sameLong);
                    file.seek(inicioLinea);
                    file.writeBytes(linea);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
file.writeUTF("Hola como estas");
        file.writeUTF("Berenjena con sabor");
        file.writeUTF("Me encante el aceite");
        file.writeUTF("Podria comer mucho mas");
 */