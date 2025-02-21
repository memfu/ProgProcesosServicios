import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Constelacion {
    private String nombre;
    private String observaciones;
    private Descubridor descubridor;

    public Constelacion(String nombre, String observaciones) {
        this.nombre = nombre;
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Constelación: " + nombre + " - " + observaciones;
    }
}
