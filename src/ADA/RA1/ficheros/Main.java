package ADA.RA1.ficheros;

import javax.sound.midi.Soundbank;
import java.net.Socket;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;


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

        Function<String, Integer> test = s -> {
            return s.length();
        };
        System.out.println( test.apply("adios"));


        //Crea una función que devuelva la potencia  de dos de un numero dado
        Function<Integer,Integer> potter = potencia -> potencia*potencia;
        System.out.println(potter.apply(4));

        //A partir de los dos functions anteriores, concadena el resultado de
        //las dos funciones
        System.out.println(test.andThen(potter).apply("supercalifragilisticoespialidoso"));


        //Crea una lista con 4 textos. Usa Arrays.asList, se llamara convertirListaEnMap, devuelve un map
        //Le pasamos dos parametro, uno de la lista y otro será una función
        //El mapa es un Hashmap<String, INteger>, el string es cada elemento de la lista
        //y el integer será la longitud de ese string

        //List <String> textos = Arrays.asList("archeron","mei","shogun","doctora mei");

        //convertirListaEnMap(textos,test);

        /**
         * Streams
         * 3 partes:
         * - colección
         * - operac. intermedias (1 o mas)
         * - operac. terminal (1)
         */

        System.out.println( List.of("no","nno","nnoo","soo","si").stream()
                .filter(st -> st.length() > 3)
                .map(text -> text.toUpperCase())
                .map(len -> len.length())
                .filter(num -> num >4)
                .collect(Collectors.toList()));

        //Usando Streams itera sobre una lista de enteros y muestrala
        List.of(1,2,3,4,0,5,6,7,8).stream()
                .forEach(num -> System.out.println(num));

        //Crea una lista de String, comprueba si algun elemento o texto contiene la
        //letra a y muestra.
        System.out.println(List.of("pera", "naranja", "melon","manzana", "limon").stream()
                .filter(st -> st.contains("a"))
                .collect(Collectors.toList()));

        //Devuelve la suma de los numeros negativos de la siguietne lista:
        //58,9,-4,10,-1
        System.out.println(List.of(58,9,-4,-7,10,-1).stream()
                .filter(num -> num < 0)
                .mapToInt(value -> value)
                .sum()
        );

        //Como el anterior, pero devolvemos el valor maximo
        System.out.println(List.of(58,9,-4,-7,10,-1).stream()
                .max((o1, o2) -> o1-o2));

        //Creamos una lista de palabras, devolevemos una lista con las que empiezen por c, ordenado alfabeticamente
        System.out.println(List.of("queso","camaleon","carlos","comadreja","macaron").stream()
                .filter(st -> st.startsWith("c"))
                .sorted()
                .collect(Collectors.toList()));

        //Creamos una lista de 3 empleados, muestra el nombre de los empleado que tengan mas de 30 tacos


        List<Employee> esclavos = Arrays.asList(
                new Employee("Pablo",25, 23333),
                new Employee("Federico", 24, 14000),
                new Employee("Alonso", 33, 32999)
        );
        Deapartment it = new Deapartment("IT", esclavos);
        Deapartment rrhh = new Deapartment("RRHH", List.of(
                new Employee("Paca", 69,41000),
                new Employee("Javier", 20,36000),
                new Employee("Alonso", 33, 32999)
        ));
        List<Deapartment> severo = Arrays.asList(it,rrhh);

        //mostrar el nombre de todos los empleados de todos los dtps
        severo.stream()
                .flatMap(deapartment -> deapartment.employees().stream())
                        .forEach(employee -> System.out.println(employee.name()));

        //cuenta cunatos empelados estan trabajando con edad mayor de 30 años
        //de todos los departamentos ordenados por nombre y sin repetidos.
        System.out.println( severo.stream()
                .flatMap(deapartment -> deapartment.employees().stream())
                .filter(employee -> employee.edad() > 30)
                .map(employee -> employee.name())
                .distinct()
                .count());

/*
        esclavos.stream()
                .filter(employee -> employee.edad() > 30)
                .forEach(employee -> System.out.println(employee.name()));

        //Calcula la mdeia salarial de los empleados de menos de 25 años
        System.out.println( esclavos.stream()
                .filter(employee -> employee.edad() < 25)
                .mapToDouble(value -> value.salary())
                .average());

        //Muestra el nombre de los 2 empleados con mayor aslario
        esclavos.stream()
                .sorted((o1, o2) -> (int) (o2.salary() - o1.salary()))
                .skip(1)
                .limit(1)
                .forEach(employee -> System.out.println(employee.name()));



 */

    }

    /*
    public static Map<String, Integer> convertirListaEnMap(List<String> lista, Function <String, Integer> func){
        Map<String, Integer> mapa = new HashMap<>();
        //recorremos la lista
        for (String texto: lista) {
            mapa.put(texto, func.apply(texto));
        }
    }

     */
}

record Deapartment(String name, List<Employee> employees){

}


record Employee(String name, int edad, double salary){

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
