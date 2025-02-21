import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Descubridor {
    private String nombre;
    private String nacionalidad;
    private int yearDiscover;
    private Constelacion constelacion;

    @Override
    public String toString() {
        return "La constelación " + constelacion.getNombre() + " fue descubierta por " + nombre +
                " (" + nacionalidad + ") en el año " + yearDiscover;
    }
}
