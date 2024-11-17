import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket socketCliente;

    public HiloEscuchador(Socket cliente) {
        numCliente++;
        hilo = new Thread(this,"Cliente " + numCliente);
        this.socketCliente = cliente;
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());

        try {
            // Se crean los flujos de datos tanto de entradas como de salidas para con el cliente
            InputStream entrada = socketCliente.getInputStream();
            OutputStream salida = socketCliente.getOutputStream();

            String texto = ""; // Aquí se guarda el mensaje que envíe el cliente
            while (!texto.trim().equals("FIN")) {
                byte[] mensaje = new byte[1024]; // Crea una variable tipo buffer/"contenedor" de bytes
                entrada.read(mensaje); // La entrada lee lo que escribe el cliente y lo guarda en el array de bytes "mensaje"
                texto = new String(mensaje); // Convierte los bytes del mensaje en un String
                if (texto.trim().equals("FIN")) {
                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                } else {
                    System.out.println("Cliente dice: " + texto);
                    salida.write(("Tu mensaje tiene " + texto.trim().length() + " caracteres").getBytes());
                }
            }

            // Se cierran primero los flujos de entrada y salida y luego la conexión del socket del cliente
            entrada.close();
            salida.close();
            socketCliente.close();

        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}
