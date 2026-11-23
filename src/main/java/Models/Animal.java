package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Animal {
    private int id;                 // se guarda como _id
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private Sexo sexo;
    private String descripcion;
    private Date fechaIngreso;    // aaaa-mm-dd
    private EstadoAnimal estado;
    private List<RegistroMedico> historialMedico = new ArrayList<>();
    private boolean eliminado = false;      // borrado lógico

    public Animal() {
    }

    public Animal(String nombre, String especie, String raza, int edad, Sexo sexo,
                  String descripcion, Date fechaIngreso, EstadoAnimal estado) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }

    public void setEstado(EstadoAnimal estado) {
        this.estado = estado;
    }

    public List<RegistroMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(List<RegistroMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " | " + especie + " - " + raza + " | " + edad + " años | "
                + sexo + " | ingreso: " + fechaIngreso + " | estado: " + estado
                + (eliminado ? " (ELIMINADO)" : "")
                + "\n      " + descripcion;
    }
}
