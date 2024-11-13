import java.io.File;
import java.io.IOException;

public class ProcesoLanzadorDefault {

    // Al ejecutar un programa, Java necesita saber dónde están las clases que la aplicación usa. Por eso necesito definir la classpath.
    static String classpath = System.getProperty("java.class.path");

    public void lanzarTriangulo () {
        File directorio = new File("src/ficheros");
         /*
        "java": Es el comando para ejecutar la Máquina Virtual de Java (JVM), para iniciar un proceso que ejecute un programa Java.
        "-cp": Es un argumento de la JVM que significa "classpath" y permite especificar una lista de rutas de archivos
        y directorios donde la JVM debe buscar clases y bibliotecas necesarias para ejecutar el programa.
         */
        ProcessBuilder triangulo5 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "5");
        ProcessBuilder triangulo7 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "7");
        ProcessBuilder triangulo9 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "9");

        // Redirijo las salidas a un fichero .txt
        triangulo5.redirectOutput(new File(directorio,"triangulo5.txt"));
        triangulo7.redirectOutput(new File(directorio,"triangulo7.txt"));
        triangulo9.redirectOutput(new File(directorio,"triangulo9.txt"));

        // En caso de haber errores, redirijo los errores al mismo fichero
        triangulo5.redirectErrorStream(true);
        triangulo7.redirectErrorStream(true);
        triangulo9.redirectErrorStream(true);

        try {
            // Inicio los procesos con mensaje por consola
            triangulo5.start();
            System.out.println("Iniciado el proceso para el triángulo con 5 filas.");

            triangulo7.start();
            System.out.println("Iniciado el proceso para el triángulo con 7 filas.");

            triangulo9.start();
            System.out.println("Iniciado el proceso para el triángulo con 9 filas.");

        } catch (IOException e) {
            System.out.println("Error al iniciar el proceso: " + e.getMessage());            }
        }
    }
