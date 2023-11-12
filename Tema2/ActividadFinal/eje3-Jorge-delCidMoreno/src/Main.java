import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ListaVideoJuegos handler = new ListaVideoJuegos();
            File xml = new File("videojuegos.xml");
            saxParser.parse(xml,handler);

            ArrayList<Videojuego> videojuegos = handler.getListaVideoJuegos();
            for (Videojuego v : videojuegos) {
                if ("Activa".equals(v.getEstado())) {
                    System.out.println("Título: " + v.getTítulo());
                    System.out.println("Semilla: " + v.getSemilla());
                    System.out.println("Palabras clave: " + v.getPalabras_clave());
                    System.out.println("--------------");
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
    }
}
}