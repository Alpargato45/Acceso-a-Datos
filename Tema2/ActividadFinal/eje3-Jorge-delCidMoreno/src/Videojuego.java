import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="item")
@XmlType(propOrder={"título","semilla","palabras_clave"})
public class Videojuego {
    private String título;
    private String Semilla;
    private String Palabras_clave;
    private String Estado;

    public Videojuego() {
    }
    @XmlAttribute(name="Título")
    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }
    @XmlAttribute(name="Semilla")
    public String getSemilla() {
        return Semilla;
    }

    public void setSemilla(String semilla) {
        Semilla = semilla;
    }
    @XmlAttribute(name="Palabras_clave")
    public String getPalabras_clave() {
        return Palabras_clave;
    }
    @XmlAttribute(name="Estado")
    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setPalabras_clave(String palabras_clave) {
        Palabras_clave = palabras_clave;
    }
}
