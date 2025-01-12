
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServidorM {

    public static final int TIEMPO_LIMITE = 120; // Tiempo límite en segundos
    private static final Queue<HiloEscuchador> cola = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
        System.out.println("----------------------------------");
        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("localhost",2001);  //ojo el puerto
            servidor.bind(direccion);
            System.out.println("Servidor listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());
            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                HiloEscuchador hiloEscuchador = new HiloEscuchador(enchufeAlCliente);
                synchronized (cola) {
                    cola.add(hiloEscuchador);
                    if (cola.size() == 1) {
                        hiloEscuchador.notifyTurn();
                    } else {
                        hiloEscuchador.notifyQueued();
                    }
                }

                new Thread(hiloEscuchador).start();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}