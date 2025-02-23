import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "personaje") // aquí especificamos el nombre del elemento raíz
public class Personaje {
    private int idPersonaje;
    private String nombre;
    private String apellido;
    private int edad;
    private String ocupacion;

    public Personaje() {
    }
    public Personaje(int idPersonaje, String nombre, String apellido, int edad, String ocupacion) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ocupacion = ocupacion;
    }

    @XmlAttribute // para poder usar el atributo idPersonaje como atributo de la etiqueta raíz (personaje)
    public int getIdPersonaje() {
        return idPersonaje;
    }
    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
    @XmlElement // se usa con todos los getters correspondientes a los atributos que queramos importar
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @XmlElement
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlElement
    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}
