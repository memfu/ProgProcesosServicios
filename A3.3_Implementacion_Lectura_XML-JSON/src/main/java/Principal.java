import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;

public class Principal {
    public static void main(String[] args) {
        /*
        JAXBContext contexto va asociado a la clase Personaje y es utilizado por el objeto
        Marshaller para localizar información respecto a la estructura del objeto a convertir.
         */
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(Personaje.class);
        } catch (JAXBException e) {
            System.out.println("Error creando el contexto.");
            System.out.println(e.getMessage());
            return;
        }
        Marshaller m; // Se usa para realizar la conversión
        try {
            m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Personaje p = new Personaje(1, "Homer", "Simpson", 48, "Trabajador Central Nuclear");
            m.marshal(p, System.out);
            m.marshal(p, new File("Homer.xml")); // Con esta línea creamos un archivo .xml
        } catch (JAXBException e) {
            System.out.println("Error convirtiendo el objeto a formato XML.");
            System.out.println(e.getMessage());
        }
    }
}