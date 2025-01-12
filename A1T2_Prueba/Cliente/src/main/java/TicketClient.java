import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TicketClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PORT);

            // Hilo para escuchar mensajes del servidor
            Thread listenerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Servidor: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada por el servidor.");
                }
            });

            listenerThread.start();

            // Enviar mensajes al servidor
            while (true) {
                System.out.print("Tú: ");
                String clientMessage = scanner.nextLine();
                out.println(clientMessage);

                if ("FIN".equalsIgnoreCase(clientMessage)) {
                    System.out.println("Has salido del programa.");
                    break;
                }
            }

            listenerThread.join(); // Espera a que el hilo de escucha termine

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}