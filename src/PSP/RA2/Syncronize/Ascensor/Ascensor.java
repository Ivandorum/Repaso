package PSP.RA2.Syncronize.Ascensor;

public class Ascensor {

    private static final int PESO_MAX=320;
    private static final int MAX_PERS=4;

    private int peso;
    private int numPersona;


    public synchronized boolean canPass(Persona persona){
        if ((peso+persona.getPeso())<PESO_MAX && numPersona < MAX_PERS) {
            peso+=persona.getPeso();
            numPersona++;
            return true;
        }else{
            return false;
        }
    }

    public void salirAscensor(Persona persona){
        numPersona--;
        this.peso -= persona.getPeso();
    }
}
