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

public class Vista_GestionUsuarios extends Vista_Principal {

    public JTable tablaUsuarios;
    public DefaultTableModel modeloTabla;

    public Vista_GestionUsuarios() {
        super();
        setTextoBienvenida("");
        setTextoModulo("Panel de Usuarios");
    }

    @Override
    protected JPanel crearPanelCentro() {
        JPanel panelPrincipalCentrado = new JPanel(new BorderLayout());
        panelPrincipalCentrado.setBackground(Color.WHITE);
        panelPrincipalCentrado.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel panelParaTabla = new JPanel(new BorderLayout());
        panelParaTabla.setOpaque(false);

        JLabel lbTituloListado = new JLabel("Listado de Usuarios Registrados en el Sistema");
        lbTituloListado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbTituloListado.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        panelParaTabla.add(lbTituloListado, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        modeloTabla.addColumn("ID Login");
        modeloTabla.addColumn("ID Usuario");
        modeloTabla.addColumn("Nombre / Persona");
        modeloTabla.addColumn("Correo Electrónico");
        modeloTabla.addColumn("Fecha Registro");
        modeloTabla.addColumn("Estado Cuenta");
        modeloTabla.addColumn("Estado Mora");

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setRowHeight(40);
        tablaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        tablaUsuarios.setGridColor(new Color(230, 230, 233));

        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
        scrollTabla.getViewport().setBackground(Color.WHITE);

        panelParaTabla.add(scrollTabla, BorderLayout.CENTER);

        JLabel textoDeConsejo = new JLabel("Consejo: Haz doble clic sobre cualquier usuario para Activar o Desactivar su acceso al sistema.");
        textoDeConsejo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        textoDeConsejo.setForeground(Color.GRAY);
        textoDeConsejo.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));
        panelParaTabla.add(textoDeConsejo, BorderLayout.SOUTH);

        panelPrincipalCentrado.add(panelParaTabla, BorderLayout.CENTER);

        return panelPrincipalCentrado;
    }
}