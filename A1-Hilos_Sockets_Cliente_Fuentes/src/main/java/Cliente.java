import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        System.out.println(" APLICACIÓN CLIENTE");
        System.out.println("-----------------------------------");

        String menu = "Elija una de las siguientes opciones introduciendo el número:\n" +
                "1. Insertar libro.\n" +
                "2. Eliminar libro.\n" +
                "3. Consultar libro por ISBN.\n" +
                "4. Consultar libro por titulo.\n" +
                "5. Consultar libro por autor/a.\n" +
                "6. Salir de la aplicación.\n";

        Scanner scanner = new Scanner(System.in);
        Socket cliente = null;
        BufferedReader entradaServidor = null;
        PrintWriter salidaServidor = null;

        try {
            // Crea un cliente y se conecta al servidor en 192.168.1.107:8080
            cliente = new Socket("192.168.1.107", 8080);
            System.out.println("Ya está conectado al servidor en 192.168.1.107:8080");

            // Crear flujos para enviar y recibir datos
            entradaServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salidaServidor = new PrintWriter(cliente.getOutputStream(), true);

            int respuestaMenu = 0;
            while (respuestaMenu !=6) {
                    System.out.println(menu);
                    if (scanner.hasNextInt()) {
                        respuestaMenu = scanner.nextInt();
                        scanner.nextLine(); // consume el salto de línea y limpia el buffer
                    } else {
                        System.out.println("Por favor, introduzca un número válido.");
                        scanner.nextLine(); // consume la entrada inválida y limpia el buffer
                        continue; // Para volver al inicio del bucle
                    }
                String titulo, autor, isbn, accion, campo;
                float precio = 0;

                switch (respuestaMenu) {
                    case 1:
                        System.out.println("Inserte el título del libro.");
                        titulo = scanner.nextLine();
                        System.out.println("Inserte el autor del libro.");
                        autor = scanner.nextLine();
                        // Valida que el precio sea un float
                        while (true) {
                            System.out.println("Inserte el precio del libro.");
                            if (scanner.hasNextFloat()) {
                                precio = scanner.nextFloat();
                                scanner.nextLine(); // Limpia el buffer
                                // Validar que el precio sea positivo
                                if (precio > 0) {
                                    break;  // Sale del bucle si el precio es válido y positivo
                                } else {
                                    System.out.println("El precio debe ser un valor positivo. Intente de nuevo.");
                                }
                            } else {
                                System.out.println("Por favor, introduzca un precio válido.");
                                scanner.nextLine(); // Limpia el buffer si el precio es incorrecto
                            }
                        }
                        accion = "INSERTAR";
                        String libro = titulo + "," + autor + "," + precio;
                        salidaServidor.println(accion + "," + libro);
                        break;
                    case 2:
                        System.out.println("Inserte el título del libro a eliminar.");
                        accion = "ELIMINAR";
                        titulo = scanner.nextLine();
                        salidaServidor.println(accion + "," + titulo);
                        break;
                    case 3:
                        System.out.println("Inserte el ISBN del libro a buscar.");
                        accion = "BUSCAR";
                        campo = "isbn";
                        isbn = scanner.nextLine();
                        salidaServidor.println(accion + "," + campo + "," + isbn);
                        break;
                    case 4:
                        System.out.println("Inserte el título del libro a buscar.");
                        accion = "BUSCAR";
                        campo = "titulo";
                        titulo = scanner.nextLine();
                        salidaServidor.println(accion + "," + campo + "," + titulo);
                        break;
                    case 5:
                        System.out.println("Inserte el autor o la autora del libro a buscar.");
                        accion = "BUSCAR";
                        campo = "autor";
                        autor = scanner.nextLine();
                        salidaServidor.println(accion + "," + campo + "," + autor);
                        break;
                    case 6:
                        System.out.println("El programa se ha cerrado correctamente. Bye!");
                        salidaServidor.println("6");
                        break;
                    default:
                        System.out.println("Introduzca un número válido.\n");
                }

                // Lee y muestra respuesta del servidor (excepto para salir)
                if (respuestaMenu != 6) {
                    System.out.println("Esperando respuesta del servidor...");
                    String respuesta = entradaServidor.readLine();
                    if (respuesta != null) {
                        System.out.println("Servidor responde:");
                        System.out.println(respuesta);
                    } else {
                        System.out.println("No se recibió respuesta del servidor.");
                    }
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("No se puede establecer comunicación con el servidor");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        } finally {
            try {
                if (entradaServidor != null) entradaServidor.close();
                if (salidaServidor != null) salidaServidor.close();
                if (cliente != null) cliente.close();
                System.out.println("Conexión cerrada.");
            } catch (IOException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}

