import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

class ListaVideoJuegos extends DefaultHandler {
    private ArrayList<Videojuego> listaVideoJuegos;
    private Videojuego videojuego;
    private String currentElement;

    public ArrayList<Videojuego> getListaVideoJuegos() {
        return listaVideoJuegos;
    }

    @Override
    public void startDocument() throws SAXException {
        listaVideoJuegos = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if ("item".equals(qName)) {
            videojuego = new Videojuego();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (!value.isEmpty()) {
            switch (currentElement) {
                case "Título":
                    videojuego.setTítulo(value);
                    break;
                case "Semilla":
                    videojuego.setSemilla(value);
                    break;
                case "Palabras_clave":
                    videojuego.setPalabras_clave(value);
                    break;
                case "Estado":
                    videojuego.setEstado(value);
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            listaVideoJuegos.add(videojuego);
        }
    }
}