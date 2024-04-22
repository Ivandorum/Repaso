package ADA.RA1.ficheros;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//Acceso Secuencial
/**
 * Files.newBufferedReader
 * Files.newBufferedWritter
 */

//Acceso Aleatorio
public class RandomRoulette {

    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("files/file.dat","rw")) {
            /*
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.writeInt(33);//4
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.writeDouble(3.12);//8
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.writeChar('a');//2
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.writeBoolean(true);//1
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.writeFloat(12.09f);//4
            System.out.println(randomAccessFile.getFilePointer());
            randomAccessFile.writeUTF("carlos");//2 + 1 por carac. = 8
            System.out.println(randomAccessFile.getFilePointer());

            //quiero leer el string
            randomAccessFile.seek(randomAccessFile.length() - 8);
            System.out.println(randomAccessFile.readUTF());

        */

            /*
            //Sacar la media de numeros del fichero
                randomAccessFile.seek(0);
                double total = 0;
                int num = 0;
                while(randomAccessFile.length() != randomAccessFile.getFilePointer()) {
                    total += randomAccessFile.readInt();
                    num++;
                }
                System.out.println(total/num);
        */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * replaceNumberAt(3,5.4) --> tendremos un fichero con numeros double
         *
         * Hay que replace(1, 0.5) -> el primero por 0.5
         *
         */

        replaceNumberAt(2,8.2);
    }

    public static void replaceNumberAt(int id, double num){
        try(RandomAccessFile file = new RandomAccessFile("files/double.dat","rw")){
            int loc = id * 8 - 8;
            if(file.length() > loc) {
                file.seek(loc);
                file.writeDouble(num);
            } else {
                System.out.println("NO tengo tantos numeros");
            }
            file.seek(0);
            while(file.length() != file.getFilePointer()) {
                System.out.println(file.readDouble());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
