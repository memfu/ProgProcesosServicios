import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) {
        System.out.println(" APLICACIÓN DE SERVIDOR ");
        System.out.println("----------------------------------");


        try {
            // Se crea un socket para servidor
            ServerSocket servidor = new ServerSocket();
            // Se especifica la dirección IP y el puerto que se van a usar
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.107", 2000 );
            // Se asocia el servidor con la dirección IP y el puerto
            servidor.bind(direccion);

            System.out.println("Servidor creado y escuchando...");
            System.out.println("Dirección IP: " + direccion.getAddress());

            // Se crea un nuevo socket para el cliente en cuanto hay un cliente que solicite conexión
            Socket socketCliente = servidor.accept();
            System.out.println("Comunicación entrante");

            // Se crean los flujos de datos tanto de entradas como de salidas para con el cliente
            InputStream entrada = socketCliente.getInputStream();
            OutputStream salida = socketCliente.getOutputStream();

            // Aquí se guarda el mensaje que envíe el cliente
            String texto = "";
            /*
             .trim() elimina espacios en blanco al principio y al fin del texto
             Esto se hace porque todo el tamaño que se le ha dado al array de bytes se va a ocupar:
             con el mensaje del cliente y el resto con espacios en blanco
             */
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
