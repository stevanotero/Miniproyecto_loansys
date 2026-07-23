/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;
/**
 *
 * @author Sants
 */
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class Mantenimiento extends Plantilla {
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JTextField txtBuscar;
    private JButton btnRegistrarMantenimiento;
    private JTable tabla;

    private JPanel formularioView;

    public Mantenimiento() {
        super("Mantenimiento - LoanSys");
        //color de la interfaz, el lado izquierdo de la barra
        super.Mantenimiento.setBackground(new Color(90, 91, 92));
        super.Mantenimiento.setForeground(Color.WHITE);
        vistaP();
        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void vistaP() {
        JPanel panelCentral = getPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("Panel de mantenimiento");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 250, 25);
        panelCentral.add(lblTitulo);

        JLabel lblSub = new JLabel("Gestión de mantenimientos registrados");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(20, 58, 250, 20);
        panelCentral.add(lblSub);

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBounds(0, 70, 1000, 600);
        panelContenido.setBackground(new Color(245, 245, 245));

        panelContenido.add(cVistaLista(), "lista");

        formularioView = new JPanel(null);
        formularioView.setBackground(new Color(245, 245, 245));
        panelContenido.add(formularioView, "formulario");

        panelCentral.add(panelContenido);
    }

    private JPanel cVistaLista() {
        JPanel panelLista = new JPanel(null);
        panelLista.setBackground(new Color(245, 245, 245));

        txtBuscar = new JTextField("Buscar...");
        txtBuscar.setBounds(25, 10, 180, 30);
        txtBuscar.setBackground(new Color(209, 209, 214));
        txtBuscar.setForeground(Color.GRAY);
        agregarPlaceholder(txtBuscar, "Buscar...");
        panelLista.add(txtBuscar);

        btnRegistrarMantenimiento = new JButton("Registrar Mantenimiento");
        btnRegistrarMantenimiento.setBounds(310, 10, 190, 30);
        btnRegistrarMantenimiento.setBackground(new Color(61, 201, 90));
        btnRegistrarMantenimiento.setForeground(Color.WHITE);
        btnRegistrarMantenimiento.setFocusPainted(false);
        panelLista.add(btnRegistrarMantenimiento);

        String[] columnas = {
            "Código", "Elemento", "Técnico", "Fecha del mantenimiento"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        tabla = new JTable(modelo);
        tabla.setRowHeight(40);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(35, 90, 730, 320);
        panelLista.add(scroll);

        return panelLista;
    }
//parte del buscador
    private void agregarPlaceholder(JTextField campo, String texto) {
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }
                //el buscador pero si se deja vacio se pone de nuevo el coso que esta dentro, la paalabra por defecto "buscar"
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().trim().isEmpty()) {
                    campo.setText(texto);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void mostrarLista() {
        cardLayout.show(panelContenido, "lista");
    }

    public JButton getBtnRegistrarMantenimiento() {
        return btnRegistrarMantenimiento;
    }

    public JTable getTabla() {
        return tabla;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }
}