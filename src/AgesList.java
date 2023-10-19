import java.util.ArrayList;

public class AgesList {
    private ArrayList<String> ages= new ArrayList<>();

    public AgesList(int numMarcos)
    {
        for(int i=0; i<numMarcos;i++)
        {
            this.ages.add("0000000000000000000000000000000"); //30 bits
        } 
    }
    public ArrayList<String> getAges()
    {
        return ages;
    }
    public synchronized Integer getOlder()
	{
		Integer  older = 0;
		Integer min = Integer.MAX_VALUE;
		for(Integer i = 0; i<this.ages.size(); i++)
		{
			//Sacamos el valor actual y lo pasamos a decimal
			String stringAge= this.ages.get(i);
			Integer numAge = Integer.parseInt(stringAge,2);
			//Si es el menor, actualizamos
			if(numAge < min)
			{
				older = i;
				min = numAge;
			}
		}
		return older; //Índice del más viejo
	}
}
