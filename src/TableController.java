import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Thread encargado de ir actualizando el estado de la tabla de páginas y los marcos de página en memoria real cada 2 milisegundos.
public class TableController extends Thread {
    private Integer numMarcos;
    private Integer numFallos;
    private boolean estaActivo;
    private String file;
    private TablaPaginas tablePages;
    private AgesList ages;
    private References references;
    private AgingAlgorithm agingAlgorithm;

    //Constructor
    public TableController(String file, Integer numMarcos, TablaPaginas tablePages, AgesList ages, References references,AgingAlgorithm agingAlgorithm )
    {
        this.numMarcos=numMarcos;
        this.numFallos=0;
        this.estaActivo=true;
        this.file=file;
        this.tablePages=tablePages;
        this.ages=ages;
        this.references=references;
        this.agingAlgorithm=agingAlgorithm;
    }

    public void desactivar()
    {
        this.estaActivo=false;
    }

    public void run()
    {
        while(estaActivo)
        {
            try 
            {
                File file = new File(this.file);
                Scanner scanner = new Scanner(file);
                Integer contadorLineas = 0;

                //Leer el archivo linea por linea
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (contadorLineas < 6) {
                        contadorLineas++;
                        continue;
                    } 
                    else {
                        Integer actualPage = Integer.valueOf(line.split(",")[1]);
                        Integer indexActualPage = tablePages.getIndexPagina(actualPage);
                        if(indexActualPage!=-1) //SI está en la TP
                        {
                            references.addReference(indexActualPage); //Añadir un "1"
                            agingAlgorithm.addReferencia();
                        }
                        else //NO está en la TP
                        {
                            //Revisar si hay espacio en TP
                            Integer indexFree = tablePages.getIndexLibre();
                            if(indexFree!=-1) //SI hay espacio en TP
                            {
                                tablePages.setPagina(indexFree,actualPage);
                                references.addReference(indexFree);
                                agingAlgorithm.addReferencia();
                            }
                            else //NO hay espacio en TP
                            {
                                //Buscar índice de la página de mayor edad y reemplazar en TP
                                Integer indexOld = ages.getOlder();
                                tablePages.setPagina(indexOld, actualPage);
                                references.addReference(indexOld); 
                                agingAlgorithm.addReferencia();
                            }
                            numFallos++;
                        }
                        contadorLineas++;
                    }
                    //ContadorLineas++; en vez de 3 veces
                    //Pulso de reloj de 2 ms
                    try{
                        Thread.sleep(2);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
                scanner.close();
                System.out.println("\nNombre archivo: "+file+"\nNumero de marcos: "+numMarcos
                +"\nSe obtuvieron "+numFallos+" fallos de pagina." );
                desactivar(); //Acabar thread
                agingAlgorithm.finish();

                
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado");
                e.printStackTrace();
            }
        }
    }
}
