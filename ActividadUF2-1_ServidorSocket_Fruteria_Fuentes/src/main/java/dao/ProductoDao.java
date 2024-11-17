package dao;

import model.Producto;

import java.util.TreeMap;

public class ProductoDao {
    // Crea una instancia de la clase TreeMap formada por un string (la abreviatura) y un objeto de tipo Producto
    private TreeMap<String, Producto> productos = new TreeMap<String, Producto>();

    Producto producto = new Producto();

    public void listarFrutas () {
        productos.put("PL",new Producto("Peras limoneras", 14, 5f));
        productos.put("PC",new Producto("Peras conferencia", 12, 7f));
        productos.put("PN",new Producto("Plátano canario", 5, 2.5f));
        productos.put("BN",new Producto("Bananas", 7, 1.3f));
        productos.put("TP",new Producto("Tomates tipo pera", 8, 1.7f));
        productos.put("TR",new Producto("Tomates Raf", 7, 5.3f));
        productos.put("UN",new Producto("Uvas negras", 8, 3.2f));
        productos.put("UB",new Producto("Uvas blancas", 5, 2.7f));
        productos.put("PT",new Producto("Picotas", 8, 4.3f));
        productos.put("CR",new Producto("Ciruelas rojas", 10, 2.8f));
        productos.put("MR",new Producto("Melocotones rojos", 3, 2.5f));
        productos.put("MA",new Producto("Melocotones amarillos", 4, 3.2f));
    }

    public String getFruit(String abreviatura) {
        abreviatura = abreviatura.toUpperCase();
        Producto fruta = productos.get(abreviatura);
        if  (fruta != null) {
            return fruta.toString();
        } else {
            return "No hay ningún producto con esa abreviatura";
        }
    }

}
