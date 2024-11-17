import resources.HiloCliente;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        System.out.println(" APLICACIÓN DE SERVIDOR ");
        System.out.println("----------------------------------");

        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("192.168.1.107", 8080 ); // Se especifica la dirección IP y el puerto que se van a usar
            serverSocket.bind(direccion); // Se asocia el servidor con la dirección IP y el puerto 8080
            System.out.println("El servidor está escuchando en el puerto 8080.");

            System.out.println("Servidor creado y listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado con éxito.");
                new HiloCliente(clientSocket);
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

}
