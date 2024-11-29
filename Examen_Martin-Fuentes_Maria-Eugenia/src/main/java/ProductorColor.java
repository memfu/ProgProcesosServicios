import java.util.Random;

public class ProductorColor implements Runnable {

    private BufferColor buffer;
    private static int numeroColor = 0;
    private Thread hilo;
    private Random random;

    public ProductorColor() {
        this.random = new Random();
    }

    // Constructor de ProductorColor
    public ProductorColor(BufferColor buffer) {
        // Se incrementa el contador de exámenes (variable numeroExamen).
        numeroColor++;
        // Se construye el hilo y se le pone el nr correspondiente del color
        hilo = new Thread(this, String.valueOf(numeroColor));
        // Se establece el valor de la propiedad buffer
        this.buffer = buffer;
        // Se inicia el hilo
        hilo.start();
    }

    // Método para obtener el nombre del color
    private String getColorName(int color) {
        switch (color) {
            case 1:
                return "Rojo";
            case 2:
                return "Verde";
            case 3:
                return "Amarillo";
            case 4:
                return "Azul";
            default:
                return "-";
        }
    }

    @Override
    public void run() {
        // Genera un número aleatorio entre 1 y 4
        int color = random.nextInt(4) + 1;
        String colorRandom = getColorName(color);

        // Se añade el nuevo código al buffer de colores.
        buffer.ponerColor(colorRandom);

        // Muestra un mensaje en consola informando sobre el color que se acaba de producir.
        System.out.println("Se ha generado con éxito el color " + colorRandom + ".");
    }
}
