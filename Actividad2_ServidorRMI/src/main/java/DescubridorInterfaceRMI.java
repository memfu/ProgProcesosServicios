import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DescubridorInterfaceRMI extends Remote {

    public String buscarNombre(String nombre) throws RemoteException;
    public String buscarNacionalidad(String nacionalidad) throws RemoteException;

    public String buscarYear(int year) throws RemoteException;

    public Constelacion buscarConstelacion(Constelacion constelacion) throws RemoteException;

}
