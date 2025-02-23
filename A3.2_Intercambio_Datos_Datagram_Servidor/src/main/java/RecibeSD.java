import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecibeSD {
    public static void main(String[] args) {
        // Formato para la fecha y la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            InetSocketAddress direccion = new InetSocketAddress("localhost", 5000);
            DatagramSocket ds = new DatagramSocket(direccion);
            System.out.println("Preparado para recibir...");

            // 1. Recibir el número total de paquetes
            byte[] bufferNumero = new byte[10];
            DatagramPacket paqueteInicial = new DatagramPacket(bufferNumero, bufferNumero.length);
            ds.receive(paqueteInicial);
            int totalPaquetes = Integer.parseInt(new String(paqueteInicial.getData()).trim());
            System.out.println("Número total de paquetes a recibir: " + totalPaquetes);

            // 2. Recibe los paquetes de datos
            String texto = "";
            for (int i = 1; i < totalPaquetes + 1; i++) {
                LocalDateTime now = LocalDateTime.now();
                // Guardo en un string con el formato de antes
                String timestamp = now.format(formatter);
                byte[] mensaje = new byte[500];
                DatagramPacket carta = new DatagramPacket(mensaje, 500);
                ds.receive(carta);
                texto = new String(mensaje).trim();
                System.out.println("Mensaje recibido: " + timestamp + "-PaqueteRecibidoNr" + i + " el siguiente mensaje: " + texto + "\n");
            }

            ds.close();
            System.out.println("Socket Datagram cerrado");
        } catch (SocketException | UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
