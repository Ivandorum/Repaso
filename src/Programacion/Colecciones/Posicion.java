package Programacion.Colecciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * POsicion:
 * - numero, descripcion
 * - movimientos -> Map(String,Integer)
 *
 * COnstructor: con numero y descripcion. INiciamos el Map
 *
 * - getter de desc y map
 *
 * - addUbicacion (String direccion, Integer idPosicion)
 */

public class Posicion {
    private int numero;
    private String descripcion;
    private Map<String,Integer> movimientos;

    public Posicion(int numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
        movimientos = new HashMap<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Map<String, Integer> getMovimientos() {
        return movimientos;
    }

    public void addUbicacion(String direccion, Integer idPosicion){
        movimientos.put(direccion,idPosicion);
    }
}

/**
 * Juego:
 *
 * - Map<Integer, POsicion > posiciones
 *
 *  Constructor:
 *  crear el mapa. Y le añadimos POsiciones con los siguientes valores:
 *  1 - Marineford, cuartel general de la Marina
 *  2 - Isla Gyojin, estas debajo del mar en la tierra de lo tritones
 *  3 - Sabaody Archipelago, estas explorando cerca del Red Line
 *  4 - Impel Down, te hasn encarcelado en la prision submarina de máxima seguridad
 *  5 - Skypiea, has escapado a la isla en el cielo
 *  0 - Era todo un sueño, ahora continua duermiendo
 */

class Juego{
    private Map<Integer, Posicion> posiciones;

    public Juego() {
        posiciones = new HashMap<>();

        posiciones.put(1,new Posicion(1,"Marineford, cuartel general de la Marina"));
        posiciones.put(2,new Posicion(2,"Isla Gyojin, estas debajo del mar en la tierra de lo tritones"));
        posiciones.put(3,new Posicion(3,"Sabaody Archipelago, estas explorando cerca del Red Line"));
        posiciones.put(4,new Posicion(4,"Impel Down, te han encarcelado en la prision submarina de máxima seguridad"));
        posiciones.put(5,new Posicion(5,"Skypiea, has escapado a la isla en el cielo"));
        posiciones.put(6,new Posicion(0,"Era todo un sueño, ahora continua duermiendo"));
        posiciones.put(7,new Posicion(6, "La isla de kurohige, ahora eres su esclavo"));

        posiciones.get(1).getMovimientos().put("U",5);
        posiciones.get(1).getMovimientos().put("D",4);
        posiciones.get(1).getMovimientos().put("L",2);
        posiciones.get(1).getMovimientos().put("R",3);

        posiciones.get(2).getMovimientos().put("R",1);

        posiciones.get(5).getMovimientos().put("L",1);

        posiciones.get(3).getMovimientos().put("D",4);
        posiciones.get(3).getMovimientos().put("U",5);

        posiciones.get(4).getMovimientos().put("L",1);
        posiciones.get(4).getMovimientos().put("D",7);

        posiciones.get(5).getMovimientos().put("M",6);
        posiciones.get(4).getMovimientos().put("M",6);
        posiciones.get(3).getMovimientos().put("M",6);
        posiciones.get(2).getMovimientos().put("M",6);
        posiciones.get(1).getMovimientos().put("M",6);

        posiciones.get(7).getMovimientos().put("U",5);
        posiciones.get(7).getMovimientos().put("D",4);
        posiciones.get(7).getMovimientos().put("L",2);
        posiciones.get(7).getMovimientos().put("R",3);
        posiciones.get(7).getMovimientos().put("M",6);

        //Desde todas las posiciones puedes salir.
        posiciones.values().stream().forEach(posicion -> posicion.addUbicacion("M",0));
    }

    //Metodo play, empezamos en Marinefor y mostramos donde estamos.
    //Luego con un bucle infinito (M) iremos pidiendo al usuario que se mueva
    public void play(){
        Scanner sc = new Scanner(System.in);

        int pos = 1;
        String move;
        do{
            System.out.println("Estamos en " + posiciones.get(pos).getDescripcion());
            System.out.println("Adonde te quieres ir: " + posiciones.get(pos).getMovimientos().keySet());
             move = sc.nextLine();
            pos = posiciones.get(pos).getMovimientos().get(move);
        } while (!move.equals("M"));
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.play();
    }
}