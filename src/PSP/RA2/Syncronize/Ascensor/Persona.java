package PSP.RA2.Syncronize.Ascensor;

import java.util.Random;

public class Persona implements Runnable{

    private final String id;
    private final int peso;
    private final Ascensor ascensor;


    private static final int MIN_UP = 20;
    private static final int MAX_UP = 40;


    public Persona(String id, int peso, Ascensor ascensor) {
        this.id = id;
        this.peso = peso;
        this.ascensor = ascensor;
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            System.out.println(id + " esta preparado para asaltar el ascensor " + "con peso " + peso);
            boolean pass = false;
            synchronized (ascensor) {
                while (!pass) {
                    pass = ascensor.canPass(this);
                    if (!pass) {
                        System.out.println("Esperando");
                        ascensor.wait();
                    }
                }
                Thread.sleep(r.nextInt(MIN_UP, MAX_UP + 1) * 1000);
                System.out.println("Has llegado a tu destino " + id);
                ascensor.salirAscensor(this);
                ascensor.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public int getPeso() {
        return peso;
    }

}
