package dao;

import model.Asignatura;
import model.Estudiante;

import java.util.ArrayList;

public class EstudianteDAO {

    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();


    public void listarEstudiantes() {
        Estudiante estudiante1 = new Estudiante("12345678A", "Juan", "Pérez");
        estudiante1.agregarAsignatura(new Asignatura("PSP", 8.5f));
        estudiante1.agregarAsignatura(new Asignatura("PMDM", 7.2f));
        estudiante1.agregarAsignatura(new Asignatura("AD", 6.5f));
        estudiante1.agregarAsignatura(new Asignatura("DI", 9.3f));

        Estudiante estudiante2 = new Estudiante("12345678B", "María", "Castedo");
        estudiante2.agregarAsignatura(new Asignatura("PSP", 8.5f));
        estudiante2.agregarAsignatura(new Asignatura("PMDM", 7.2f));
        estudiante2.agregarAsignatura(new Asignatura("AD", 9.5f));
        estudiante2.agregarAsignatura(new Asignatura("DI", 8.2f));

        Estudiante estudiante3 = new Estudiante("12345678C", "María", "Castedo");
        estudiante3.agregarAsignatura(new Asignatura("PSP", 5.5f));
        estudiante3.agregarAsignatura(new Asignatura("PMDM", 8.2f));
        estudiante3.agregarAsignatura(new Asignatura("AD", 7.9f));
        estudiante3.agregarAsignatura(new Asignatura("DI", 8.8f));

        listaEstudiantes.add(estudiante1);
        listaEstudiantes.add(estudiante2);
        listaEstudiantes.add(estudiante3);
    }

    public String getEstudianteByDNI(String dni) {
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getDni().equals(dni)) {
                return estudiante.toString();
            }
        }
        return "No hay ningún alumno con ese DNI.";
    }
}