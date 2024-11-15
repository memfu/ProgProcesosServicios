import java.util.Random;

public class Examinado implements Runnable {

    private Thread hilo;
    BufferExamenes buffer;

    public Thread getHilo() {
        return hilo;
    }

    // Constructor de Examninado
    public Examinado(String alumno, BufferExamenes generador) {
        // Se construye el hilo con el nombre del alumno
        hilo = new Thread(this, alumno);
        // Se establece el valor de la propiedad buffer
        this.buffer = generador;
        // Se inicia el hilo
        hilo.start();
    }

    // "synchronized" para que solo un hilo pueda acceder al método cada vez
    @Override
    public synchronized void run() {
        /*
        Simula aquí un examen de 10 preguntas cuyas respuestas se seleccionarán al azar
        entre A, B, C, D o – (sin contestar).
         */
        String codigoExamen = this.buffer.consumirExamen();

        if (codigoExamen != null) {
            // Se definen los valores para las posibles respuestas
            String[] opciones = {"A", "B", "C", "D", "-"};
            Random random = new Random();

            for (int i = 1; i <= 10; i++) {
                // Se elige un índice aleatorio en el rango de 0 a opciones.length - 1
                int indiceAleatorio = random.nextInt(opciones.length);
                String valorAleatorio = opciones[indiceAleatorio];

                // Estructura: "Código de examen - Nombre - Pregunta 1: C"
                System.out.println(codigoExamen + " - " + hilo.getName() + " - Pregunta " + i + ": " + valorAleatorio);
            }
        } else {
            System.out.println("Agotado tiempo de espera y no hay más exámenes.");
        }

    }

}

