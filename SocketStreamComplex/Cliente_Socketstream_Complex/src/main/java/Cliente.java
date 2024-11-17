import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        System.out.println(" APLICACIÓN CLIENTE");
        System.out.println("-----------------------------------");
        Scanner scanner = new Scanner(System.in);

        try {
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("192.168.1.107", 2000);

            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            System.out.println("Comunicación establecida con el servidor");

            // Creamos los flujos de entrada y de salida
            InputStream entrada = cliente.getInputStream();
            OutputStream salida = cliente.getOutputStream();

            String texto = "";
            while (!texto.equals("FIN")) {
                System.out.println("Escribe mensaje (FIN para terminar): ");
                texto = scanner.nextLine(); // Recoge el mensaje por consola del cliente
                // Lo envía al servidor. Como tiene que ser enviado como array de bytes, usamos el método .getBytes()
                salida.write(texto.getBytes());

                // Parte para la respuesta del servidor
                byte[] mensaje = new byte[1024]; // Variable tipo buffer para establecer cuantos bytes se recogen
                System.out.println("Esperando respuesta del servidor...");
                entrada.read(mensaje); // La entrada lee y lo guarda en el array de bytes "mensaje"
                System.out.println("Servidor responde: " + new String(mensaje));
            }

            // Se cierran primero los flujos de entrada y salida y luego la conexión
            entrada.close();
            salida.close();
            cliente.close();

            System.out.println("Comunicación cerrada");

        } catch (UnknownHostException e) {
            System.out.println("No se puede establecer comunicación con el servidor");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e.getMessage());
        }
    }
}
