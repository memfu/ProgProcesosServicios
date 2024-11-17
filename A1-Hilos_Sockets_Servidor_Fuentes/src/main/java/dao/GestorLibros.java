package dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Libro;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor(force = true)

public class GestorLibros {
    private final ArrayList<Libro> biblioteca = new ArrayList<>();


    public void inicializarLibrosBiblio() {
        // Libros previamente añadidos a la lista
        biblioteca.add(new Libro("A1", "Frankenstein", "Mary Shelley", 10.50f));
        biblioteca.add(new Libro("B2", "The Phantom of the Opera", "Gaston Leroux", 15.00f));
        biblioteca.add(new Libro("C3", "The Picture of Dorian Gray", "Oscar Wilde", 25f));
        biblioteca.add(new Libro("D4", "Dracula", "Bram Stoker", 30.60f));
        biblioteca.add(new Libro("E5", "A Christmas Carol", "Charles Dickens", 5.90f));

    }

    public synchronized String insertBook(String title, String author, float price) {
        // Genera un ISBN único
        String isbn = generarISBNUnico();

        // Crea el libro con el ISBN generado
        Libro nuevoLibro = new Libro(isbn, title, author, price);

        // Agrega el libro a la lista
        biblioteca.add(nuevoLibro);

        return "Libro añadido exitosamente: " + nuevoLibro.toString();
    }

    public synchronized String deleteBookByTitle(String titulo) {
        for (Libro book : biblioteca) {
            if (book.getTitulo().equalsIgnoreCase(titulo)) {
                biblioteca.remove(book);
                return "El libro con el título \"" + titulo + "\" ha sido eliminado con éxito.";
            }
        }
        return "No hay ningún libro en la biblioteca con el ISBN " + titulo;
    }

    public synchronized String getBook(String campo, String valor) {
        campo = campo.toLowerCase();
        /*
        Para el caso de que haya un autor con varios libros,
        usamos StringBuilder para almacenar todos los libros encontrados
         */
        StringBuilder respuesta = new StringBuilder();
        boolean encontrado = false; // Variable para saber si el libro fue encontrado

        for (Libro book : biblioteca) {
        switch (campo) {
            case "isbn":
                if (book.getISBN().equalsIgnoreCase(valor)) {
                    respuesta.append(book.toString()).append(" - "); // Agrega el libro encontrado a la respuesta
                    encontrado = true;
                }
                break;
            case "titulo":
                if (book.getTitulo().equalsIgnoreCase(valor)) {
                    respuesta.append(book.toString()).append(" - ");
                    encontrado = true;
                }
                break;
            case "autor":
                if (book.getAutor().equalsIgnoreCase(valor)) {
                    respuesta.append(book.toString()).append(" - ");
                    encontrado = true;
                }
                break;
            default:
                // Si el campo no es válido, salir del bucle
                return "No hay ningún libro con ese campo.";
         }
         }

        // Si se encontró al menos un libro, devuelve la lista completa
        if (encontrado) {
            return respuesta.toString();  // Retorna la lista de libros encontrados
        } else {
            // Si no se encontró ningún libro, informar al usuario
            return "No hay ningún libro en la biblioteca con el " + campo + " \"" + valor + "\".";
        }
    }

    private String generarISBNUnico() {
        String isbn;
        do {
            // Genera un ISBN en formato LetraNúmero (p.ej., A123)
            char letra = (char) ('A' + (int) (Math.random() * 26)); // Letra aleatoria de A a Z
            int numero = (int) (Math.random() * 1000); // Número aleatorio entre 0 y 999
            isbn = letra + String.format("%03d", numero); // Combina en formato LetraNNN
        } while (isbnExistente(isbn)); // Se asegura de que el ISBN no exista
        return isbn;
    }

    private boolean isbnExistente(String isbn) {
        for (Libro libro : biblioteca) {
            if (libro.getISBN().equalsIgnoreCase(isbn)) {
                return true; // ISBN ya existe
            }
        }
        return false; // ISBN no existe
    }
}
