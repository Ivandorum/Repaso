package PSP.RA1.hilos;

public class CreacionFred {
    public static void main(String[] args) {

        Thread f1 = new Fred();
        Fred f = new Fred();
        f.start();
        f1.start();


        Thread fred = new Thread(new FredRun());
        fred.start();

        Runnable r = () -> {
            System.out.println("Mira soy una gamba(lambda)");
        };
        Thread h = new Thread(r);
        h.start();

    }
}

class Fred extends Thread{

    @Override
    public void run() {
        System.out.println("Hola Carlos, Aqui tienes.");
    }
}

class FredRun implements Runnable{

    @Override
    public void run() {
        System.out.println("Fred corre, corre Fred");
    }
}
