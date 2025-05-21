package entities;

public class Categoria {
    private int id_categoria;
    private String nombre;

    public Categoria() {
        super();
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getHeader() {
        return String.format("%-10s %-50s\n%-10s %-50s", "Id", "Nombre de la categoria", "0", "Todas las categorias");
    }

    @Override
    public String toString() {
        return String.format("%-10s %-50s", this.id_categoria, this.nombre);
    }

}
