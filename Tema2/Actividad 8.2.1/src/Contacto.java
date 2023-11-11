import java.io.Serializable;

public class Contacto implements Serializable {
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String telefonoTrabajo;
    private String telefonoCasa;
    private String descripcion;

    public Contacto(String nombre, String apellido, String correo, String telefono, String telefonoTrabajo, String telefonoCasa, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.telefonoTrabajo = telefonoTrabajo;
        this.telefonoCasa = telefonoCasa;
        this.descripcion = descripcion;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    @Override
    public String toString() {
        return "Contacto: " + nombre + " " + apellido + " Correo electrónico: "
                + correo + " Teléfono: " + telefono + "\n Descripción: " + descripcion;
    }
}

