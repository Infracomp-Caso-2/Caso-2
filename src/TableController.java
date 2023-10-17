
//Thread encargado de ir actualizando el estado de la tabla de páginas y los marcos de página en memoria real cada 2 milisegundos.
public class TableController extends Thread {
    private boolean estaActivo;
    
    public TableController()
    {
        this.estaActivo=true;
    }
    public void desactivar()
    {
        this.estaActivo=false;
    }
    public void run()
    {
        while(estaActivo)
        {
            //leer UNA línea del archivo
            //Buscar página en la TP
                //Si no esta, hacer fallo e intercambiar
                //Si si está, agregar la referencia para que el thread 2 maneje LRU
            //volver a dormir

            //Esperar para proximo ciclo
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
