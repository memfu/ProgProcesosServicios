import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
public class EnviaSD {
    public static void main(String[] args) {
        // Formato para la fecha y la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Scanner lector = new Scanner(System.in);
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress destino = InetAddress.getByName("localhost");
            String mensaje = "";
            int nrPaquetes = 100;

            // 1. Envía primero el número total de paquetes al servidor para que lo ponga en su for loop
            String numeroPaquetes = String.valueOf(nrPaquetes);
            DatagramPacket paqueteInicial = new DatagramPacket(numeroPaquetes.getBytes(), numeroPaquetes.length(), destino, 5000);
            ds.send(paqueteInicial);
            System.out.println("Número total de paquetes enviado: " + nrPaquetes);

            // 2. Envía los paquetes
            for (int i = 1; i < nrPaquetes + 1; i++) {
                LocalDateTime now = LocalDateTime.now();
                // Guardo en un string con el formato de antes
                String timestamp = now.format(formatter);
                mensaje = timestamp + "-PaqueteEnviadoNr" + i;
                int lon = mensaje.length();
                DatagramPacket carta = new DatagramPacket(mensaje.getBytes(), lon, destino, 5000);
                ds.send(carta);
                System.out.println("Enviado: " + mensaje + "\n");
            }

            System.out.println("Último paquete enviado.");

            ds.close();
            System.out.println("Socket Datagram cerrado");
        } catch (SocketException | UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}