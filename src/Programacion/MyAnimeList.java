package Programacion;

import java.lang.reflect.Array;
import java.util.List;

public class MyAnimeList {
    private static final int DEFAULT_INCREMENT = 5;

    private int[] lista;
    private int current;//COntiene la ultima posicion del elemento insertado

    public MyAnimeList() {
        lista = new int[DEFAULT_INCREMENT];
        current= 0;
    }

    public int get(int index){
        return lista[index];
    }

    public void resize(){
        int[] otro = new int[DEFAULT_INCREMENT + lista.length];
        copy(otro);
    }

    public void add(int add){
        if(current >= lista.length) {
            resize();
        }
            lista[current] = add;
            current++;
    }

    public int size(){
        return current;
    }

    public int remove(){
        current--;
        int delete = lista[current];
        lista[current] = 0;
        return delete;
    }

    public void print(){
        System.out.print("[");
        for (int i = 0; i < current; i++) {
            System.out.print(lista[i]);
        }
        System.out.print("]");
    }

    public void copy(int[] newLista){
        for (int i = 0; i < current; i++) {
            newLista[i] = lista[i];
        }
        lista = newLista;
    }

    public int remove(int pos){
        int delete = lista[pos];
        lista[pos] = 0;
        for (int i = pos; i < current; i++) {
            lista[i] = lista[i+1];
        }
        current--;
        return delete;
    }
    public static void main(String[] args) {
        MyAnimeList lista = new MyAnimeList();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        //lista.copy(lista.lista);
        lista.remove(2);
        lista.print();
    }
}
