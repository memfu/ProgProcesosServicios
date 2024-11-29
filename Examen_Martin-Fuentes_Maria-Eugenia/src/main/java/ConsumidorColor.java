public class ConsumidorColor implements Runnable {

    private Thread hilo;
    BufferColor buffer;

    public Thread getHilo() {
        return hilo;
    }

    // Constructor de ConsumidorColor
    public ConsumidorColor( BufferColor generador) {
        // Se construye el hilo
        hilo = new Thread(this);
        // Se establece el valor de la propiedad buffer
        this.buffer = generador;
        // Se inicia el hilo
        hilo.start();
    }

    // "synchronized" para que solo un hilo pueda acceder al método cada vez
    @Override
    public synchronized void run() {
        String color = this.buffer.sacarColor();

        if (color != null) {
            System.out.println(color + " - " + hilo.getName());
        } else {
            System.out.println("Agotado tiempo de espera y no hay más colores.");
        }

    }

}
