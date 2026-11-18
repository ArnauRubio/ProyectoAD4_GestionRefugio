package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adopcion {
    public static final String PENDIENTE = "pendiente";
    public static final String APROBADA = "aprobada";
    public static final String RECHAZADA = "rechazada";

    private int id;                    // se guarda como _id
    private int idAnimal;              // referencia a animales._id
    private int idAdoptante;       // referencia a adoptantes._id
    private Date fechaSolicitud;     // aaaa-mm-dd
    private Date fechaResolucion;    // puede ser null
    private String estado;
    private String observaciones;
    private List<Seguimiento> seguimiento = new ArrayList<>();
    private boolean eliminado;

    public Adopcion() {
    }

    public Adopcion(int id, int idAnimal, int idAdoptante, Date fechaSolicitud, String observaciones) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.idAdoptante = idAdoptante;
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

    public int getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
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
        return "[" + id + "] animal: " + idAnimal + " | adoptante: " + idAdoptante
                + " | solicitud: " + fechaSolicitud
                + " | resolución: " + (fechaResolucion == null ? "-" : fechaResolucion)
                + " | estado: " + estado + (eliminado ? " (ELIMINADA)" : "")
                + "\n      Obs: " + observaciones;
    }
}
