import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProcesoLanzadorUser {
    private static Scanner scannerNum = new Scanner(System.in);
    private static String classpath = System.getProperty("java.class.path");

    public void lanzarTriangulo() {
        int nrTriangle = 0;
        // Fija la ubicación de una carpeta previamente creada llamada "ficheros"
        File directorio = new File("src/ficheros");

        // Pregunta por consola al usuario cuántos triángulos quiere crear
        System.out.println("Introduzca el número de triángulos que quiere crear.");

        // Comprobación de entrada válida
        while (true) {
            try {
                // Recoge por consola el número de triángulos
                nrTriangle = scannerNum.nextInt();

                // Comprueba si el número ingresado es positivo
                if (nrTriangle <= 0) {
                    System.out.println("Por favor, ingrese un número entero positivo.");
                    continue; // Volver a pedir la entrada
                }
                // Sale del bucle si la entrada es válida
                break;
            // Recoge el error en caso de que la respuesta no sea un número
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero.");
                scannerNum.next(); // Limpia el buffer del escáner
            }
        }
        for (int i = nrTriangle; i > 0; i--) {
            try {
                ProcessBuilder proceso = new ProcessBuilder("java", "-cp", classpath, "Triangulo", String.valueOf(i));
                // Redirige la salida a un fichero .txt ubicado dentro de la carpeta "ficheros"
                proceso.redirectOutput(new File(directorio, "triangulo" + i + ".txt"));
                // En caso de haber errores, redirige los errores al mismo fichero
                proceso.redirectErrorStream(true);
                // Inicia proceso
                proceso.start();
                System.out.println("Iniciado el proceso para el triángulo con " + i + " filas.");

            } catch (IOException e) {
                System.out.println("Error al iniciar el proceso del triángulo de " + i + " filas: " + e.getMessage());
            }
        }
        // Cerrado del scanner al final del método
        scannerNum.close();
    }
}

