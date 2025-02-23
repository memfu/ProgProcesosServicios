import com.google.gson.Gson;

public class PrincipalFromJson {
    public static void main(String[] args) {
        String json = "{'idPersona':6,'nombre':'Montgomery/Monty','apellido':' Burns','edad':80, 'ocupacion': 'Jefe Central Nuclear'}";
        Gson gson = new Gson();
        Personaje p = gson.fromJson(json, Personaje.class);
        System.out.println(p);
    }
}
