package Simulacros.PSP.Subasta;

import java.util.HashMap;
import java.util.Map;

public class Subasta {
    private Map<Integer,Integer> puja;

    public Subasta() {
        puja = new HashMap<>();
    }

    public synchronized boolean realizarPuja(int precio, int lote){
        if(puja.isEmpty() || !puja.containsKey(lote)){
            puja.put(lote,precio);
            return true;
        }else if(puja.get(lote) < precio){
            puja.put(lote,precio);
            return true;
        }
        return false;
    }
}
