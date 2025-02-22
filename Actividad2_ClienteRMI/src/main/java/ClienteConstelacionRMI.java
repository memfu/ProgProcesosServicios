import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class ClienteConstelacionRMI {
    public static void main(String[] args) {
        Registry registry;
        Scanner lector = new Scanner(System.in);
        Formato formato = null;
        try {
            //La parte importante del código, que permite al cliente obtener el stub para comunicarse con el
            //objeto remoto,
            //obtener el acceso al registro de objetos remotos a través de la misma dirección IP y puerto suministrado
            //acordaos de cambiar la IP
            registry = LocateRegistry.getRegistry("localhost", 5055);
            System.out.println("Hemos obtenido el registro");
            //stub a partir del identificador del objeto suministrado en el programa servidor
            ConstelacionInterfaceRMI constelaciones = (ConstelacionInterfaceRMI) registry.lookup("miConstelacion");
            DescubridorInterfaceRMI descubridores = (DescubridorInterfaceRMI) registry.lookup("miDescubridor");
            System.out.println("Hemos obtenido el objeto remoto");
            System.out.println(); // Retorno de carro.
            String buscado;
            String opcion;
            do {
                escribirMenu();
                opcion = lector.nextLine().toUpperCase();
                if (opcion.equals("C")) {
                    opcion = "CN";
                } else if (opcion.equals("D")) {
                    escribirMenuD();
                    opcion = opcion + lector.nextLine().toUpperCase();
                } else {
                    System.out.println("Elija una opción correcta");
                }
                switch (opcion) {
                    case "CN":
                        System.out.println("Escribe el nombre de la constelación: ");
                        buscado = formato.formatearNombre(lector.nextLine());
                        System.out.println(constelaciones.buscarNombre(buscado));
                        break;
                    case "DN":
                        System.out.println("Escribe el nombre del descubridor: ");
                        buscado = formato.formatearNombre(lector.nextLine());
                        System.out.println(descubridores.buscarNombre(buscado));
                        break;
                    case "DNAC":
                        System.out.println("Escribe el país de origen del descubridor: ");
                        buscado = formato.formatearNombre(lector.nextLine());
                        System.out.println(descubridores.buscarNacionalidad(buscado));
                        break;
                    case "DA":
                        System.out.println("Escribe el año del descubrimiento: ");
                        buscado = lector.nextLine();
                        int a = Integer.parseInt(buscado);
                        System.out.println(descubridores.buscarYear(a));
                        break;
                    case "DC":
                        System.out.println("Escribe la constelación del descubridor: ");
                        buscado = formato.formatearNombre(lector.nextLine());
                        System.out.println(descubridores.buscarConstelacion(buscado));
                        break;
                    case "F":
                        System.out.println("Programa finalizado");
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
            } while (!opcion.equals("F"));
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
        }
        lector.close();
    }
    private static void escribirMenu() {
        System.out.println();
        System.out.println("Constelaciones y descubridores");
        System.out.println("--------------------------");
        System.out.println("Indique sobre que quiere buscar: ");
        System.out.println("C = Constelación");
        System.out.println("D = Descubridor");
        System.out.println("--------------------------");
        System.out.println("¿Qué opción elige?");
        System.out.println("Para finalizar el programa escriba \"F\"");
    }

    private static void escribirMenuD() {
        System.out.println();
        System.out.println("Descubridores");
        System.out.println("--------------------------");
        System.out.println("Indique sobre que quiere buscar: ");
        System.out.println("N = Nombre");
        System.out.println("Nac = Nacionalidad");
        System.out.println("A = Año del descubrimiento");
        System.out.println("C = Constelación descubierta");
        System.out.println("--------------------------");
        System.out.println("¿Qué opción elige?");
        System.out.println("Para finalizar el programa escriba \"F\"");
    }
}