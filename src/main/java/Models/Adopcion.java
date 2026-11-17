package Models;

import java.util.ArrayList;
import java.util.List;

public class Adopcion {
    public static final String PENDIENTE = "pendiente";
    public static final String APROBADA = "aprobada";
    public static final String RECHAZADA = "rechazada";

    private int id;                    // se guarda como _id
    private int idAnimal;              // referencia a animales._id
    private String dniAdoptante;       // referencia a adoptantes._id
    private String fechaSolicitud;     // aaaa-mm-dd
    private String fechaResolucion;    // puede ser null
    private String estado;
    private String observaciones;
    private List<Seguimiento> seguimiento = new ArrayList<>();
    private boolean eliminado;

    public Adopcion() {
    }

    public Adopcion(int id, int idAnimal, String dniAdoptante, String fechaSolicitud, String observaciones) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.dniAdoptante = dniAdoptante;
        this.fechaSolicitud = fechaSolicitud;
        this.observaciones = observaciones;
        this.estado = PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getDniAdoptante() {
        return dniAdoptante;
    }

    public void setDniAdoptante(String dniAdoptante) {
        this.dniAdoptante = dniAdoptante;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Seguimiento> getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(List<Seguimiento> seguimiento) {
        this.seguimiento = seguimiento;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "[" + id + "] animal: " + idAnimal + " | adoptante: " + dniAdoptante
                + " | solicitud: " + fechaSolicitud
                + " | resolución: " + (fechaResolucion == null ? "-" : fechaResolucion)
                + " | estado: " + estado + (eliminado ? " (ELIMINADA)" : "")
                + "\n      Obs: " + observaciones;
    }
}
