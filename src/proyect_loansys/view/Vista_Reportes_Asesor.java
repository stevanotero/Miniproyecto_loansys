package proyect_loansys.view;

import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juans
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Vista_Reportes_Asesor extends Vista_Principal {

    // Componentes
    public JButton btnEstadoGeneral;
    public JButton btnFrecuenciaUso;
    public JButton btnAlertasMantenimiento;
    public JButton btnExportarPDF;
    public JButton btnLimpiar;

    public JTable tablaReportes;
    public DefaultTableModel modelo;
    private JScrollPane scroll;

    public Vista_Reportes_Asesor() {
        super();
        setTextoBienvenida("¡Bienvenido, Asesor!");
        setTextoModulo("Reportes e Historial");

        inicializarComponentes();
        armarLayout();
    }

    private void inicializarComponentes() {
        btnEstadoGeneral = new JButton("Estado General del Inventario");
        btnFrecuenciaUso = new JButton("Frecuencia de Uso");
        btnAlertasMantenimiento = new JButton("Alertas de Mantenimiento");
        btnExportarPDF = new JButton("Exportar a PDF");
        btnLimpiar = new JButton("Limpiar Tabla");

        // Colores
        btnEstadoGeneral.setBackground(new Color(0, 153, 76));
        btnFrecuenciaUso.setBackground(new Color(0, 102, 204));
        btnAlertasMantenimiento.setBackground(new Color(204, 102, 0));
        btnExportarPDF.setBackground(new Color(139, 0, 0));
        btnLimpiar.setBackground(new Color(128, 128, 128));

        btnEstadoGeneral.setForeground(Color.WHITE);
        btnFrecuenciaUso.setForeground(Color.WHITE);
        btnAlertasMantenimiento.setForeground(Color.WHITE);
        btnExportarPDF.setForeground(Color.WHITE);
        btnLimpiar.setForeground(Color.WHITE);

        // Tabla
        modelo = new DefaultTableModel();
        tablaReportes = new JTable(modelo);
        scroll = new JScrollPane(tablaReportes);
    }

    private void armarLayout() {
        // Usamos el panelCentro que viene de la clase padre
        JPanel panelCentro = crearPanelCentro(); // Llamamos al método abstracto
        panelCentro.setLayout(new BorderLayout(10, 10));
        panelCentro.setBackground(Color.WHITE);
        panelCentro.removeAll();

        // Panel de botones superior
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(new EmptyBorder(15, 20, 10, 20));

        panelBotones.add(btnEstadoGeneral);
        panelBotones.add(btnFrecuenciaUso);
        panelBotones.add(btnAlertasMantenimiento);
        panelBotones.add(btnExportarPDF);
        panelBotones.add(btnLimpiar);

        // Panel de la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(new EmptyBorder(10, 20, 20, 20));

        JLabel titulo = new JLabel("Resultados del Reporte");
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        panelTabla.add(titulo, BorderLayout.NORTH);
        panelTabla.add(scroll, BorderLayout.CENTER);

        panelCentro.add(panelBotones, BorderLayout.NORTH);
        panelCentro.add(panelTabla, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    @Override
    protected JPanel crearPanelCentro() {
        // Este método es obligatorio por ser abstracto en Vista_Principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        return panel;
    }
}