import java.util.ArrayList;

public class References {
    private ArrayList<String> references= new ArrayList<>();

    //Constructor
    public References(int numMarcos)
    {
        for(int i=0; i<numMarcos;i++)
        {
            this.references.add("0");
        } 
    }
    public ArrayList<String> getReferences()
    {
        return this.references;
    }
    public synchronized void addReference(int index)
    {
        for(int i=0; i<this.references.size();i++)
        {
            if(i==index) //si es referenciado -> 1
            {
                references.set(i,"1");
            }
            else //si no es referenciado -> 0
            {
                references.set(i, "0");
            }
        }
    }
}
