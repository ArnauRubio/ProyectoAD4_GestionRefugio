package Models;

/**
 * Un registro del historial médico de un animal.
 * Se guarda EMBEBIDO dentro del documento del animal (array "historialMedico").
 */
public class RegistroMedico {

    private String fecha;        // formato aaaa-mm-dd
    private String descripcion;
    private String veterinario;

    public RegistroMedico() {
    }

    public RegistroMedico(String fecha, String descripcion, String veterinario) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.veterinario = veterinario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
