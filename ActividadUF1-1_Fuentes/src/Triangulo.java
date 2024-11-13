import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Triangulo {


    public static void main(String[] args){
        // Definir el formato deseado para la fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.nnnnnnnnn");
        // Comprueba que haya argumentos
        if (args.length == 0) {
            System.out.println("Se requiere un argumento");
            return;
        }
        // Definir variable con fecha y hora para escribir en el fichero
        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        // Formatear la fecha y hora
        String fechaFormateada = fechaYHoraActual.format(formato);
        System.out.println("La fecha de inicio del proceso es el " + fechaFormateada);
        // Convierte el argumento en una variable "filas" de tipo integer
        int filas = Integer.parseInt(args[0]);
        // mientras que el no de filas sea mayor o igual a uno, ejecuta la función y luego réstale uno a "filas"
        for (int i=filas; i>=1; i--) {
            // empezando por 1, mientras que el número sea menor o igual que el no de filas, imprime el número
            // y luego súmale 1
            for (int n=1; n<=i; n++) {
                System.out.print(n);
            }
            System.out.println();
        }
        fechaYHoraActual = LocalDateTime.now();
        fechaFormateada = fechaYHoraActual.format(formato);
        System.out.println("La fecha de finalización del proceso es el " + fechaFormateada);
    }
}