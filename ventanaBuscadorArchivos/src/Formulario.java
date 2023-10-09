import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Formulario extends JFrame{
    private JPanel panelPrincipal;
    private JButton botonUnidad;
    private JButton botonDirectorio;
    private JButton botonArchivo;
    private JTextField unidadtxt;
    private JTextField directoriotxt;
    private JTextField archivotxt;
    private JButton rutaButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public Formulario() {
        setTitle("Formulario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        add(panelPrincipal);
        setVisible(true);
        setLocationRelativeTo(null);
        archivotxt.setEditable(false);
        directoriotxt.setEditable(false);

        añadirUnidades();
        String ruta1 = comboBox1.getSelectedItem().toString();

        añadirRuta(ruta1);
        añadirDirectorios(ruta1);
        añadirArchivos(ruta1);
        setVisible(true);

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String ruta = comboBox2.getSelectedItem().toString();
                archivotxt.setText("");
                directoriotxt.setText("");
                comboBox2.removeAllItems();
                añadirRuta(ruta);
                añadirDirectorios(ruta);
                añadirArchivos(ruta);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                añadirRuta(comboBox1.getSelectedItem().toString());
            }
        });
    }

    public void añadirDirectorios(String rutaElegida) {

        File ruta = new File(rutaElegida);

        if (ruta.exists()) {
            File[] listaArchivos = ruta.listFiles();

            for (int i = 0; i < listaArchivos.length; i++) {
                if (listaArchivos[i].isDirectory()) {
                    if (i == 0) {
                        archivotxt.setText(listaArchivos[i].toString());
                    } else {
                        archivotxt.setText(archivotxt.getText() + "\n" + listaArchivos[i].toString());
                    }
                }
            }
        }
    }



    public void añadirArchivos(String rutaElegida) {

        File ruta = new File(rutaElegida);

        if (ruta.exists()) {
            File[] listaArchivos = ruta.listFiles();

            for (int i = 0; i < listaArchivos.length; i++) {
                if (listaArchivos[i].isFile()) {
                    if (i == 0) {
                        directoriotxt.setText(listaArchivos[i].toString());
                    } else {
                        directoriotxt.setText(directoriotxt.getText() + "\n" + listaArchivos[i].toString());
                    }
                }
            }
        }
    }

    public void añadirRuta(String rutaElegida){
        File ruta = new File(rutaElegida);

        comboBox2.addItem(comboBox1.getSelectedItem());

        if(ruta.exists()){
            File[] listaArchivos = ruta.listFiles();

            for (int i = 0; i < listaArchivos.length; i++) {
                if (listaArchivos[i].isDirectory()) {
                    comboBox2.addItem(listaArchivos[i]);
                }
            }
        }
    }
    public void añadirUnidades(){
        File[] unidades = File.listRoots();
        File unidad;

        for (int i = 0; i < unidades.length; i++){
            unidad = unidades[i];
            comboBox1.addItem(unidad);
        }
    }
}
