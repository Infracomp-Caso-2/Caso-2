
//Clase para representar la tabla de paginas

import java.util.ArrayList;

public class TablaPaginas {

    private ArrayList<Integer> paginas = new ArrayList<Integer>();

    public TablaPaginas(int totalPages) {
        for (int i = 0; i < totalPages; i++) {
            paginas.add(null);
        }
    }

    public ArrayList<Integer> getPaginas() {
        return paginas;
    }

    public void setPaginas(ArrayList<Integer> paginas) {
        this.paginas = paginas;
    }

    public Boolean estaReferenciaEnTP(Integer page) {
        return paginas.get(page) != null;
    }

    public void agregarPagina(Integer page, Integer posicion) {
        paginas.set(page, posicion);
    }
}
