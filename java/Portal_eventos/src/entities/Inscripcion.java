package entities;

public class Inscripcion {
    private int id_inscripcion;
    private int id_usuario;
    private int id_evento;
    private String fecha_inscripcion;

    public Inscripcion() {
        super();
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(String fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-50s ", this.id_inscripcion, this.id_evento, this.fecha_inscripcion);
    }

    public static String getHeader() {
        return String.format("%-20s %-20s %-50s %-50s", "Id", "Id evento", "Fecha del evento y fecha de Inscripción", "Nombre del evento");
    }

}
