package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private String dni;
    private String nombre;
    private String apellidos;
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();

    public Estudiante(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.asignaturas = new ArrayList<>();  // Inicializamos el ArrayList para evitar NullPointerException
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder(nombre + " " + apellidos + " tiene las siguientes notas:\n");

        for (Asignatura asignatura : asignaturas) {
            resultado.append("- ").append(asignatura.getNombre()).append(": ").append(asignatura.getNota()).append("\n");
        }

        return resultado.toString();
    }
}
