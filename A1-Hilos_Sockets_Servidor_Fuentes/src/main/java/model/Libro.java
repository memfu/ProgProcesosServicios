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

    // Constructor sin ISBN para que se cree automáticamente
    public Libro(String titulo, String autor, float precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "* ISBN " + ISBN + " - \"" + titulo + "\" de " + autor + " (" + precio + " EUR)";
    }
}
