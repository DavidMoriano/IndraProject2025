package entities;

public class Evento {
    private int id_evento;
    private String nombre;
    private String fecha;
    private int duracion; //horas
    private String estado;
    private int id_categoria;
    private int id_organizador;
    private int id_ubicacion;

    public Evento() {
        super();
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_organizador() {
        return id_organizador;
    }

    public void setId_organizador(int id_organizador) {
        this.id_organizador = id_organizador;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

     @Override
    public String toString() {
        return String.format("%-10s %-50s %-12s %-15d %-10s %-12d %-15d %-12d", 
                             this.id_evento, this.nombre, this.fecha, this.duracion, this.estado, id_categoria, 
                             this.id_organizador, this.id_ubicacion);
    }

    public static String getHeader() {
        return String.format("%-10s %-50s %-12s %-15s %-10s %-12s %-15s %-12s", 
                             "Id", "Nombre del Evento", "Fecha", "Duración", "Estado", "ID Categoría", "ID Organizador", "ID Ubicación");
    }

}
