package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    private String nombre;
    private int stock;
    private float precio;

    @Override
    public String toString() {
        return "De " + nombre + " hay un stock de " + stock + " unidades y tiene un precio de " + precio + " EUR." ;
    }
}
