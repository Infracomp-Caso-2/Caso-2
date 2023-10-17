import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class modo2 {

    private int marcos = 0;
    private String archivo;
    private Map<String, ArrayList<Integer>> referencias = new HashMap<String, ArrayList<Integer>>();

    public modo2(int marcos, String archivo) {
        this.marcos = marcos;
        this.archivo = archivo;
    }

    public void cargarListaReferencias(marcos, archivo) {
        // Cargar referencias

        for (int i = 0; i < marcos; i++) {
            // Cargar marcos
            ArrayList<Integer> marco = new ArrayList<Integer>();
            marco.add(0);
            marco.add(0);
            marco.add(0);
            marco.add(0);
            marco.add(0);
            marco.add(0);
            marco.add(0);
            marco.add(0);
            referencias.put("Marco " + i, marco);
        }
    
    }
}
