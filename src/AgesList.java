import java.util.ArrayList;

public class AgesList {
    private ArrayList<String> ages= new ArrayList<>();

    public AgesList(int numMarcos)
    {
        for(int i=0; i<numMarcos;i++)
        {
            this.ages.add("0000000000000000000000000000000");
        } 
    }
    public ArrayList<String> getAges()
    {
        return ages;
    }
    public synchronized int getOlder()
	{
		int  older = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<this.ages.size(); i++)
		{
			//Sacamos el valor actual y lo pasamos a decimal
			String stringAge= this.ages.get(i);
			int numAge = Integer.parseInt(stringAge);
			//Si es el menor, actualizamos
			if(numAge < min)
			{
				older = i;
				min = numAge;
			}
		}
		return older; //indice del más viejo
	}
}
