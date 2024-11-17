import dao.GestorLibros;

import java.io.*;
import java.net.*;

public class HiloCliente implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket socketCliente;
    GestorLibros gestor = new GestorLibros();

    public HiloCliente(Socket cliente) {
        numCliente++;
        hilo = new Thread(this,"Cliente " + numCliente);
        this.socketCliente = cliente;
        hilo.start();
        // Inicializo la lista de libros
        gestor.inicializarLibrosBiblio();
    }

    @Override
    public void run() {
        try {
            // Se crean los flujos de datos tanto de entradas como de salidas para con el cliente
            InputStream entrada = socketCliente.getInputStream();
            OutputStream salida = socketCliente.getOutputStream();
            // Como daba problemas con la lectura de bytes, he optado por usar el BufferedReader y el BufferedWriter
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada)); // Usar BufferedReader para leer el código del producto
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(salida)); // Para escribir respuestas correctamente

             String mensaje;
             // Bucle para recibir mensajes del cliente y enviar respuestas
            while (true) {
                System.out.println("Esperando la petición del cliente..."); // Log para ver si el servidor está esperando
                mensaje = reader.readLine(); // Leer mensaje del cliente

                if (mensaje == null || mensaje.equals("6")) {
                    System.out.println("Conexión cerrada por el cliente.");
                    writer.write("Hasta pronto, gracias por establecer conexión");
                    writer.flush(); // Aseguramos que los datos se envíen
                    break;
                }

                System.out.println("Mensaje del cliente: " + mensaje);
                String accion = mensaje.split(",")[0];
                String respuesta = "";
                try {
                    switch (accion) {
                        case "INSERTAR":
                            respuesta = gestor.insertBook(mensaje.split(",")[1], mensaje.split(",")[2], Float.parseFloat(mensaje.split(",")[3]));
                            break;
                        case "ELIMINAR":
                            respuesta = gestor.deleteBookByTitle(mensaje.split(",")[1]);
                            break;
                        case "BUSCAR":
                            respuesta = gestor.getBook(mensaje.split(",")[1], mensaje.split(",")[2]);
                            break;
                        default:
                            respuesta = "Operación no conocida";
                    }

                } catch (Exception e) {
                    respuesta = "Error al procesar la solicitud: " + e.getMessage();
                }
                writer.write(respuesta + "\n"); // Responde al cliente
                writer.flush(); // Aseguramos que los datos se envíen
                System.out.println("Respuesta del servidor: " + respuesta);

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
