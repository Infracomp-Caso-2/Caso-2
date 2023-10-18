import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Modo2 extends Thread {
    private AgingAlgorithm agingAlgorithm;
    private MarcoPagina marcoPagina;

    public static Integer contadorFallas = 0;
    // No se si las referencias deberian ser estaticas por lo que se usa para el
    // aging y para las referencias :p
    private ArrayList<Integer> referencias;
    private String nombreArchivo;
    private TablaPaginas tablaPaginas;
    private Integer totalPages = 0;

    public Modo2(String nombreArchivo, MarcoPagina marcoPagina, ArrayList<Integer> referencias) {
        this.nombreArchivo = nombreArchivo;
        this.marcoPagina = marcoPagina;
        this.referencias = referencias;
    }

    public void run() {
        try {
            File file = new File(nombreArchivo);
            Scanner scanner = new Scanner(file);

            Integer contadorLineas = 0;

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (contadorLineas < 6) {
                    contadorLineas++;
                    continue;
                } else if (contadorLineas == 6) {
                    Integer totalPages = Integer.valueOf(line.split("=")[1]);
                    tablaPaginas = new TablaPaginas(totalPages);
                    agingAlgorithm = new AgingAlgorithm(referencias, totalPages);
                    contadorLineas++;
                } else {
                    Integer page = Integer.valueOf(line.split(",")[1]);
                    leerReferencias(page);
                    referencias.add(page);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
    }

    public void leerReferencias(Integer page) {
        // Preguntar si la referencia esta en la TP
        // Si esta...
        // Si no esta entonces la referencia se mete en la TP y en el marco de paginas
        Boolean estaEnTP = tablaPaginas.estaReferenciaEnTP(page);

        if (!estaEnTP) {
            // Si no esta en la TP entonces se mete en la TP y en el marco de paginas
            contadorFallas++;
            // Si el marco tiene espacio entonces se agrega la pagina :3
            if (marcoPagina.tieneEspacio()) {
                Integer nuevaPosicion = marcoPagina.agregarPagina(page);
                tablaPaginas.agregarPagina(page, nuevaPosicion);
            }
            // Si el marco no tiene espacio entonces se aplica el algoritmo de reemplazo
            else {

                Integer posicion = agingAlgorithm.posicionCambiar(marcoPagina);
                marcoPagina.cambiarPagina(posicion, page);
                tablaPaginas.agregarPagina(page, posicion);
            }
        }

    }

}
