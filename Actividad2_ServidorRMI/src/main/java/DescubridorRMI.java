import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DescubridorRMI extends UnicastRemoteObject implements DescubridorInterfaceRMI{
    private static final long serialVersionUID = -20250221L;
    private ArrayList<Descubridor> descubridores;

    ConstelacionRMI constelacionRMI ;


    public DescubridorRMI() throws RemoteException {

        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(0))); // Osa Mayor
        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(1))); // Osa Menor
        descubridores.add(new Descubridor("Babilonios", "Mesopotamia", -1000, constelacionRMI.constelaciones.get(2))); // Tauro
        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(3))); // Leo
        descubridores.add(new Descubridor("Babilonios", "Mesopotamia", -1000, constelacionRMI.constelaciones.get(4))); // Escorpio
        descubridores.add(new Descubridor("Johann Bayer", "Alemania", 1603, constelacionRMI.constelaciones.get(5))); // Can Mayor
        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(6))); // Casiopea
        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(7))); // El Boyero
        descubridores.add(new Descubridor("Andreas Corsali", "Italia", 1515, constelacionRMI.constelaciones.get(8))); // Cruz del Sur
        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(9))); // Acuario
        descubridores.add(new Descubridor("Claudio Ptolomeo", "Grecia", 150, constelacionRMI.constelaciones.get(10))); // Géminis
    }


    @Override
    public String buscarNombre(String nombre) throws RemoteException {
        String resultado = "";
        for (Descubridor c : descubridores) {
            if (c.getNombre().contains(nombre)) {
                resultado = resultado + c + "\n";
            }
        }
        return resultado;
    }

    @Override
    public String buscarNacionalidad(String nacionalidad) throws RemoteException {
        String resultado = "";
        for (Descubridor c : descubridores) {
            if (c.getNacionalidad().contains(nacionalidad)) {
                resultado = resultado + c + "\n";
            }
        }
        return resultado;
    }

    @Override
    public String buscarYear(int year) throws RemoteException {
        String resultado = "";
        for (Descubridor c : descubridores) {
            if (c.getYearDiscover() == year) {
                resultado = resultado + c + "\n";
            }
        }
        return resultado;
    }

    @Override
    public String buscarConstelacion(String constelacion) throws RemoteException {
        String resultado = "";
        for (Descubridor d : descubridores) {
            if (d.getConstelacion().equals(constelacionRMI.buscarNombre(constelacion)) ) {
                resultado = d.toString() + "\n";
            }
        }
        return resultado;
    }


}
