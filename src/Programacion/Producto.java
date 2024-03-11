package Programacion;

/**
 * Clase POJO:
 * - Atributos, campos o variables de intancia
 * - COnstructor, por defecto, paremetrizado
 * - Getters y Setters
 * - Metodos
 * - ToString, hashcode, etc ...
 */

/**
 * Visibilidad:
 * - private
 * - protected: package y herencia (si no pones nada es protected solo de package)
 * - public
 */
/*
record Product(String nombre, double precio) {
    public static int id;
    public static void test(){
        System.out.println("si" + id);
    }
/*
    public static void main(String[] args) {
        Product p = new Product("", 33);
    }

}
*/

public class Producto implements Comparable<Producto>{

    public static final  int numPasillo = 1;
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public static void test(){
        System.out.println("si" + numPasillo);
    }

    public Producto() {
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public int compareTo(Producto o) {
        /**
         * 0 --> si son iguales
         * --> <0 si this es menor a o
         * *--> >0 si this es mayor a o
         */
        if(this.precio == o.precio){
            return 0;
        }else if (this.precio < o.precio){
            return 1;
        }else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
