import java.util.HashMap;
//Thread encargado de ejecutar el algoritmo de envejecimiento cada 1 milisegundo.

public class AgingAlgorithm extends Thread{
    private int marcosPagina; //DATO COMPARTIDO ¿?
    private HashMap<Integer, Integer> tablaPaginas; //DATO COMPARTIDO ¿?
    private boolean estaActivo;

    public AgingAlgorithm(int marcosPagina)
    {
        this.marcosPagina=marcosPagina;
        this.tablaPaginas= new HashMap<>();//tamaño¿?
        this.estaActivo=true;
    }
    public void terminarAlgoritmo()
    {
        this.estaActivo=false;
    }
    //Hacer algoritmo
    /*
     * Asignar a cada página un contador de 8 bits
     * Cada uno de los contadores se desplaza a la derecha 1 bit antes de agregar el bit R
     * El bit R se agrega al bit de más a la izquierda
     */
    public void run()
    {
        while(estaActivo)
        {
            //Llamar método algoritmo

            //simular pulso de reloj
            try {
                sleep(1); 
            } catch (Exception e) {}
            
        }
    }
}
