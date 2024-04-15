package Programacion.Colecciones;

import Programacion.MyAnimeList;

import java.util.*;

class Dog{
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
public class TestCOl {

    /*
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(List.of(1,2,3,4,5));
        List<Integer> lista2 = new LinkedList<>();
        /*
        for (int i = 0; i < lista.size(); i++) {
            lista.add(1);
            System.out.println(lista.get(i));
            lista.remove(3);
        }
        ListIterator<Integer> it = lista.listIterator();
        while (it.hasNext()){
            int num = it.next();
            if(num == 3) {
                it.remove();
                it.next();
                it.previous();
                it.add(13);
            }
        }
        System.out.println(lista);
        System.out.println();
    }
    */

    public static void main(String[] args) {
        Map<String,String> idiomas = new HashMap<>();

        idiomas.put("es","español");
        idiomas.put("en","ingles");
        idiomas.put("fr","frances");
        idiomas.put("jp","japones");
        idiomas.put("mn","mono");

        idiomas.remove("mn","mono");
        System.out.println(idiomas.replace("en","espaninglish"));

        // keySet solo para la clave y values solo para el contenido. ENTRYsET PARA DEVOLVER TODO
        for (Map.Entry<String, String> entry : idiomas.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        Map<Integer, Map<String, String>> map = new HashMap<>();

        map.put(1,idiomas);
        map.get(1).remove("jp");
        System.out.println(map);

        //Creamos un Set con estos elementos
        //22,45,33,66,55,34,77
        //33,2,83,45,3,12,55
        Set<Integer> c1 = new HashSet<>(List.of(22,45,33,66,55,34,77));
        Set<Integer> c2 = new HashSet<>(List.of(33,2,83,45,3,12,55));

        //c1.addAll(c2); //UNION
        //System.out.println(c1);

        //c1.retainAll(c2);//INTERSECCION
        //System.out.println(c1);

        c1.removeAll(c2);//DIFERENCIA
        System.out.println(c1);
    }
}
