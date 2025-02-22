import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DescubridorRMI extends UnicastRemoteObject implements DescubridorInterfaceRMI{
    private static final long serialVersionUID = -20250221L;
    private List<Descubridor> descubridores;

    private ConstelacionRMI constelacionRMI = new ConstelacionRMI();


    public DescubridorRMI() throws RemoteException {
        this.constelacionRMI = constelacionRMI; // Inicializa la referencia a las constelaciones
        this.descubridores = new ArrayList<>();


        // Crea los descubridores una sola vez
        Descubridor ptolomeo = new Descubridor("Claudio Ptolomeo", "Grecia", 150);
        Descubridor babilonios = new Descubridor("Babilonios", "Mesopotamia", -1000);
        Descubridor bayer = new Descubridor("Johann Bayer", "Alemania", 1603);
        Descubridor corsali = new Descubridor("Andreas Corsali", "Italia", 1515);

        // Agrega constelaciones a cada descubridor
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(0)); // Osa Mayor
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(1)); // Osa Menor
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(3)); // Leo
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(6)); // Casiopea
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(7)); // El Boyero
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(9)); // Acuario
        ptolomeo.agregarConstelacion(constelacionRMI.constelaciones.get(10)); // Géminis

        babilonios.agregarConstelacion(constelacionRMI.constelaciones.get(2)); // Tauro
        babilonios.agregarConstelacion(constelacionRMI.constelaciones.get(4)); // Escorpio

        bayer.agregarConstelacion(constelacionRMI.constelaciones.get(5)); // Can Mayor

        corsali.agregarConstelacion(constelacionRMI.constelaciones.get(8)); // Cruz del Sur

        // Agrega los descubridores únicos a la lista
        descubridores.add(ptolomeo);
        descubridores.add(babilonios);
        descubridores.add(bayer);
        descubridores.add(corsali);
    }


    @Override
    public String buscarNombre(String nombre) throws RemoteException {
        StringBuilder resultado = new StringBuilder();

        for (Descubridor d : descubridores) {
            if (d.getNombre() != null && d.getNombre().contains(nombre)) {
                resultado.append(d).append("\n");
            }
        }

        return resultado.toString().isEmpty() ? "No se encontró ningún descubridor con ese nombre." : resultado.toString();
    }


    @Override
    public String buscarNacionalidad(String nacionalidad) throws RemoteException {
        StringBuilder resultado = new StringBuilder();

        for (Descubridor d : descubridores) {
            if (d.getNacionalidad() != null && d.getNacionalidad().contains(nacionalidad)) {
                resultado.append(d.getNombre()).append("\n");
            }
        }

        return resultado.toString().isEmpty() ? "No se encontraron descubridores con esa nacionalidad." : resultado.toString();
    }

    @Override
    public String buscarYear(int year) throws RemoteException {
        StringBuilder resultado = new StringBuilder();

        for (Descubridor d : descubridores) {
            if (d.getYearDiscover() == year) {
                resultado.append(d + "\n");
            }
        }

        return resultado.toString().isEmpty() ? "No se encontraron descubridores para ese año." : resultado.toString();
    }

    @Override
    public String buscarConstelacion(String constelacion) throws RemoteException {
        StringBuilder resultado = new StringBuilder();

        for (Descubridor d : descubridores) {
            for (Constelacion c : d.getConstelaciones()) {
                if (c.getNombre().equalsIgnoreCase(constelacion)) {
                    resultado.append(d.getNombre()).append("\n");
                    break; // Optimización: si ya lo encontramos, no seguimos buscando en su lista
                }
            }
        }

        return resultado.toString().isEmpty() ? "No se encontró ningún descubridor para esa constelación." : resultado.toString();
    }


}
