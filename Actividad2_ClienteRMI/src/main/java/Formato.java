import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formato {
    public static String formatearNombre(String nombre) {
        // Elimina espacios extras al inicio y final
        nombre = nombre.trim();

        // Si es "Cruz del sur", mantenerlo tal cual
        if (nombre.equalsIgnoreCase("Cruz del sur")) {
            return "Cruz del sur";
        }

        // Expresión regular para transformar el nombre
        Pattern pattern = Pattern.compile("\\b(\\p{L})\\p{L}*");
        Matcher matcher = pattern.matcher(nombre.toLowerCase());

        StringBuffer resultado = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(resultado, matcher.group(1).toUpperCase() + matcher.group().substring(1));
        }
        matcher.appendTail(resultado);

        return resultado.toString();
    }
}
