/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Alexis
 */
public class Vista_Devoluciones extends Vista_Principal {

    public JTable tablaDevoluciones;
    public DefaultTableModel modeloTabla;

    public Vista_Devoluciones() {
        super(); 
        setTextoBienvenida("");
        setTextoModulo("Panel de Devoluciones"); 
    }

    @Override
    protected JPanel crearPanelCentro() {
        // Panel principal con márgenes limpios y profesionales (Igual a Vista_Prestamo)
        JPanel panelPrincipalCentrado = new JPanel(new BorderLayout());
        panelPrincipalCentrado.setBackground(Color.WHITE);
        panelPrincipalCentrado.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Contenedor interno exclusivo de la tabla
        JPanel panelParaTabla = new JPanel(new BorderLayout());
        panelParaTabla.setOpaque(false);
        
        // Título de la sección
        JLabel lbTituloListado = new JLabel("Listado de Devoluciones Realizadas");
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

        // Estructura de columnas sincronizada con la base de datos (Devoluciones)
        modeloTabla.addColumn("ID Devolución");
        modeloTabla.addColumn("ID Préstamo");
        modeloTabla.addColumn("Elemento");
        modeloTabla.addColumn("Fecha Inicio");
        modeloTabla.addColumn("Fecha Devolución");
        modeloTabla.addColumn("Observaciones");

        // Configuración estética y de altura de filas para la tabla
        tablaDevoluciones = new JTable(modeloTabla);
        tablaDevoluciones.setRowHeight(40);
        tablaDevoluciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaDevoluciones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaDevoluciones.getTableHeader().setReorderingAllowed(false);
        tablaDevoluciones.setGridColor(new Color(230, 230, 233));
        
        // ScrollPane estilizado con bordes suaves redondeados
        JScrollPane scrollTabla = new JScrollPane(tablaDevoluciones);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        panelParaTabla.add(scrollTabla, BorderLayout.CENTER);
        
        // Etiqueta informativa inferior de ayuda al usuario
        JLabel textoDeConsejo = new JLabel("Consejo: Este panel refleja de forma permanente todas las entregas conformes y registros históricos procesados.");
        textoDeConsejo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        textoDeConsejo.setForeground(Color.GRAY);
        textoDeConsejo.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));
        panelParaTabla.add(textoDeConsejo, BorderLayout.SOUTH);
        
        // Ensamblado final al panel del módulo
        panelPrincipalCentrado.add(panelParaTabla, BorderLayout.CENTER);
        
        return panelPrincipalCentrado;
    }
}