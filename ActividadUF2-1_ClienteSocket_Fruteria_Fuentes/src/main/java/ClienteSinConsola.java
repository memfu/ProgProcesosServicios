import java.io.*;
import java.net.*;

public class ClienteSinConsola {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.1.107", 2000); // Cambia la IP si es necesario
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("PL"); // Envía el código del producto

            // Lee la respuesta del servidor
            String respuesta = reader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            writer.println("FIN"); // Envía "FIN" para cerrar la conexión

            // Lee la respuesta final
            respuesta = reader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cierra conexiones
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
