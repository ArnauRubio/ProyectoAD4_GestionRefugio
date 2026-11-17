package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Animal {
    // Estados posibles
    public static final String DISPONIBLE = "disponible";
    public static final String RESERVADO = "reservado";
    public static final String ADOPTADO = "adoptado";
    public static final String EN_TRATAMIENTO = "en tratamiento";

    private int id;                 // se guarda como _id
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String sexo;
    private String descripcion;
    private Date fechaIngreso;    // aaaa-mm-dd
    private String estado;
    private List<RegistroMedico> historialMedico = new ArrayList<>();
    private boolean eliminado;      // borrado lógico

    public Animal() {
    }

    public Animal(int id, String nombre, String especie, String raza, int edad, String sexo,
                  String descripcion, Date fechaIngreso, String estado) {
        this.id = id;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
