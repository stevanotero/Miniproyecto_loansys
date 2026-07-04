package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.view.Plantilla;

public class Reportes_Tecnico extends Plantilla {

    private JPanel panelFiltros;
    public JTextField txtBuscar;
    public JButton btnGenerarReporte;
    public JTable tabla;
    public JButton btnExportarReporte;

    public JLabel lblNumeroTotal;
    public JLabel lblNumeroRealizados;
    public JLabel lblNumeroProceso;

    public Reportes_Tecnico() {
        super("Reportes Técnico");
        super.Reportes.setBackground(new Color(90,91,92));
        super.Reportes.setForeground(Color.WHITE);

        JPanel panel5 = super.getPanel();
        panel5.setBackground(new Color(245, 245, 245));
        panel5.setLayout(null);

        panelFiltros = new JPanel();
        panelFiltros.setLayout(null);
        panelFiltros.setBounds(20, 20, 930, 60);
        panelFiltros.setBackground(new Color(245, 245, 245));

        txtBuscar = new JTextField("Buscar elemento...");
        txtBuscar.setBounds(10, 15, 180, 30);
        txtBuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscar.getText().equals("Buscar elemento...")) {
                    txtBuscar.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscar.getText().trim().isEmpty()) {
                    txtBuscar.setText("Buscar elemento...");
                }
            }
        });

        btnGenerarReporte = new JButton("+ Generar reporte");
        btnGenerarReporte.setBounds(450, 15, 170, 30);
        btnGenerarReporte.setBackground(new Color(40, 167, 69));
        btnGenerarReporte.setForeground(Color.WHITE);
        btnGenerarReporte.setFocusPainted(false);

        btnExportarReporte = new JButton("→ Exportar reporte");
        btnExportarReporte.setBounds(630, 15, 170, 30);
        btnExportarReporte.setBackground(new Color(40, 167, 69));
        btnExportarReporte.setForeground(Color.WHITE);
        btnExportarReporte.setFocusPainted(false);

        panelFiltros.add(txtBuscar);
        panelFiltros.add(btnGenerarReporte);
        panelFiltros.add(btnExportarReporte);
        panel5.add(panelFiltros);

        JPanel panelTotal = new JPanel();
        panelTotal.setLayout(null);
        panelTotal.setBounds(70, 120, 180, 100);
        panelTotal.setBackground(new Color(220, 220, 225));

        JLabel lblTotal = new JLabel("Total mantenimiento");
        lblTotal.setBounds(20, 15, 150, 20);
        lblNumeroTotal = new JLabel("0");
        lblNumeroTotal.setFont(new Font("Arial", Font.BOLD, 30));
        lblNumeroTotal.setBounds(80, 45, 50, 30);

        panelTotal.add(lblTotal);
        panelTotal.add(lblNumeroTotal);

        JPanel panelRealizados = new JPanel();
        panelRealizados.setLayout(null);
        panelRealizados.setBounds(340, 120, 180, 100);
        panelRealizados.setBackground(new Color(220, 220, 225));

        JLabel lblRealizados = new JLabel("Mantenimiento realizado");
        lblRealizados.setBounds(10, 15, 170, 20);
        lblNumeroRealizados = new JLabel("0");
        lblNumeroRealizados.setFont(new Font("Arial", Font.BOLD, 30));
        lblNumeroRealizados.setBounds(80, 45, 50, 30);

        panelRealizados.add(lblRealizados);
        panelRealizados.add(lblNumeroRealizados);

        JPanel panelProceso = new JPanel();
        panelProceso.setLayout(null);
        panelProceso.setBounds(610, 120, 180, 100);
        panelProceso.setBackground(new Color(220, 220, 225));

        JLabel lblProceso = new JLabel("Mantenimiento en proceso");
        lblProceso.setBounds(10, 15, 170, 20);
        lblNumeroProceso = new JLabel("0");
        lblNumeroProceso.setFont(new Font("Arial", Font.BOLD, 30));
        lblNumeroProceso.setBounds(80, 45, 50, 30);

        panelProceso.add(lblProceso);
        panelProceso.add(lblNumeroProceso);

        panel5.add(panelTotal);
        panel5.add(panelRealizados);
        panel5.add(panelProceso);

        String[] columnas = {"Código", "Elemento", "Fecha realizado", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(70, 270, 720, 180);
        panel5.add(scroll);

        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}