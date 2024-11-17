import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    public static void main(String[] args) {
        System.out.println(" APLICACIÓN DE SERVIDOR ");
        System.out.println("----------------------------------");

        try {
            ServerSocket servidor = new ServerSocket(); // Se crea un socket para servidor
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.107", 2000 ); // Se especifica la dirección IP y el puerto que se van a usar
            servidor.bind(direccion); // Se asocia el servidor con la dirección IP y el puerto

            System.out.println("Servidor creado y listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());

            while (true) {
                Socket socketCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloEscuchador(socketCliente);
            }

        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }

}
