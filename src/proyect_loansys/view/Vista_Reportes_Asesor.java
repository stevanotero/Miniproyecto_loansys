/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyect_loansys.view;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juans
 */
public class Vista_Reportes_Asesor extends Vista_Principal {

    public JButton btnEstadoGeneral, btnFrecuenciaUso, btnAlertasMantenimiento, btnExportarPDF, btnLimpiar;
    public JTable tablaReportes;
    public DefaultTableModel modelo;

    public Vista_Reportes_Asesor() {
        super();

        setTextoBienvenida("¡Bienvenido, Asesor!");
        setTextoModulo("Reportes e Historial");
    }

    @Override
    protected JPanel crearPanelCentro() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(Color.WHITE);

        // Crear botones aquí (dentro del método)
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

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(new EmptyBorder(15, 20, 10, 20));

        panelBotones.add(btnEstadoGeneral);
        panelBotones.add(btnFrecuenciaUso);
        panelBotones.add(btnAlertasMantenimiento);
        panelBotones.add(btnExportarPDF);
        panelBotones.add(btnLimpiar);

        // Tabla
        modelo = new DefaultTableModel();
        tablaReportes = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaReportes);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(new EmptyBorder(10, 20, 20, 20));

        JLabel titulo = new JLabel("Resultados del Reporte");
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        panelTabla.add(titulo, BorderLayout.NORTH);
        panelTabla.add(scroll, BorderLayout.CENTER);

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        return panelPrincipal;
    }
}