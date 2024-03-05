package ADA.RA1.ficheros;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;



public class Main {
    public static void main(String[] args) {

        //En los parentesis solo pones algo si el metodo abstracto necesita algo
        //En caso que recibe dos se pone asi: (int n,String x)
        Printable pr = (int n) -> {
            System.out.println(n);
        };
        pr.test(3);

        Predicate<Integer> pred = (e) -> e == 2 ;
        System.out.println(pred.test(3));

        //1. Mayor a 10
        Predicate<Integer> mayor = (e) -> e > 10;
        if(mayor.test(9)){

        }

        //2. Menor a 20
        Predicate<Integer> menor = (e) -> e < 20;
        menor.test(34);

        //3. Unir los dos
        System.out.println((mayor.and(menor).negate().test(93)));

        BiPredicate<Integer,Integer> bipred = (uno, duo) -> {
            if(uno > duo && duo == 0 && uno > 5){
                return true;
            }
            return false;
        };

        List<String> esposas = List.of("Abc", "def", "ghi", "jkl");
        //4. Predicado qeu empieaza por A
        Predicate<String> empiezaA = (esposa) -> esposa.startsWith("A");
        //5. Predicado que me diga si tiene una longitud de 3
        Predicate<String> mayor3 = (esposa) -> esposa.length() == 3;

        //6 . Comprobar que elementos cumplen ambos
        for (int i = 0; i < 3; i++) {
            empiezaA.and(mayor3).test(esposas.get(i));
        }
    }
}

//UN unico metodo abstracto
@FunctionalInterface
interface Printable{

    //variables finales
    public static final int numreo = 9;

    //metodo abstracto
    void test(int n);

    //Para poder poner un cuerpo a la interfaz
    default void print(){
        System.out.println("");
    }
}
