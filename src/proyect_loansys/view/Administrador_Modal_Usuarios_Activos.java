/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Administrador_Modal_Usuarios_Activos extends JDialog {

    private Container contenedor;
    private JPanel datos;
    private JLabel lnombre;
    private JLabel lapellido;
    private JLabel ldocumento;
    private JLabel lcorreo;
    private JLabel lestado;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtDocumento;
    public JTextField txtCorreo;
    public JComboBox estado;
    public JButton guardar;
    public JButton cancelar;
    private FlowLayout flow;
    private GridLayout grid;
    private TitledBorder titulo;
    
    private String listaEstado[] = {  "",  "ACTIVO",  "DESACTIVADO"};

    
    public Administrador_Modal_Usuarios_Activos() {

        super();

        contenedor = getContentPane();

        flow = new FlowLayout();
        contenedor.setLayout(flow);

        titulo = new TitledBorder("Cambio de estado");

        datos = new JPanel();
        datos.setBorder(titulo);

        grid = new GridLayout(6, 2, 10, 10);
        datos.setLayout(grid);

        lnombre = new JLabel("Nombre");
        lapellido = new JLabel("Apellido");
        ldocumento = new JLabel("Documento");
        lcorreo = new JLabel("Correo");
        lestado = new JLabel("Estado");
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtDocumento = new JTextField(15);
        txtCorreo = new JTextField(15);

        estado = new JComboBox(listaEstado);
    
        guardar = new JButton("Guardar");
        cancelar = new JButton("Cancelar");
        
        
        //deshabilitar segu necesidad
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtDocumento.setEditable(false);
        txtCorreo.setEditable(false);


        //
        
        datos.add(lnombre);
        datos.add(txtNombre);

        datos.add(lapellido);
        datos.add(txtApellido);

        datos.add(ldocumento);
        datos.add(txtDocumento);

        datos.add(lcorreo);
        datos.add(txtCorreo);
        

        datos.add(lestado);
        datos.add(estado);

        contenedor.add(datos);
        contenedor.add(guardar);
        contenedor.add(cancelar);

  
    }
}