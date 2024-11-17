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
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("192.168.1.107", 2000);

            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            System.out.println("Comunicación establecida con el servidor");

            // Creamos los flujos de entrada y de salida
            BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter writer = new PrintWriter(cliente.getOutputStream(), true);

            String texto = "";
            while (!texto.equalsIgnoreCase("FIN")) {
                System.out.println("Escriba la abreviatura de la fruta que quiere consultar. " +
                        "Escriba FIN para terminar la aplicación: ");
                texto = scanner.nextLine(); // Recoge el mensaje por consola del cliente
                // Lo envía al servidor
                writer.println(texto); // println para enviar el texto con salto de línea

                // Respuesta del servidor
                System.out.println("Esperando respuesta del servidor...");
                String respuesta = reader.readLine();
                System.out.println("Servidor responde: " + respuesta);
            }

            // Se cierran primero los flujos de entrada y salida y luego la conexión
            reader.close();
            writer.close();
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
