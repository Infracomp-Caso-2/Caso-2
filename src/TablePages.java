import java.util.ArrayList;

public class TablePages {
    private ArrayList<Integer> TP;
    //Constructor
    public TablePages()
    {
        this.TP = new ArrayList<>();
    }

    //Métodos 
    public void addPage(int numPagina)
    {
        this.TP.add(numPagina);
    }
    public void setPage(int index, int numPagina)
    {
        this.TP.set(index,numPagina);
    }
    public int getIndex(int numPagina) //Dado una página buscar el marco de página asociado
    {
        return 0;
    }
}
