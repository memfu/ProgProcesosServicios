import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "grupo")
public class GrupoPersonajes {
    private ArrayList<Personaje> personajes;
    public GrupoPersonajes() {
        personajes = new ArrayList<Personaje>();
    }
    @XmlElement(name = "persona")
    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }
    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }
}
