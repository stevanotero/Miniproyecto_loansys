package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager; // <-- Importante para los JComboBox

public class GenerarReporte_Tecnico extends Plantilla {

    private JPanel panelFormulario;
    private JLabel lblTitulo;
    private JLabel lblEquipo;
    private JLabel lblTecnico;
    private JLabel lblDocumento;
    private JLabel lblTipoMantenimiento;
    private JLabel lblCodigoMantenimiento;
    private JLabel lblEstado;
    private JLabel lblDescripcion;
    public JTextField txtEquipo;
    public JTextField txtTecnico;
    public JTextField txtDocumento;
    public JTextField txtCodigoMantenimiento;
    public JComboBox<String> cbEstado;
    public JComboBox<String> cbTipoMantenimiento;
    public JTextArea txtDescripcion;
    public JButton btnRegistrar;
    public JButton btnCancelar;

    public GenerarReporte_Tecnico() {
        super("Registrar Reporte");

        UIManager.put("ComboBox.disabledForeground", Color.BLACK);

        JPanel panel5 = super.getPanel();
        panel5.setBackground(new Color(245, 245, 245));
        panel5.setLayout(null);

        super.Reportes.setBackground(new Color(90, 91, 92));
        super.Reportes.setForeground(Color.WHITE);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setBounds(80, 20, 760, 380);
        panelFormulario.setBackground(new Color(220, 220, 225));

        lblTitulo = new JLabel("Registrar reporte");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(20, 20, 250, 30);

        lblEquipo = new JLabel("Equipo");
        lblEquipo.setBounds(30, 70, 120, 20);
        txtEquipo = new JTextField();
        txtEquipo.setEditable(false);
        txtEquipo.setFocusable(false);
        txtEquipo.setBackground(new Color(230, 230, 230)); // Fondo gris estilo bloqueado
        txtEquipo.setForeground(Color.BLACK); // Letra negra
        txtEquipo.setBounds(30, 90, 180, 25);

        lblTecnico = new JLabel("Técnico responsable");
        lblTecnico.setBounds(30, 125, 150, 20);
        txtTecnico = new JTextField();
        txtTecnico.setEditable(false);
        txtTecnico.setFocusable(false);
        txtTecnico.setBackground(new Color(230, 230, 230));
        txtTecnico.setForeground(Color.BLACK);
        txtTecnico.setBounds(30, 145, 180, 25);

        lblDocumento = new JLabel("Número de documento");
        lblDocumento.setBounds(30, 180, 160, 20);
        txtDocumento = new JTextField();
        txtDocumento.setEditable(false);
        txtDocumento.setFocusable(false);
        txtDocumento.setBackground(new Color(230, 230, 230));
        txtDocumento.setForeground(Color.BLACK);
        txtDocumento.setBounds(30, 200, 180, 25);

        lblTipoMantenimiento = new JLabel("Tipo de mantenimiento");
        lblTipoMantenimiento.setBounds(30, 235, 160, 20);
        cbTipoMantenimiento = new JComboBox<>();
        cbTipoMantenimiento.addItem("Seleccione");
        cbTipoMantenimiento.addItem("Preventivo");
        cbTipoMantenimiento.addItem("Correctivo");
        cbTipoMantenimiento.setEnabled(false); // Aquí sí usamos false, el UIManager se encarga del color
        cbTipoMantenimiento.setBounds(30, 255, 180, 25);

        lblCodigoMantenimiento = new JLabel("Código del mantenimiento");
        lblCodigoMantenimiento.setBounds(300, 70, 170, 20);
        txtCodigoMantenimiento = new JTextField();
        txtCodigoMantenimiento.setBounds(300, 90, 180, 25);

        lblEstado = new JLabel("Estado");
        lblEstado.setBounds(300, 125, 100, 20);
        cbEstado = new JComboBox<>();
        cbEstado.addItem("Seleccione");
        cbEstado.addItem("En mantenimiento");
        cbEstado.addItem("Realizado");
        cbEstado.addItem("Pendiente");
        cbEstado.setEnabled(false);
        cbEstado.setBounds(300, 145, 180, 25);

        lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(300, 180, 120, 20);
        txtDescripcion = new JTextArea();
        txtDescripcion.setEditable(false);
        txtDescripcion.setFocusable(false);
        txtDescripcion.setBackground(new Color(230, 230, 230));
        txtDescripcion.setForeground(Color.BLACK);
        txtDescripcion.setBounds(300, 200, 210, 95);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(470, 315, 100, 30);
        btnRegistrar.setBackground(Color.GREEN);
        btnRegistrar.setFocusPainted(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(590, 315, 100, 30);
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        panelFormulario.add(lblTitulo);
        panelFormulario.add(lblEquipo);
        panelFormulario.add(txtEquipo);
        panelFormulario.add(lblTecnico);
        panelFormulario.add(txtTecnico);
        panelFormulario.add(lblDocumento);
        panelFormulario.add(txtDocumento);
        panelFormulario.add(lblTipoMantenimiento);
        panelFormulario.add(cbTipoMantenimiento);
        panelFormulario.add(lblCodigoMantenimiento);
        panelFormulario.add(txtCodigoMantenimiento);
        panelFormulario.add(lblEstado);
        panelFormulario.add(cbEstado);
        panelFormulario.add(lblDescripcion);
        panelFormulario.add(txtDescripcion);
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnCancelar);

        panel5.add(panelFormulario);

        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}