public class Examinado implements Runnable {

    private Thread hilo;
    BufferExamenes buffer;

    public Thread getHilo() {
        return hilo;
    }

    public Examinado(String alumno, BufferExamenes generador) {
    /*
    Construye el hilo. El nombre será el nombre del alumno.
    Establece el valor de la propiedad buffer
    Inicia el hilo.
     */

    }

    @Override

    public synchronized void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            /*
            Simula aquí un examen de 10 preguntas cuyas respuestas se seleccionarán al azar
            entre A, B, C, D o – (sin contestar).
             */
        } else {
            System.out.println("Agotado tiempo de espera y " + "no hay más exámenes");
        }

    }

}

