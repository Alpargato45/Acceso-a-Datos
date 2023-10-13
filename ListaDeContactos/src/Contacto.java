import java.io.*;

public class Contacto implements Serializable {

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String descripcion;

    public Contacto(String nombre, String apellido, String correo, String telefono, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Contacto: " + nombre + " " + apellido + " Correo electrónico: "
                + correo + " Teléfono: " + telefono + "\n Descripción: " + descripcion;
    }
}
