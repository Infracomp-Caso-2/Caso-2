
//Thread encargado de ir actualizando el estado de la tabla de páginas y los marcos de página en memoria real cada 2 milisegundos.

import java.util.ArrayList;

public class MarcoPagina {

    private ArrayList<Integer> marcos = new ArrayList<Integer>();

    public MarcoPagina(int totalMarcos) {
        for (int i = 0; i < totalMarcos; i++) {
            marcos.add(null);
        }
    }

    public ArrayList<Integer> getMarcos() {
        return marcos;
    }

    public void setMarcos(ArrayList<Integer> marcos) {
        this.marcos = marcos;
    }

    public Boolean tieneEspacio() {
        return marcos.contains(null);
    }

    public Integer agregarPagina(Integer page) {
        marcos.set(marcos.indexOf(null), page);
        return marcos.indexOf(page);
    }

    public void cambiarPagina(Integer posicion, Integer page) {
        marcos.set(posicion, page);
    }

}
