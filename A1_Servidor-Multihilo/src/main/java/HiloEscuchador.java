import dao.EstudianteDAO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    public HiloEscuchador(Socket cliente) {
        numCliente++;
        hilo = new Thread(this, "Cliente"+numCliente);
        this.enchufeAlCliente = cliente;
        hilo.start();
    }
    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();
            String texto = "";
            while (!texto.trim().equals("FIN")) {
                byte[] mensaje = new byte[500];
                entrada.read(mensaje);
                texto = new String(mensaje).trim();  //  con .trim();
                if (texto.trim().equalsIgnoreCase("FIN")) {
                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                    System.out.println(hilo.getName()+" ha cerrado la comunicación");
                } else {
                    System.out.println(hilo.getName() + " dice: " + texto);
                    EstudianteDAO estudianteDAO = new EstudianteDAO();
                    estudianteDAO.listarEstudiantes();
                    String respuesta = estudianteDAO.getEstudianteByDNI(texto);
                    salida.write(("\n" + respuesta + "\nSi quiere finalizar, escriba \"FIN\".").getBytes());
                }
            }
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}