import java.util.ArrayList;
//import java.util.HashMap;

//Thread encargado de ejecutar el algoritmo de envejecimiento cada 1 milisegundo.
/* 
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
    public synchronized void aging() //cada página lo llama
    {
        if(numReferences>0) //Si página fue referenciada, envejecer
        {
            for(int i=0;i<this.aging.size();i++) //para c/u pags
            {
                String valorReferencia = references.get(i);
                String edad = this.aging.get(i);
                //Mover bit 

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
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AgingAlgorithm extends Thread {

    // Representa la estructura de envejecimiento para el algoritmo.
    private byte[] agingStructure;
    private ArrayList<Integer> referencias;

    public AgingAlgorithm(ArrayList<Integer> referencias, Integer sizeTP) {
        this.referencias = referencias;
        this.agingStructure = new byte[sizeTP];
    }

    public void reference() {
        // Convertir la lista de referencia a un conjunto para acelerar las
        // verificaciones de pertenencia.
        Set<Integer> referenceSet = new HashSet<>(referencias);

        for (int j = 0; j < agingStructure.length; j++) {
            byte i = agingStructure[j];
            // Si la página actual fue referenciada, ajusta su contador en la estructura de
            // envejecimiento.
            if (referenceSet.contains(j)) {
                i = (byte) (i >>> 1);
                i = (byte) (i | 0x80);
            } else {
                i = (byte) (i >>> 1);
            }
            agingStructure[j] = i;
            // Imprime el contador actual en formato binario.
            System.out.println(j + " " + String.format("%8s", Integer.toBinaryString(i & 0xFF)).replaceAll(" ", "0"));
        }
    }

    public Integer posicionCambiar(MarcoPagina marcoPagina) {
        byte min = -1; // Inicializa el valor mínimo con el byte más pequeño.
        int pos = 0; // Posición de la página que potencialmente será reemplazada.
        ArrayList<Integer> marcos = marcoPagina.getMarcos(); // Obtiene el arreglo de páginas en RAM.
        Boolean not_yet = false; // Bandera para verificar si ya hemos asignado un valor inicial a 'min'.

        for (int j = 0; j < marcos.size(); j++) {
            int posVP = marcos.get(j); // Posición virtual de la página en la estructura de envejecimiento.
            byte bits = agingStructure[posVP]; // Valor actual de envejecimiento de la página.

            // Si una página no ha sido referenciada, es la mejor candidata para ser
            // reemplazada.
            if (bits == 0) {
                return posVP;
            }

            if (!not_yet) {
                min = bits;
                not_yet = true;
                pos = posVP;
            } else {
                // Lógica para determinar si la página actual es una mejor candidata para
                // reemplazo.
                if ((min & 0xFF) < 0x80) { // Si el bit de signo de 'min' está apagado.
                    if ((bits & 0xFF) >= 0x80) { // Si el bit de signo de 'bits' está encendido.
                        min = bits;
                        pos = posVP;
                    } else {
                        min = (byte) Math.min(min & 0xFF, bits & 0xFF); // Determina el valor mínimo.
                        if (min == bits) {
                            pos = posVP;
                        }
                    }
                } else { // Si el bit de signo de 'min' está encendido.
                    if ((bits & 0xFF) >= 0x80) {
                        min = (byte) Math.min(min & 0xFF, bits & 0xFF);
                        if (min == bits) {
                            pos = posVP;
                        }
                    }
                    // Si bits es 0 y min no es 0, actualiza min y pos.
                    if (bits == 0 && min != 0) {
                        min = bits;
                        pos = posVP;
                    }
                }
            }
        }

        return pos;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                reference();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
