public class Videojuego {
    private String título;
    private String Semilla;
    private String Palabras_clave;
    private String Estado;

    public Videojuego() {
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public String getSemilla() {
        return Semilla;
    }

    public void setSemilla(String semilla) {
        Semilla = semilla;
    }

    public String getPalabras_clave() {
        return Palabras_clave;
    }

    public void setPalabras_clave(String palabras_clave) {
        Palabras_clave = palabras_clave;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}