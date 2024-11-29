import java.util.LinkedList;
import java.util.Queue;

public class BufferColor {

        // colaColores es una cola de cadenas de texto a partir de un objeto LinkedList
        private Queue<String> colaColores;

        // Constructor de la clase BufferColor
        public BufferColor() {
            colaColores = new LinkedList<String>();
        }

        // "synchronized" para que solo un hilo pueda acceder al método cada vez
        public synchronized void ponerColor(String color) {
        /*
        Aquí se fabrica un nuevo color:
         */
            colaColores.add(color);
            notify();
        }

        // "synchronized" para que solo un hilo pueda acceder al método cada vez
        public synchronized String sacarColor() {
            // Este método se encarga de suministrar un color a cada hilo de tipo Examinador que lo solicite.
            int esperas = 0;
        /*
         Si la cola de colores está vacía, espera 1 segundo para dar tiempo a que se genere un nuevo color.
         Se limita el número de repeticiones del while con "esperas".
         */

            while (colaColores.isEmpty() && esperas <10) {
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
                // Si el nr de esperas es menor que 10, saca un color y lo entrega como retorno de esta función
                String color = colaColores.remove();
                return color;
            } else {
            /*
             Si pasadas 10 repeticiones sigue la lista vacía, se deja de esperar a que se fabriquen
             más colores y se finaliza el método devolviendo un null
             */
                return null;
            }
        }

}
