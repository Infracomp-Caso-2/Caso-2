
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
            // Lógica de actualización de la tabla de páginas y los marcos de página:
            // - Actualizar la tabla de páginas en función de las referencias generadas.
            // - Realizar seguimiento de las páginas en memoria real.
            // - Tomar decisiones de reemplazo de página si es necesario.

            //Esperar para proximo ciclo
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
