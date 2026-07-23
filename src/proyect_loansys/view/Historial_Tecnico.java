package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Historial_Tecnico extends Plantilla {

    private JPanel panelFiltros;
    private JPanel panelTarjetas;
    private JTextField txtBuscar;
    private JButton btnRealizados;
    private JButton btnRevision;
    private JButton btnBorrarFiltros;
    private JLabel lblNumeroRealizados;
    private JLabel lblNumeroRevision;
    public JTable tabla;

    public Historial_Tecnico() {
        super("Historial Técnico");
        super.historial.setBackground(new Color(90, 91, 92));
        super.historial.setForeground(Color.WHITE);

        JPanel panel5 = super.getPanel();
        panel5.setBackground(new Color(245, 245, 245));
        panel5.setLayout(null);
        
        //el btn de br filtros 
        panelFiltros = new JPanel();
        panelFiltros.setLayout(null);
        panelFiltros.setBounds(20,20,900,50);
        panelFiltros.setBackground(new Color(245,245,245));

        txtBuscar = new JTextField("Buscar");
        txtBuscar.setBounds(650,10,180,30);
        txtBuscar.setBackground(new Color(209,209,214));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals("Buscar")) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscar.getText().trim().isEmpty()) {
                    txtBuscar.setText("Buscar");
                    txtBuscar.setForeground(Color.GRAY);
                }
            }
        });
        //br filtros
        btnBorrarFiltros = new JButton("Borrar filtros");
        btnBorrarFiltros.setBounds(460, 10, 160, 30);
        btnBorrarFiltros.setBackground(new Color(209, 209, 214));
        btnBorrarFiltros.setFocusPainted(false);
        panelFiltros.add(btnBorrarFiltros);

        panelFiltros.add(txtBuscar);
        panel5.add(panelFiltros);

        panelTarjetas = new JPanel();
        panelTarjetas.setLayout(null);
        panelTarjetas.setBounds(80,90,650,120);
        panelTarjetas.setBackground(new Color(245,245,245));

        btnRealizados = new JButton();
        btnRealizados.setLayout(null);
        btnRealizados.setBackground(new Color(209,209,214));
        btnRealizados.setFocusPainted(false);
        btnRealizados.setBounds(40,10,220,90);

        JLabel lblTituloRealizados = new JLabel("Realizados", SwingConstants.CENTER);
        lblTituloRealizados.setBounds(50, 5, 120, 20);
        lblNumeroRealizados = new JLabel("0", SwingConstants.CENTER);
        lblNumeroRealizados.setFont(new Font("Arial", Font.BOLD, 30));
        lblNumeroRealizados.setBounds(90, 25, 40, 30);
        JLabel lblTextoRealizados = new JLabel("Disponibles", SwingConstants.CENTER);
        lblTextoRealizados.setBounds(10, 60, 200, 20);
        lblTextoRealizados.setFont(new Font("Arial", Font.PLAIN, 10));

        btnRealizados.add(lblTituloRealizados);
        btnRealizados.add(lblNumeroRealizados);
        btnRealizados.add(lblTextoRealizados);

        btnRevision = new JButton();
        btnRevision.setLayout(null);
        btnRevision.setBackground(new Color(209, 209, 214));
        btnRevision.setFocusPainted(false);
        btnRevision.setBounds(350, 10, 220, 90);

        JLabel lblTituloRevision = new JLabel("En mantenimiento", SwingConstants.CENTER);
        lblTituloRevision.setFont(new Font("Arial", Font.PLAIN, 11));
        lblTituloRevision.setBounds(10, 5, 200, 20);
        lblNumeroRevision = new JLabel("0", SwingConstants.CENTER);
        lblNumeroRevision.setFont(new Font("Arial", Font.BOLD, 30));
        lblNumeroRevision.setBounds(90, 25, 40, 30);
        JLabel lblTextoRevision = new JLabel("Elementos en mantenimiento", SwingConstants.CENTER);
        lblTextoRevision.setBounds(10, 60, 200, 20);
        lblTextoRevision.setFont(new Font("Arial", Font.PLAIN, 10));

        btnRevision.add(lblTituloRevision);
        btnRevision.add(lblNumeroRevision);
        btnRevision.add(lblTextoRevision);

        panelTarjetas.add(btnRealizados);
        panelTarjetas.add(btnRevision);
        panel5.add(panelTarjetas);

        String[] columnas = {"Código", "Elemento", "Categoría", "Tipo Mantenimiento", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(30);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 240, 820, 220);
        panel5.add(scroll);

        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton getBtnRealizados() {
        return btnRealizados;
    }

    public JButton getBtnRevision() {
        return btnRevision;
    }

    public JButton getBtnBorrarFiltros() {
        return btnBorrarFiltros;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JLabel getLblNumeroRealizados() {
        return lblNumeroRealizados;
    }

    public JLabel getLblNumeroRevision() {
        return lblNumeroRevision;
    }
}