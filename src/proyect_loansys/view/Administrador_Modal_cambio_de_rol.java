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

/**
 *
 * @author juans
 */
public class Administrador_Modal_cambio_de_rol  extends JDialog {
     private Container contenedor;
    private JPanel datos;
    private JLabel lnombre;
    private JLabel lapellido;
    private JLabel ldocumento;
    private JLabel lcorreo;
    private JLabel lestado, lrol;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtDocumento;
    public JTextField txtCorreo;
    public JComboBox rol;
    public JButton guardar;
    public JButton cancelar;
    private FlowLayout flow;
    private GridLayout grid;
    private TitledBorder titulo;
    

    private String listadoRol[]={"","APRENDIZ","INSTRUCTOR","TECNICO","ASESOR","ADMINISTRADOR"};
    
    public Administrador_Modal_cambio_de_rol() {

        super();

        contenedor = getContentPane();

        flow = new FlowLayout();
        contenedor.setLayout(flow);

        titulo = new TitledBorder("Cambio de rol");

        datos = new JPanel();
        datos.setBorder(titulo);

        grid = new GridLayout(6, 2, 10, 10);
        datos.setLayout(grid);

        lnombre = new JLabel("Nombre");
        lapellido = new JLabel("Apellido");
        ldocumento = new JLabel("Documento");
        lcorreo = new JLabel("Correo");
        lestado = new JLabel("Estado");
        lrol = new JLabel("Rol");
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtDocumento = new JTextField(15);
        txtCorreo = new JTextField(15);


        rol = new JComboBox(listadoRol);
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
        
        datos.add(lrol);
        datos.add(rol);


        contenedor.add(datos);
        contenedor.add(guardar);
        contenedor.add(cancelar);

  
    }
}

