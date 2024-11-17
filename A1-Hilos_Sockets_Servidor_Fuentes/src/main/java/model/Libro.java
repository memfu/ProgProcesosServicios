package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Libro  implements Serializable {
    private String ISBN, titulo, autor;
    private float precio;

    @Override
    public String toString() {
        /*
         Aquí tampoco fue posible poner un salto de línea al final, porque no se mostraba de forma correcta
         en la consola del cliente
         */

        return "* ISBN " + ISBN + " - \"" + titulo + "\" de " + autor + " (" + precio + " EUR)";
    }
}
