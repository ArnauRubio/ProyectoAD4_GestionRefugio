package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 * Visita o revisión de seguimiento de una adopción aprobada.
 * Se guarda EMBEBIDO en el array "seguimiento" de la adopción.
 */
public class Seguimiento {

    private Date fecha;       // aaaa-mm-dd
    private String comentario;

    public Seguimiento() {
    }

    public Seguimiento(Date fecha, String comentario) {
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
