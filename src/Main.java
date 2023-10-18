import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    private static final int intSize = 4; //En Bytes
    private static int pageSize;
    private static int rowsMatrix1;
    private static int columnsMatrix1; //Filas matriz 2
    private static int columnsMatrix2;
    private static int pagesMatrix1;
    private static int pagesMatrix2;
    private static int pageFrames;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Generar referencias y archivo");
            System.out.println("2. Calcular el número de fallas de página");
            System.out.println("3. Salir de la aplicación");

            System.out.print("Seleccionar una opción: ");
            int option = 0;
            try {
                option = scanner.nextInt();} catch (Exception e) {
                System.out.println("Error: El número ingresado no es válido.");}
            switch (option) {
                case 1:
                    try {
                        System.out.print("Ingrese el tamaño de página en Bytes: ");
                        //Tamaño de página = TP
                        pageSize = scanner.nextInt();
                        System.out.print("Ingrese el número de filas de la matriz 1: ");
                        //Filas matriz 1 NF
                        rowsMatrix1 = scanner.nextInt();
                        System.out.print("Ingrese el número de columnas de la matriz 1 (este será también el número de filas de la matriz 2): ");
                        //Columnas matriz 1 NC1
                        columnsMatrix1 = scanner.nextInt();
                        System.out.print("Ingrese el número de columnas de la matriz 2: ");
                        //Columnas matriz 2 NC2
                        columnsMatrix2 = scanner.nextInt();
                        generateReferencesFile();
                        System.out.println("Creando archivo...\n");
                        System.out.println("Archivo de referencias creado.\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error al generar el archivo de referencias.\n");
                    }
                    break;
                case 2:
                    try{
                    System.out.println("Ingrese el número de marcos de página: ");
                    pageFrames = scanner.nextInt();
                    System.out.print("Ingrese el nombre del archivo de referencias (references.txt): ");
                    String file = scanner.next();
                    System.out.println("\nSimulación en proceso...");
                    //Poner el método para simular el comportamiento y calcular las fallas
                    } catch (Exception e){
                        System.out.println("Error, el valor ingresado no es válido.\n");
                        continue;
                    }
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error, seleccione una opción válida.");
                    break;
                }}
    }

    private static void generateReferencesFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("references.txt"))) {
            int matrix1Elements = rowsMatrix1 * columnsMatrix1;
            int matrix2Elements = columnsMatrix1 * columnsMatrix2;
            int numReferences = (columnsMatrix1 * 2 + 1) * rowsMatrix1 * columnsMatrix2;
            float x = (matrix1Elements * intSize) / pageSize;
            pagesMatrix1 = (int) Math.ceil(x);
            x = (matrix2Elements * intSize) / pageSize;
            pagesMatrix2 = (int) Math.ceil(x);
            x = (rowsMatrix1 * columnsMatrix2 * intSize) / pageSize;
            int pagesMatrix3 = (int) Math.ceil(x);
            int totalPages = pagesMatrix1 + pagesMatrix2 + pagesMatrix3;

            writer.write("TP=" + pageSize + "\n");
            writer.write("NF=" + rowsMatrix1 + "\n");
            writer.write("NC1=" + columnsMatrix1 + "\n");
            writer.write("NC2=" + columnsMatrix2 + "\n");
            writer.write("NR=" + numReferences + "\n");
            writer.write("NP=" + totalPages + "\n");
            generateReferences(writer);

        } catch (IOException e) {
            System.out.println("Error durante la escritura del archivo de referencias.\n");
        }
    }

    public static int[] virtualPage(int matrix, int row, int column) {
        int[] result = new int[2]; //[0] = número página virtual [1] = desplazamiento
        float x = 0;
        if (matrix == 1) {
            x = (row * columnsMatrix1 * intSize + intSize * column) / pageSize;
            result[0] = (int) Math.ceil(x);
            result[1] = row * columnsMatrix1 * intSize + intSize * column - result[0] * pageSize;} 
        else if (matrix == 2) {
            x = (row * columnsMatrix2 * intSize + intSize * column) / pageSize;
            result[0] = pagesMatrix1 + (int) Math.ceil(x);
            result[1] = pagesMatrix1 * pageSize + row * columnsMatrix2 * intSize + intSize * column - result[0] * pageSize;} 
        else {
            x = (row * columnsMatrix2 * intSize + intSize * column) / pageSize;
            result[0] = (int) Math.ceil(x);
            result[1] = row * columnsMatrix2 * intSize + intSize * column - result[0] * pageSize;
            result[0] += (pagesMatrix1 + pagesMatrix2);}
        return result;
    }

    public static void generateReferences(BufferedWriter writer) throws IOException {
        int[] result = null;
        for (int i = 0; i < rowsMatrix1; i++) {
            for (int j = 0; j < columnsMatrix2; j++) {
                for (int k = 0; k < columnsMatrix1; k++) {
                    result = virtualPage(1, i, k);
                    writer.write("[A-" + i + "-" + k + "]," + result[0] + "," + result[1] + "\n");
                    result = virtualPage(2, k, j);
                    writer.write("[B-" + k + "-" + j + "]," + result[0] + "," + result[1] + "\n");
                }
                result = virtualPage(3, i, j);
                writer.write("[C-" + i + "-" + j + "]," + result[0] + "," + result[1] + "\n");
            }}}
}
