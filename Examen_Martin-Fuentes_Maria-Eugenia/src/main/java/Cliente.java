import java.io.*;
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
            // Creamos primero el socket
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("127.0.0.1", 6665);

            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            System.out.println("Comunicación establecida con el servidor");

            // Creamos los flujos de entrada y de salida
            PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            // Muestra opciones al usuario
            System.out.println("Seleccione una opción:");
            System.out.println("1 - KILOS DE NARANJAS EN EL STOCK");
            System.out.println("2 - KILOS DE MANZANAS EN EL STOCK");

            String opcion = scanner.nextLine();

            // Envia la opción al servidor
            output.println(opcion);

            // Esperar y mostrar la respuesta del servidor
            String respuesta = input.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar conexión
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }

    }
}
