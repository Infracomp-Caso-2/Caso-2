import java.util.ArrayList;
//import java.util.HashMap;

//Thread encargado de ejecutar el algoritmo de envejecimiento cada 1 milisegundo.

public class AgingAlgorithm extends Thread{
    private int marcosPagina; 
    private int numReferences; //llamados a la página 
    //private HashMap<Integer, Integer> tablaPaginas; 
    private ArrayList<String> references;
    private ArrayList<String> aging;
    private boolean estaActivo;

    public AgingAlgorithm(int marcosPagina)
    {
        this.marcosPagina=marcosPagina;
        this.numReferences=0;
        //this.tablaPaginas= new HashMap<>();
        this.references=new ArrayList<String>();
        this.aging=new ArrayList<String>();
        this.estaActivo=true;
    }
    public void createAges()
    {
        for( int i=0; i<this.marcosPagina; i++)
        {
            aging.add("000000000000000000000000000000"); //30 bits - recomendado por Sandra
        }
    }
    public void createReferences()
    {
        for( int i=0; i<this.marcosPagina; i++)
        {
            references.add("0");
        }
    }
    // ------------------ SE PUEDE HACER EN 3 LÍNEAS
    public void addReference(int index)//agregar una referencia a un marco
    {
        for (int i=0; i<this.references.size();i++)
        {
            if(i==index)
            {
                this.references.set(i, "1");
            }
            else
            {
                this.references.set(i, "0");
            }
        }
        numReferences++;
    }
    public int getOlder() //Buscar el indice más viejo
    {
        int oldIndex=0;
        int min = Integer.MAX_VALUE; //cualquier valor
        for (int i=0;i<this.aging.size();i++)
        {
            String age = this.aging.get(i);
            int edad = Integer.parseInt(age);
            if(edad<min)
            {
                oldIndex=i;
                min = edad;
            }
        }
        return oldIndex;
    }
    
    public void finish()
    {
        this.estaActivo=false;
    }
    //Hacer algoritmo
    /*
     * Asignar a cada página un contador de 30 bits
     * Cada uno de los contadores se desplaza a la derecha 1 bit antes de agregar el bit R
     * El bit R se agrega al bit de más a la izquierda
     */
    public synchronized void aging() //cada página lo llama
    {
        if(numReferences>0) //Si página fue referenciada, envejecer
        {
            for(int i=0;i<this.aging.size();i++) //para c/u pags
            {
                String valorReferencia = references.get(i);
                String edad = this.aging.get(i);
                //Mover bit 
                /* NO ESTÁ HECHO AÚN */

                references.set(i, edad+valorReferencia); //(i,edad)o (i,valorReferencia+1) 
            }
            numReferences=0;
        }

    }

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
