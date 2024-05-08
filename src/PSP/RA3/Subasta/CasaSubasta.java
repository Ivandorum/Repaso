package PSP.RA3.Subasta;

import java.util.HashMap;
import java.util.Map;

public class CasaSubasta {
    private Map<Integer,Map<String, Integer>> lote;

    public CasaSubasta(){
        this.lote = new HashMap<>();
    }

    public synchronized boolean aceptarSubasta(int idLote, int precio, String idCliente){
        Map <String, Integer> info = lote.get(idLote);
        Integer precioActual = info.get(idCliente);
        if( precioActual != null && precioActual >= precio){
            return false;
        }else{
            info.put(idCliente,precio);
            return  true;
        }
    }
}
