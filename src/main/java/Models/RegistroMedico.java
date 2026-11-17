package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 * Un registro del historial médico de un animal.
 * Se guarda EMBEBIDO dentro del documento del animal (array "historialMedico").
 */
public class RegistroMedico {

    private Date fecha;        // formato aaaa-mm-dd
    private String descripcion;
    private String veterinario;

    public RegistroMedico() {
    }

    public RegistroMedico(Date fecha, String descripcion, String veterinario) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.veterinario = veterinario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    @Override
    public String toString() {
        return fecha + " - " + descripcion + " (vet: " + veterinario + ")";
    }
}
