package PSP.RA1.hilos;

import java.time.LocalDateTime;
import java.util.Random;

public class TestHIlo {
    public static void main(String[] args) {

        //Creo dos hillos heredados de fred y uno suma los 100 primeros numeros y el otro realiza la potencia de 2 de los 100 primero numeros
       /*
        Suma suma = new Suma();
        suma.start();
        Potencia potencia = new Potencia();
        potencia.start();

        */

        //Simula una carrera entree varios corredores. Cada corredor se
        //representa con un hilo de Runnable. Dentro de cada hilo se le pasa un nombre
        //dentro del metodo run, se recorre una distancia random
        // entre 1 -10, y voy recorriendo hasta llegar a 100. Cada vez que corro
        //esa distancia duermo 100 ms

        //En el main lanzamos 5 corredores para ver cual llega antes a la meta

        /*
        Thread thread1 = new Thread(new Corredor("Patri"));
        Thread thread2 = new Thread(new Corredor("Carlos"));
        Thread thread3 = new Thread(new Corredor("Pablo"));
        Thread thread4 = new Thread(new Corredor("Ivan"));
        Thread thread5 = new Thread(new Corredor("Alvaro"));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
         */

        /**
         * 2. UN restaurante con varios camareros y un chef. Utiliza hilos para los camareros  y para el chef
         *  Los camareros los haremos con Thread.
         *  El chef tiene nombre frances
         *  El chef prepara los platos y hasta que no esten listos no se pueden servir. Dentro del chef
         *  simulamos la creación de platos. Vamos a crear 100 platos de canaletas, se tarda 50 ms
         *  Cada camarero tiene un id unico y un numero de mesas random que servir,
         *  entre 30-50 mesas. El camarero tiene que servir a todas las mesas antes
         *  de irse a casa, la simulacion de servir una mesa es un tiempo entre
         *  100 -500 ms
         *  Main, tiene 3 camareros. MOstrar cuando se van a casa.
         */

        /*
        Chef chef = new Chef("Ratatouille");
        chef.start();

        Camarero camarero1 = new Camarero(1,chef);
        Camarero camarero2 = new Camarero(2,chef);
        Camarero camarero3 = new Camarero(3,chef);

        camarero1.start();
        camarero3.start();
        camarero2.start();

         */

        /**
         * 3. SImularemos una fabrica. Utilizaremos hilos con clases anónimas ambas (Runnable, Thread)
         * para representar diferentes etapas en el proceso de fabricación de figuras.
         * Cada hilo debe realizar una tarea independiente, tendremos la de ensamblaje 30 ms, pintura 150ms y embalaje 10ms.
         * Vamos a fabricar 10 figuras con un id unico.
         */

        Thread ensamble = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Se ha ensamblado, la figura");
                }
            }
        };


        Thread pintura = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        ensamble.join();
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Se ha terminado de pintar, la figura");
                }
            }
        };


        Runnable embalar = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        pintura.join();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Terminado de embalar la figura");
                }
            }
        };
        Thread embalarHIlo = new Thread(embalar);

            Thread figura = new Thread(){
                @Override
                public void run() {
                    ensamble.start();
                    pintura.start();
                    embalarHIlo.start();
                    System.out.println("Figura terminada");
                }
            };

    }
}


/*
class Chef extends Thread{

    private final int PLATOS = 100;
    private final int LIMPIAR_TEMP = 51;

    private String nombre;

    public Chef(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void run() {
        int faltan = PLATOS;
        do{
            faltan--;
            try {
                Thread.sleep(LIMPIAR_TEMP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(faltan != 0);
        System.out.println( nombre + " dice: Deberian de estar emplatando ya");
    }
}

class Camarero extends Thread{
    private int id;
    private Chef chef;
    private final int MIN_MESAS = 30;
    private final int MAX_MESAS = 51;
    private final int MIN_WAIT = 100;
    private final int MAX_WAIT = 501;

    public Camarero(int id, Chef chef){
        this.id = id;
        this.chef = chef;
    }

    @Override
    public void run() {
        try {
            chef.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        Random r = new Random();
        int restantes = r.nextInt(MIN_MESAS,MAX_MESAS);
        do {
            restantes --;
            try {
                Thread.sleep(r.nextInt(MIN_WAIT,MAX_WAIT));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(restantes != 0);
        System.out.println("Por fin el camarero " + id + " se pudo ir a casa a las " + LocalDateTime.now());
    }
}

 */

/*
class Corredor implements Runnable {

    private String nombre;
    private final int meta = 100;
    private final int MIN_RUN = 1;
    private final int MAX_RUN = 11;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        Random distancia = new Random();
        int recorrido = 0;
        while(true){
            try {
            recorrido += distancia.nextInt(MIN_RUN,MAX_RUN);
            if(recorrido >= meta){
                System.out.println(nombre + " acaba de llegar a la meta");
                break;
            }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


 */

/*
class Suma extends Thread{
    @Override
    public void run() {
        int suma = 0;
        for (int i = 0; i <= 100; i++) {
            suma += i;
        }
        System.out.println("Numeros sumados del 0 al 100: " + suma);
    }
}
class Potencia extends Thread {
    @Override
    public void run() {
        int suma = 0;
        int poten;
        for (int i = 0; i <= 100; i++) {
            poten = i * i;
            suma += poten;
        }
        System.out.println("La suma de las potencias de 2 de los 100 primeros numeros es igual a  " + suma);
    }

}

 */
