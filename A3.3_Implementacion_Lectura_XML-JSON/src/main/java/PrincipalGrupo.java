import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class PrincipalGrupo {
    public static void main(String[] args) {

        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(GrupoPersonajes.class);
        } catch (JAXBException e) {
            System.out.println("Error creando el contexto");
            System.out.println(e.getMessage());
            return;
        }

        Marshaller m;
        try {
            m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            GrupoPersonajes grupo = new GrupoPersonajes();
            grupo.getPersonajes().add(new Personaje(1, "Homer", "Simpson", 48, "Trabajador Central Nuclear"));
            grupo.getPersonajes().add(new Personaje(2, "Lisa", "Simpson", 12, "Estudiante sobresaliente"));
            grupo.getPersonajes().add(new Personaje(3, "Bart", "Simpson", 13, "Estudiante"));
            grupo.getPersonajes().add(new Personaje(4, "Marge", "Simpson", 40, "Ama de casa"));
            grupo.getPersonajes().add(new Personaje(4, "Maggie", "Simpson", 1, "Bebé"));
            m.marshal(grupo, new File("Simpson.xml"));
            System.out.println("El archivo Simpson.xml ha sido creado con éxito.");
        } catch (JAXBException e) {
            System.out.println("Error convirtiendo el objeto a formato XML.");
            System.out.println(e.getMessage());
        }
    }
}
