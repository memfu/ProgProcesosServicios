import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TicketServer {
    private static final int PORT = 12345;
    private static final int TIEMPO_LIMITE = 20; // Tiempo límite en segundos
    private static final Queue<ClientHandler> cola = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        System.out.println("Servidor iniciado. Esperando conexiones...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                synchronized (cola) {
                    cola.add(clientHandler);
                    if (cola.size() == 1) {
                        clientHandler.notifyTurn();
                    } else {
                        clientHandler.notifyQueued();
                    }
                }

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private boolean turno;
        private boolean running;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            this.turno = false;
            this.running = true;
        }

        public void notifyTurn() {
            turno = true;
            sendMessage("Es tu turno! Tienes " + TIEMPO_LIMITE + " segundos para comprar tus entradas.\n" +
                    "Escribe 'comprar' para adquirir una entrada o 'FIN' para salir.\n");
        }

        public void notifyQueued() {
            sendMessage("Servidor ocupado. Te hemos añadido a la cola. Espera tu turno.\n");
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                long tiempoInicio = System.currentTimeMillis();

                while (running) {
                    if (!turno) {
                        Thread.sleep(1000);
                        continue;
                    }

                    long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
                    if (tiempoTranscurrido >= TIEMPO_LIMITE) {
                        sendMessage("Se acabó tu tiempo. Desconectando...\n");
                        break;
                    }

                    if (in.ready()) {
                        String mensaje = in.readLine();
                        if (mensaje == null) {
                            System.out.println("Cliente desconectado inesperadamente.");
                            break;
                        }
                        if ("comprar".equalsIgnoreCase(mensaje.trim())) {
                            sendMessage("Compra realizada con éxito! Desconectando...\n");
                            break;
                        } else if ("FIN".equalsIgnoreCase(mensaje.trim())) {
                            sendMessage("Has solicitado finalizar. Desconectando...\n");
                            break;
                        } else {
                            sendMessage("Comando no reconocido. Escribe 'comprar' para adquirir una entrada o 'FIN' para salir.\n");
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error en la conexión con el cliente: " + e.getMessage());
            } finally {
                try {
                    if (!clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                synchronized (cola) {
                    cola.poll();
                    if (!cola.isEmpty()) {
                        cola.peek().notifyTurn();
                    }
                }

                System.out.println("Cliente desconectado: " + clientSocket.getInetAddress());
            }
        }

        private void sendMessage(String message) {
            if (clientSocket.isClosed()) return;
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                System.out.println("Error al enviar mensaje al cliente: " + e.getMessage());
            }
        }
    }
}
