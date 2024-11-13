import java.util.Random;

public class Examinado implements Runnable {

    private Thread hilo;
    BufferExamenes buffer;

    public Thread getHilo() {
        return hilo;
    }

    // Constructor de Examninado
    public Examinado(String alumno, BufferExamenes generador) {
        // Se establece el valor de la propiedad buffer
        this.buffer = generador;
        // Se construye el hilo con el nombre del alumno
        hilo = new Thread(this, alumno);
        // Se inicia el hilo
        hilo.start();
    }

    @Override
    public synchronized void run() {
        /*
        Simula aquí un examen de 10 preguntas cuyas respuestas se seleccionarán al azar
        entre A, B, C, D o – (sin contestar).
         */
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            // Se definen los valores posibles
            String[] opciones = {"A", "B", "C", "D", "-"};
            Random random = new Random();

            // Se elige un índice aleatorio en el rango de 0 a opciones.length - 1
            int indiceAleatorio = random.nextInt(opciones.length);
            String valorAleatorio = opciones[indiceAleatorio];

        } else {
            System.out.println("Agotado tiempo de espera y " + "no hay más exámenes");
        }

    }

}

