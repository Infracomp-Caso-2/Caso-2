import java.util.ArrayList;

//Thread encargado de ejecutar el algoritmo de envejecimiento cada 1 milisegundo.
public class AgingAlgorithm extends Thread{
    private int numReferences; 
    private ArrayList<String> references;
    private ArrayList<String> ages;
    private boolean estaActivo;

    public AgingAlgorithm(ArrayList<String> references, ArrayList<String> ages)
    {
        this.estaActivo=true;
        this.numReferences=0;
        this.references=references;
        this.ages=ages;
    }
    public void finish()
    {
        this.estaActivo=false;
    }
    public synchronized void addReferencia()
    {
        this.numReferences++;
    }
    
    public synchronized void aging() //Cada página lo llama
    {
        if(numReferences>0) //Si página fue referenciada, envejecer
        {
            for(int i=0;i<this.ages.size();i++) //Para c/u pags
            {
                String bitReferencia = references.get(i);
                String edad = this.ages.get(i);//0x30
                //Remover último caracter
                char[] caracteres = edad.toCharArray();
                char[] nuevoArreglo = new char[caracteres.length - 1];
    
                for (int j = 0; j < nuevoArreglo.length; j++) {
                    nuevoArreglo[j] = caracteres[j];
                }
                edad = new String(nuevoArreglo);//0x29
                //actualizar edad
                this.ages.set(i, bitReferencia+edad); // 1/0 + 0x29
            }
            numReferences=0;//Reset de referencias ya que se actualizaron todas las páginas
        }

    }

    public void run()
    {
        while(estaActivo) 
        {
            //Llamar método algoritmo
            aging();
            //Simular pulso de reloj
            try {
                sleep(1); 
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}