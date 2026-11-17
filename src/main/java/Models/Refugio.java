package Models;

import java.util.Date;

public class Refugio {
    private String nombre;           // razón social
    private String cif;
    private Date fechaApertura;    // aaaa-mm-dd
    private Direccion direccion;     // documento embebido
    private String telefono;
    private String email;

    public Refugio() {
    }

    public Refugio(String nombre, String cif, Date fechaApertura, Direccion direccion,
                   String telefono, String email) {
        this.nombre = nombre;
        this.cif = cif;
        this.fechaApertura = fechaApertura;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "  " + nombre + "  (CIF: " + cif + ")\n"
                + "  Abierto desde: " + fechaApertura + "\n"
                + "  Dirección: " + direccion + "\n"
                + "  Contacto: " + telefono + " | " + email;
    }
}
