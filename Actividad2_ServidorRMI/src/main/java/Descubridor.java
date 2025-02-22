import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Descubridor {
    private String nombre;
    private String nacionalidad;
    private int yearDiscover;
    private List<Constelacion> constelaciones = new ArrayList<>();

    public Descubridor(String nombre, String nacionalidad, int yearDiscover) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.yearDiscover = yearDiscover;
    }

    // Método para agregar una constelación a la lista
    public void agregarConstelacion(Constelacion constelacion) {
        this.constelaciones.add(constelacion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" (").append(nacionalidad).append(") en el año ").append(yearDiscover)
                .append(" descubrió las constelaciones: ");

        for (int i = 0; i < constelaciones.size(); i++) {
            sb.append(constelaciones.get(i).getNombre());
            if (i < constelaciones.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
