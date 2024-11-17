import dao.ProductoDao;

import java.io.*;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket socketCliente;
    ProductoDao frutaDao = new ProductoDao();

    public HiloEscuchador(Socket cliente) {
        numCliente++;
        hilo = new Thread(this,"Cliente " + numCliente);
        this.socketCliente = cliente;
        hilo.start();
    }

    @Override
    public void run() {
        // Inicializa la lista de Frutas
        frutaDao.listarFrutas();
        System.out.println("Estableciendo comunicación con " + hilo.getName());

        try {
            // Se crean los flujos de datos tanto de entradas como de salidas para con el cliente
            InputStream entrada = socketCliente.getInputStream();
            OutputStream salida = socketCliente.getOutputStream();
            // Como daba problemas con la lectura de bytes, he optado por usar el BufferedReader y el BufferedWriter
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada)); // Usar BufferedReader para leer el código del producto
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(salida)); // Para escribir respuestas correctamente


            String codigoProducto; // Aquí se guarda el código que envíe el cliente

            while (true) {
                System.out.println("Esperando el código de producto del cliente..."); // Log para ver si el servidor está esperando
                codigoProducto = reader.readLine(); // Lee la línea enviada por el cliente

                if (codigoProducto == null || codigoProducto.trim().equalsIgnoreCase("FIN")) {
                    writer.write("Hasta pronto, gracias por establecer conexión");
                    writer.flush(); // Aseguro que los datos se envíen
                    break;
                }
                System.out.println("Cliente busca la fruta con el código: " + codigoProducto);

                // Busca el producto y lo guarda en un String "respuesta"
                String respuesta = frutaDao.getFruit(codigoProducto.trim());
                System.out.println("Respuesta del servidor: " + respuesta);
                writer.write(respuesta + "\n"); // Responde al cliente con la respuesta
                writer.flush(); // Aseguro que los datos se envíen
            }

            // Se cierran los flujos de entrada, salida y la conexión del socket del cliente
            reader.close();
            writer.close();
            socketCliente.close();
            System.out.println("Conexión cerrada con el cliente");

        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}
