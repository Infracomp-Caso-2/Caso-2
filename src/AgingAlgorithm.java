import java.util.HashMap;
//Thread encargado de ejecutar el algoritmo de envejecimiento cada 1 milisegundo.

public class AgingAlgorithm {
    private int marcosPagina; //DATO COMPARTIDO ¿?
    private HashMap<Integer, Integer> tablaPaginas; //DATO COMPARTIDO ¿?

    public AgingAlgorithm(int marcosPagina)
    {
        this.marcosPagina=marcosPagina;
        this.tablaPaginas= new HashMap<>();//tamaño¿?
    }

    //Hacer algoritmo
    /*
     * Asignar a cada página un contador
     * Cada uno de los contadores se desplaza a la derecha 1 bit antes de agregar el bit R
     * El bit R se agrega al bit de más a la izquierda
     */
}
