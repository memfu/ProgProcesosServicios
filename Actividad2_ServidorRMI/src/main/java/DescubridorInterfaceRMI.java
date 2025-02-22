import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DescubridorInterfaceRMI extends Remote {

    public String buscarNombre(String nombre) throws RemoteException;
    public String buscarNacionalidad(String nacionalidad) throws RemoteException;

    public String buscarYear(int year) throws RemoteException;

    public String buscarConstelacion(String constelacion) throws RemoteException;

}

