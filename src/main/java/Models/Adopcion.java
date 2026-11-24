package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adopcion {

    private int id;                    // se guarda como _id
    private Animal animal;              // referencia a animales._id
    private Adoptante adoptante;       // referencia a adoptantes._id
    private Date fechaSolicitud;     // aaaa-mm-dd
    private Date fechaResolucion;    // puede ser null
    private EstadoAdopcion estado;
    private String observaciones;
    private List<Seguimiento> seguimiento = new ArrayList<>();
    private boolean enRefugio;

    public Adopcion() {
    }

    public Adopcion(Animal animal, Adoptante adoptante, Date fechaSolicitud, String observaciones) {
        this.animal = animal;
        this.adoptante = adoptante;
        this.fechaSolicitud = fechaSolicitud;
        this.observaciones = observaciones;
        this.estado = EstadoAdopcion.PENDIENTE;
        this.enRefugio = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
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

    public EstadoAdopcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAdopcion estado) {
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

    public boolean isEnRefugio() {
        return enRefugio;
    }

    public void setEnRefugio(boolean enRefugio) {
        this.enRefugio = enRefugio;
    }

    @Override
    public String toString() {
        return "[" + id + "] animal: " + animal + " | adoptante: " + adoptante
                + " | solicitud: " + fechaSolicitud
                + " | resolución: " + (fechaResolucion == null ? "-" : fechaResolucion)
                + " | estado: " + estado + (enRefugio ? " (ELIMINADA)" : "")
                + "\n      Obs: " + observaciones;
    }
}
