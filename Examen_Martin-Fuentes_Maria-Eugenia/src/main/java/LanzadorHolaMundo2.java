import java.io.File;
import java.io.IOException;

public class LanzadorHolaMundo2 {
        private static String classpath = System.getProperty("java.class.path");

        public void lanzarHolaMundo(int nrRepeticiones) {

            // Fija la ubicación de una carpeta previamente creada llamada "ficheros"
            File directorio = new File("src/main/java/ficheros");

            for (int i = nrRepeticiones; i > 0; i--) {
                try {
                    ProcessBuilder proceso = new ProcessBuilder("java", "-cp", classpath, "HolaMundo", String.valueOf(i));
                    // Redirige la salida a un fichero .txt ubicado dentro de la carpeta "ficheros"
                    proceso.redirectOutput(new File(directorio, "salida" + i + ".txt"));
                    // En caso de haber errores, redirige los errores a otro fichero
                    proceso.redirectError(new File(directorio, "error" + i + ".txt"));
                    // Inicia proceso y manda mensaje informativo por consola
                    proceso.start();
                    System.out.println("Iniciado el proceso para la repetición nr. " + i + ".");

                } catch (IOException e) {
                    System.out.println("Error al iniciar el proceso para la repetición nr. " + i + ": " + e.getMessage());
                }
            }

        }

}
