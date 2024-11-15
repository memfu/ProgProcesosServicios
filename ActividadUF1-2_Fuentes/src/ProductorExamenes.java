import java.time.LocalDateTime;

/*
La clase ProductorExamenes se encargará de generar exámenes, asignándole a cada uno un código q
ue estará formado por la letra E seguida del número de examen, un guión y el año,
por ejemplo: “E2-2024 (segundo examen del año 2024).” El número de examen se establece
a partir de la variable estática numeroExamen.
 */

public class ProductorExamenes implements Runnable {

    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;

    // Constructor de ProductorExamenes
    public ProductorExamenes(BufferExamenes buffer) {
        // Se incrementa el contador de exámenes (variable numeroExamen).
        numeroExamen++;
        // Se construye el hilo y se le pone el nombre de "E" + nr correspondiente de examen
        hilo = new Thread(this, "E" + numeroExamen);
        // Se establece el valor de la propiedad buffer
        this.buffer = buffer;
        // Se inicia el hilo
        hilo.start();
    }

    @Override
    public void run() {
        // Variable de tipo integer aa para obtener el año actual
        int aa = LocalDateTime.now().getYear();
        // Generación del código de examen. Se añade el año aquí porque nos interesa cuando se ejecuta el hilo, no cuando se crea.
        String codigo = this.hilo.getName() + "-" +aa;

        // Se añade el nuevo código al buffer de exámenes.
        buffer.fabricarNuevoExamen(codigo);

        // Muestra un mensaje en consola informando sobre el código del examen que se acaba de producir.
        System.out.println("Se ha generado con éxito el código " + codigo + ".");
    }

}

