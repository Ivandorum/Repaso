package PSP.RA2.Syncronize;

public class Sumador {
    private int num = 0;
    private int i = 0;
    public void sumar100(){
        for (; i < 100; i++) {
            num += i;
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}


class HiloSumador implements Runnable{
    Sumador sumador;

    public HiloSumador(Sumador sumador) {
        this.sumador = sumador;
    }

    @Override
    public void run() {
        synchronized (sumador){
            sumador.sumar100();
        }
    }
}

class MainSumador{
    public static void main(String[] args) {

        Sumador sum = new Sumador();

        Thread hiloSum1 = new Thread(new HiloSumador(sum));
        hiloSum1.setName("Sum1");
        hiloSum1.start();

        Thread hiloSum2 = new Thread(new HiloSumador(sum));
        hiloSum2.setName("Sum2");
        hiloSum2.start();
    }
}

