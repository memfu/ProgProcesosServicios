import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static int KilosNaranjas = 1250;
    static int KilosManzanas = 890;

    public static void main(String[] args) {


        System.out.println(" APLICACIÓN DE SERVIDOR ");
        System.out.println("----------------------------------");


        try {
            // Se crea un socket para servidor
            ServerSocket servidor = new ServerSocket(6665);

            System.out.println("Servidor creado y escuchando...");

            // Se crea un nuevo socket para el cliente en cuanto hay un cliente que solicite conexión
            Socket socketCliente = servidor.accept();
            System.out.println("Comunicación entrante");

            // Se crean los flujos de datos tanto de entradas como de salidas para con el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);

            // Aquí se guarda el mensaje que envíe el cliente
            String texto = entrada.readLine();

            switch (texto) {
                case "1":
                    salida.println("Kilos de naranjas disponibles: " + KilosNaranjas);
                    break;
                case "2":
                    salida.println("Kilos de manzanas disponibles: " + KilosManzanas);
                    break;
                case "3":
                    salida.println("Fin de la consulta. Gracias por usar el servicio.");
                    break;
                default:
                    salida.println("No se encuentra disponible esa opción en el menú.");
                    break;
            }

            // Se cierran primero los flujos de entrada y salida y luego las conexiones de los dos sockets
            entrada.close();
            salida.close();
            socketCliente.close();
            servidor.close();

            System.out.println("Comunicación cerrada");

        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }


    }
}
