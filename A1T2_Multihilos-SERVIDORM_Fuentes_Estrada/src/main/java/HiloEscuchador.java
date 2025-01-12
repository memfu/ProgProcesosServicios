import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    private boolean turno;
    public HiloEscuchador(Socket cliente) {
        numCliente++;
        hilo = new Thread(this, "Cliente"+numCliente);
        this.enchufeAlCliente = cliente;
        this.turno = false;
        hilo.start();
    }
    public void notifyTurn() {
        turno = true;
        sendMessage("Es tu turno! Tienes " + ServidorM.TIEMPO_LIMITE + " segundos para comprar tus entradas.\n");
    }

    public void notifyQueued() {
        sendMessage("Servidor ocupado. Te hemos añadido a la cola. Espera tu turno.\n");
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();
            long tiempoInicio = System.currentTimeMillis();
            String texto = "";
            while (!texto.trim().equals("FIN")) {
                if (!turno) {
                    Thread.sleep(1000);
                    continue;
                }

                long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
                if (tiempoTranscurrido >= ServidorM.TIEMPO_LIMITE) {
                    sendMessage("Se acabó tu tiempo. Desconectando...\n");
                    break;
                }

                byte[] mensaje = new byte[100];
                entrada.read(mensaje);
                texto = new String(mensaje).trim();  //  AÑADE .trim();
                if (texto.trim().equalsIgnoreCase("FIN")) {
                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                    System.out.println(hilo.getName()+" ha cerrado la comunicación");
                } else if (texto.trim().equalsIgnoreCase("comprar")) {
                    sendMessage("Compra realizada con éxito! Desconectando...\n");
                    break;
                } else {
                    sendMessage("Comando no reconocido. Escribe 'comprar' para adquirir una entrada.\n");
                }
                }
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error en la conexión con el cliente: " + e.getMessage());
        } finally {
            try {
                enchufeAlCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            synchronized (ServidorM.cola) {
                ServidorM.cola.poll();
                if (!ServidorM.cola.isEmpty()) {
                    ServidorM.cola.peek().notifyTurn();
                }
            }

            System.out.println("Cliente desconectado: " + enchufeAlCliente.getInetAddress());
        }

    }

    private void sendMessage(String message) {
        try (PrintWriter out = new PrintWriter(enchufeAlCliente.getOutputStream(), true)) {
            out.println(message);
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje al cliente: " + e.getMessage());
        }
    }
}
