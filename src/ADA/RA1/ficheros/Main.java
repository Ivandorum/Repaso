package ADA.RA1.ficheros;

import java.util.*;
import java.util.function.*;


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
        for (int i = 0; i < esposas.size(); i++) {
            if(empiezaA.and(mayor3).test(esposas.get(i))){
                System.out.println(esposas.get(i));
            }
        }

        List<Aldea> aldeas = Arrays.asList(
                new Aldea("Konoha", 15000),
                new Aldea("Murcia", 1000),
                new Aldea("Patri", 10),
                new Aldea("Marina", 15),
                new Aldea("Nueva Dehli", 1000000000)
        );

        //7. Ahora me psa una lista y un numero y si esa aldea espieza por la letra N y tengas mas de 100.000 habitantes.
        // MOstramos la ciudades que cumplan eso

        BiPredicate<String,Integer> preAldea = (letra,habitante) ->  letra.startsWith("N") && habitante > 100000;

        for (int i = 0; i < aldeas.size(); i++) {
            if(preAldea.test(aldeas.get(i).getNombre(),aldeas.get(i).getHabitantes())){
                System.out.println(aldeas.get(i));
            }
        }

        Consumer<String> imprimirTexto = (t) -> System.out.println(t);
        imprimirTexto.accept("Dolares");

        //8. Consumer que imprima una Aldea que le pase.
        Consumer<Aldea> imprimirAldea = (chi) -> System.out.println(chi);
        imprimirAldea.accept(new Aldea("Paldea", 1000000));

        //9. mOstramos todas las ciudades de la lista utilizando el consumer de antes
        for (int i = 0; i < aldeas.size(); i++) {
            imprimirAldea.accept(aldeas.get(i));
        }

        //10. Pasamos dos String y lo imprimo por espacio.
        BiConsumer<String, String> animales = (p,a) -> System.out.println(p + " " + a);
        animales.accept("Periquito", "Antilope");

        Map<String, String> contactos = new HashMap<>();
        contactos.put("Patri", "Plaza Barcelona");
        contactos.put("Ivan", "Plaza Madrid");
        contactos.put("Pablo", "Pisos Azules");

        //11. Mostrar los contactos, nombre y direccion  separados por espacio
        for (Map.Entry<String, String> contac: contactos.entrySet()) {
            animales.accept(contac.getKey(),contac.getValue());
        }

        //Existe y ya
        Supplier<Aldea> productoCuidad = () -> new Aldea("Esternocleidomastoideo", 123412);
        Aldea a = productoCuidad.get();
        System.out.println(a);

        //12. Crea una lista con 10 String, luego utilizando muestre la ultima letra
        //de cada uno de los string de la lista
        List<String> lista = List.of("Si","No","Puede","Carlos","Nartuo","Patri","pitufo","leon","telefono","OnePiece");
        Consumer<String> preComp = (c) -> System.out.println(c.charAt(c.length() -1));
        for (int i = 0; i < lista.size(); i++) {
            preComp.accept(lista.get(i));
        }
    }
}

class Aldea{
    private String nombre;
    private int habitantes;

    public Aldea(String nombre, int habitantes) {
        this.nombre = nombre;
        this.habitantes = habitantes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHabitantes() {
        return habitantes;
    }

    @Override
    public String toString() {
        return "Aldea{" +
                "nombre='" + nombre + '\'' +
                ", habitantes=" + habitantes +
                '}';
    }
}
//UN unico metodo abstracto
@FunctionalInterface
interface Printable{

    //variables finales
    public static final int numero = 9;

    //metodo abstracto
    void test(int n);

    //Para poder poner un cuerpo a la interfaz
    default void print(){
        System.out.println("");
    }
}
