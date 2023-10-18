
//Clase para representar la tabla de paginas

import java.util.ArrayList;

public class TablaPaginas {

    private ArrayList<Integer> paginas = new ArrayList<Integer>();

    public TablaPaginas(int totalPages) {
        for (int i = 0; i < totalPages; i++) {
            paginas.add(-1);
        }
    }

    public ArrayList<Integer> getPaginas() {
        return paginas;
    }

    public void setPaginas(ArrayList<Integer> paginas) {
        this.paginas = paginas;
    }

    public void setPagina(int index, int pagina)
    {
        this.paginas.set(index, pagina);
    }

    public Integer getIndexPagina(Integer page) 
    {
        int index =-1;
        boolean referenciado = false;
        for(int i=0; i<this.paginas.size() && !referenciado;i++)
        {
            if(this.paginas.get(i)==page)
            {
                referenciado=true;
                index=i;
            }
        }
        return index;
    }

    public void agregarPagina(Integer page, Integer posicion) {
        paginas.set(page, posicion);
    }
    
    public int getIndexLibre()
    {
        int index=-1; //Ningún indice libre, TP llena
        boolean hayEspacio = false;
        for(int i=0; i<this.paginas.size() && !hayEspacio;i++)
        {
            int indexActual = this.paginas.get(i);
            if(indexActual==-1)
            {
                hayEspacio=true;
                index=i;
            }
        }
        return index;
    }
}
