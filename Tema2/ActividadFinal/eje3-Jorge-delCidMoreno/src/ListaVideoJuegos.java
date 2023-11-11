import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name="list")
@XmlType(propOrder={"videoJuegos"})
public class ListaVideoJuegos {

    private ArrayList<Videojuego> listaVideoJuegos = new ArrayList<>();

    public ListaVideoJuegos() {
    }

    @XmlElementWrapper(name="list")
    @XmlElement(name="videojuego")
    public ArrayList<Videojuego> getVideoJuegos() {
        return listaVideoJuegos;
    }

    public void setListaVideojuegos(ArrayList<Videojuego> listaVideojuegos) {
        this.listaVideoJuegos = listaVideojuegos;
    }
}
