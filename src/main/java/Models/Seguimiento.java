package Models;

/**
 * Visita o revisión de seguimiento de una adopción aprobada.
 * Se guarda EMBEBIDO en el array "seguimiento" de la adopción.
 */
public class Seguimiento {

    private String fecha;       // aaaa-mm-dd
    private String comentario;

    public Seguimiento() {
    }

    public Seguimiento(String fecha, String comentario) {
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return fecha + " - " + comentario;
    }
}
