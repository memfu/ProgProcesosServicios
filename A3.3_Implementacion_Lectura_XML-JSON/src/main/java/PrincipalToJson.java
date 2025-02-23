import com.google.gson.Gson;

public class PrincipalToJson {
    public static void main(String[] args) {
        GrupoPersonajes grupo = new GrupoPersonajes();
        grupo.getPersonajes().add(new Personaje(1, "Homer", "Simpson", 48, "Trabajador Central Nuclear"));
        grupo.getPersonajes().add(new Personaje(2, "Lisa", "Simpson", 12, "Estudiante sobresaliente"));
        grupo.getPersonajes().add(new Personaje(3, "Bart", "Simpson", 13, "Estudiante"));
        grupo.getPersonajes().add(new Personaje(4, "Marge", "Simpson", 40, "Ama de casa"));
        grupo.getPersonajes().add(new Personaje(4, "Maggie", "Simpson", 1, "Bebé"));
        Gson gson = new Gson();
        String json = gson.toJson(grupo);
        System.out.println(json);
    }
}