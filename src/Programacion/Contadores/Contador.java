package Programacion.Contadores;


import Programacion.Producto;

import java.util.Arrays;
import java.util.Collections;

public class Contador {
    /*
    public static int obtenerSuma(int n1, int n2) {
        return n1 + n2;
    }

    //Para obligar que nadie pueda crear contadores
    private Contador(){}
     */

    static int x;
    int y;

    public Contador(){ //scope/ambito

        x+=2;
        y++;
    }
    public static int obtenerCubo(){
        return x * x;
    }
}
class TestContador{
    public static void main(String[] args) {
        int[] att = {1,2,3,4,5,6,7,8,9,0}; //Con valor

        Producto[] productos = new Producto[5];
        productos[0] = new Producto("leche", 3);
        productos[1] = new Producto("aceite", 3);

        Arrays.sort(productos);
        System.out.println(Arrays.toString(productos));

    }
}
/*
class TestContador{
    public static void main(String[] args) {
        Contador c5 = new Contador();
        Contador c6 = new Contador();
        Contador c7 = new Contador();
        System.out.println(Contador.obtenerCubo()); //X=6 y=1

    }
}

 */