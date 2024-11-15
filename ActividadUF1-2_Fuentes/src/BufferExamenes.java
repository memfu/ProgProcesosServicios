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

    // "synchronized" para que solo un hilo pueda acceder al método cada vez
    public synchronized void fabricarNuevoExamen(String codigo) {
    /*
    Aquí se fabrica un nuevo examen: un hilo de la clase ProductorExamenes se encarga de
    fabricarlo y pasarlo como argumento a este método
     */
        colaExamenes.add(codigo);
        notify();
        /*
        Se añade el código pasado como argumento a la cola y libera el hilo que está intentando
        consumir un nuevo examen mediante el "notify()"
         */
    }

    // "synchronized" para que solo un hilo pueda acceder al método cada vez
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
                System.out.println("Se ha producido un error: " + e.getMessage());
            }
        }

        if (esperas < 10) {
            // Si el nr de esperas es menor que 10, saca un examen y lo entrega como retorno de esta función
            String examen = colaExamenes.remove();
            return examen;
        } else {
            /*
             Si pasadas 10 repeticiones sigue la lista vacía, se deja de esperar a que se fabriquen
             más exámenes y se finaliza el método consumirExamen devolviendo un null
             */
            return null;
        }
    }
}