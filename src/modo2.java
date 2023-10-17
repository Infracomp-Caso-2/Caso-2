import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class modo2 extends Thread {
    private AgingAlgorithm agingAlgorithm;
    private MarcoPagina marcoPagina;

    public static Integer contadorFallas = 0;
    private ArrayList<Integer> referencias = new ArrayList<Integer>();
    private String nombreArchivo;
    private TablaPaginas tablaPaginas;

    public modo2(String nombreArchivo, AgingAlgorithm agingAlgorithm, MarcoPagina marcoPagina) {
        this.nombreArchivo = nombreArchivo;
        this.agingAlgorithm = agingAlgorithm;
        this.marcoPagina = marcoPagina;
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

        if (estaEnTP) {
            // Si esta en la TP entonces se actualiza el bit de referencia
            // agingAlgorithm.actualizarBitReferencia(page);
        } else {
            // Si no esta en la TP entonces se mete en la TP y en el marco de paginas
            contadorFallas++;
            if (marcoPagina.tieneEspacio()) {
                Integer nuevaPosicion = marcoPagina.agregarPagina(page);
                tablaPaginas.agregarPagina(page, nuevaPosicion);
            } else {
            }
        }

    }

}
