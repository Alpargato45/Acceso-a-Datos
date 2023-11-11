import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws JAXBException {
            JAXBContext context = JAXBContext.newInstance(ListaVideoJuegos.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ListaVideoJuegos listaVideoJuegos = (ListaVideoJuegos) unmarshaller.unmarshal(new File("videojuegos.xml"));
        ArrayList<Videojuego> videojuegos = listaVideoJuegos.getVideoJuegos();

        for(Videojuego v:videojuegos) {
            if(v.getEstado().equals("Activa"))
            System.out.println(v.getTítulo()+ " " +v.getSemilla()+ " " +v.getPalabras_clave());
        }
    }
}