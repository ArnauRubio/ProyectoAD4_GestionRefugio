package Models;

import java.util.Date;

public class Adoptante {
    private int id;
    private String dni;              // se guarda como _id
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;     // documento embebido
    private String email;
    private Date fechaRegistro;    // aaaa-mm-dd
    private String preferencias;  // array de textos
    private boolean enRefugio;

    public Adoptante() {
    }

    public Adoptante(String dni, String nombre, String apellidos, String telefono,
                     String direccion, String email, Date fechaRegistro, String preferencias) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.preferencias = preferencias;
        this.enRefugio = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public boolean isEnRefugio() {
        return enRefugio;
    }

    public void setEnRefugio(boolean enRefugio) {
        this.enRefugio = enRefugio;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + dni + " | " + nombre + " " + apellidos + " | tel: " + telefono + " | " + email
                + (enRefugio ? " (ELIMINADO)" : "")
                + "\n      Dirección: " + direccion
                + "\n      Registro: " + fechaRegistro + " | Preferencias: " + preferencias;
    }
}
