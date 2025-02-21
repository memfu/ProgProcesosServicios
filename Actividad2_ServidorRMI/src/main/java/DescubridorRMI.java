import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DescubridorRMI extends UnicastRemoteObject implements DescubridorInterfaceRMI{
    private static final long serialVersionUID = -20250221L;
    private ArrayList<Descubridor> descubridores;

    public DescubridorRMI() throws RemoteException {

        // Creación de los descubridores
        Descubridor ptolomeo = new Descubridor("Claudio Ptolomeo", "Grecia", 150);
        Descubridor babilonios = new Descubridor("Babilonios", "Mesopotamia", -1000);
        Descubridor bayer = new Descubridor("Johann Bayer", "Alemania", 1603);
        Descubridor corsali = new Descubridor("Andreas Corsali", "Italia", 1515);

        descubridores.add(ptolomeo);
        descubridores.add(babilonios);
        descubridores.add(bayer);
        descubridores.add(corsali);
    }
    //Comentario
    // Agregar los descubridores a la lista

    //Comentario de Maria


}
