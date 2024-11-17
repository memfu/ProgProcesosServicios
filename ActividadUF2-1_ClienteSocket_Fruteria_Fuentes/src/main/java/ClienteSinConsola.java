import java.io.*;
import java.net.*;

public class ClienteSinConsola {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.1.107", 2000); // Cambia la IP si es necesario
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Enviar código de producto
            writer.println("PL"); // Envía el código del producto

            // Leer la respuesta del servidor
            String respuesta = reader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Enviar "FIN" para cerrar la conexión
            writer.println("FIN");

            // Leer la respuesta final
            respuesta = reader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar conexiones
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
