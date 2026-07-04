/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import proyect_loansys.view.Vista_Principal;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Alexis
 */
public class Vista_Solicitudes extends Vista_Principal {

    public JTable tablaDeSolicitudes;
    public DefaultTableModel modeloTabla;

    public Vista_Solicitudes() {
        super();
        setTextoBienvenida("");
        setTextoModulo("Panel de Solicitudes"); 
    }

    @Override
    protected JPanel crearPanelCentro() {
        // Panel principal con márgenes limpios y profesionales
        JPanel panelPrincipalCentrado = new JPanel(new BorderLayout());
        panelPrincipalCentrado.setBackground(Color.WHITE);
        panelPrincipalCentrado.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Contenedor interno exclusivo de la tabla
        JPanel panelParaTabla = new JPanel(new BorderLayout());
        panelParaTabla.setOpaque(false);
        
        // Título de la sección
        JLabel lbTituloListado = new JLabel("Listado de Solicitudes Pendientes");
        lbTituloListado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbTituloListado.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        panelParaTabla.add(lbTituloListado, BorderLayout.NORTH);

        // Modelo de tabla restringido (No editable por celda individual)
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        // Estructura de columnas sincronizada con los JOINs de tu base de datos
        modeloTabla.addColumn("Id_Solicitud");
        modeloTabla.addColumn("Código Elemento");
        modeloTabla.addColumn("Elemento");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Documento");
        modeloTabla.addColumn("Rol");
        modeloTabla.addColumn("Fecha Solicitud");
        
        // Configuración estética y de altura de filas para la tabla
        tablaDeSolicitudes = new JTable(modeloTabla);
        tablaDeSolicitudes.setRowHeight(40);
        tablaDeSolicitudes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaDeSolicitudes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaDeSolicitudes.getTableHeader().setReorderingAllowed(false);
        tablaDeSolicitudes.setGridColor(new Color(230, 230, 233));
        
        // ScrollPane estilizado con bordes suaves redondeados
        JScrollPane scrollTabla = new JScrollPane(tablaDeSolicitudes);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        panelParaTabla.add(scrollTabla, BorderLayout.CENTER);
        
        // Etiqueta informativa inferior de ayuda al usuario
        JLabel textoDeConsejo = new JLabel("Consejo: Haz doble clic sobre cualquier fila para gestionar la aprobación o rechazo de la solicitud.");
        textoDeConsejo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        textoDeConsejo.setForeground(Color.GRAY);
        textoDeConsejo.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));
        panelParaTabla.add(textoDeConsejo, BorderLayout.SOUTH);
        
        // Ensamblado final al panel del módulo
        panelPrincipalCentrado.add(panelParaTabla, BorderLayout.CENTER);
        
        return panelPrincipalCentrado;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Vista_Solicitudes().setVisible(true));
    }
}