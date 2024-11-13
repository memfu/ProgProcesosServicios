import java.util.LinkedList;
import java.util.Queue;

/*
Para cada alumno que va a examinarse se debe imprimir un examen, que tendrá un código diferenciado.
La clase BufferExamenes mantiene una cola (objeto LinkedList) de códigos de examen.
Para cada alumno se extrae un examen de la cola.
 */

public class BufferExamenes {
    // colaExamenes es una cola de cadenas de texto a partir de un objeto LinkedList
    private Queue<String> colaExamenes;

    // Constructor de la clase BufferExamenes
    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {
    /*
    Aquí se fabrica un nuevo examen.
    Un hilo de la clase ProductorExamenes se encargará de fabricarlo y pasarlo como argumento a este método.
    Añade el código pasado como argumento a la cola y libera el hilo que está intentando consumir un nuevo examen.
     */
        colaExamenes.add(codigo);
        notify();

    }

    public synchronized String consumirExamen() {
        // Este método se encarga de suministrar un examen a cada hilo de tipo Examinador que lo solicite.
        int esperas = 0;
        /*
         Si la cola de exámenes está vacía, espera 1 segundo para dar tiempo a que se genere un nuevo examen.
         En caso de que el número de ProductorExamenes y Examinado no fuera parejo, podría entrar en un bucle infinito.
         Por eso se limita el número de repeticiones del while con "esperas".
         */

        while (colaExamenes.isEmpty() && esperas <10) {
            esperas++;
            try {
                /*
                 Con el método wait, dejamos bloqueado el hilo hasta que es liberado por otro hilo
                 con el método "notify()" o hasta que ha pasado el tiempo que se ha especificado (1 segundo)
                 */
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("Se ha producido un error: " + e.getMessage());            }
        }

        // Si la cola de exámenes ya NO está vacía
        if (!colaExamenes.isEmpty()) {
            // Saca un examen y lo entrega como retorno de esta función
            String examen = colaExamenes.remove();
            return examen;
        } else {
            return null;
        }

    }

}