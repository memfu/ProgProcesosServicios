import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Triangulo {


    public static void main(String[] args){
        // Se define el formato deseado para la fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.nnnnnnnnn");

        // Se pide argunmentos en caso de que no haya
        if (args.length == 0) {
            System.out.println("Se requiere un argumento");
            return;
        }

        // Se define la variable con fecha y hora para escribir en el fichero
        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        // Se formatea la fecha y hora
        String fechaFormateada = fechaYHoraActual.format(formato);
        System.out.println("La fecha de inicio del proceso es el " + fechaFormateada);

        // Se convierte el argumento en una variable "filas" de tipo integer
        int filas = Integer.parseInt(args[0]);

        // Mientras que el nr de filas sea mayor o igual a uno, ejecuta la función y luego réstale uno a "filas"
        for (int i = filas; i >= 1; i--) {
            // Empezando por 1, mientras que el número sea menor o igual que el nr de filas, imprime el número
            // y luego súmale 1
            for (int n = 1; n <= i; n++) {
                System.out.print(n);
            }
            System.out.println();
        }
        // Actualiza la fecha y la hora e imprímelas
        fechaYHoraActual = LocalDateTime.now();
        fechaFormateada = fechaYHoraActual.format(formato);
        System.out.println("La fecha de finalización del proceso es el " + fechaFormateada);
    }
}