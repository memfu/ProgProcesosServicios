import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Servidor {
    public static void main(String[] args) {
        System.out.println(" APLICACIÓN DE SERVIDOR ");
        System.out.println("----------------------------------");

        try {
            ServerSocket servidor = new ServerSocket(); // Se crea un socket para servidor
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.107", 2000 ); // Se especifica la dirección IP y el puerto que se van a usar
            servidor.bind(direccion); // Se asocia el servidor con la dirección IP y el puerto

            System.out.println("Servidor creado y escuchando...");
            System.out.println("Dirección IP: " + direccion.getAddress());

            /*
            Añadimos una nueva variable para que el servidor pueda escoger si quiere seguir atendiendo peticiones o cerrar el socket
             */
            Scanner scanner = new Scanner(System.in);
            String continuar = "S";

            while (continuar.toUpperCase().equals("S")) {
                System.out.println("Servidor abierto y esperando solicitud de cliente.");

                Socket socketCliente = servidor.accept();
                System.out.println("Comunicación entrante");

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

                // Se cierran primero los flujos de entrada y salida y luego las conexión del socket del cliente
                entrada.close();
                salida.close();
                socketCliente.close();

                System.out.println("El servidor ha quedado libre. ¿Atendemos más peticiones (S/N?");
                continuar = scanner.nextLine();
            }

            // Cuando la respuesta es "N", cerramos el scanner y el servidor
            scanner.close();
            servidor.close();

            System.out.println("Comunicación cerrada");

            } catch (IOException e) {
                System.out.println("Error en la conexión: " + e.getMessage());
            }
    }

}
