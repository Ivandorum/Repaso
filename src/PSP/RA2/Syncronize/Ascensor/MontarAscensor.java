package PSP.RA2.Syncronize.Ascensor;

import java.util.Random;

/**
 * Simular que una persona coge el ascensor y sube de piso
 * No pueden montar a la vez mas de 4 personas, y no pueden pesar entre todas mas de 320kg
 * - Persona: Son hilos. Tendran un identificador, peso (int)
 *      run(): Llama a un metodo para comprobar si puede subir al ascensor, sino pues espera.
 *      Una vez que pueda subir, se simulara ese tiempo subiendo.
 * - Ascensor: objeto compartido. Tiene peso(int), numPersona, metodo de comprobación de personas
 * El tiempo de llegado entre dos personas es aleatorio entre 5 y 10 segundos, y
 * el tiempo de subida o permanencia en el ascensor, aleatorio entre 20 y 40s
 */

public class MontarAscensor {

    private static final int MINLLEGA = 5;
    private static final int MAXLLEGA = 10;


    public static void main(String[] args) {
        final Ascensor ascensor = new Ascensor();
        Random r = new Random();
        int i = 1;
        while (true) {
            Thread pers1 = new Thread(new Persona("Ivan"+i, r.nextInt(40, 120), ascensor));
            pers1.start();
            i++;
            try {
                Thread.sleep(r.nextInt(MINLLEGA, MAXLLEGA + 1)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
