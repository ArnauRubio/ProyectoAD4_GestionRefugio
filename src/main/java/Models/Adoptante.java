package Models;

import java.util.ArrayList;
import java.util.List;

public class Adoptante {
    private String dni;              // se guarda como _id
    private String nombre;
    private String apellidos;
    private String telefono;
    private Direccion direccion;     // documento embebido
    private String email;
    private String fechaRegistro;    // aaaa-mm-dd
    private List<String> preferencias = new ArrayList<>();  // array de textos
    private boolean eliminado;

    public Adoptante() {
    }

    public Adoptante(String dni, String nombre, String apellidos, String telefono,
                     Direccion direccion, String email, String fechaRegistro) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "[" + dni + "] " + nombre + " " + apellidos + " | tel: " + telefono + " | " + email
                + (eliminado ? " (ELIMINADO)" : "")
                + "\n      Dirección: " + direccion
                + "\n      Registro: " + fechaRegistro + " | Preferencias: " + preferencias;
    }
}
