/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

/**
 *
 * @author juans
 */
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Administrador_Inicio_Loansys_Administrador extends Administrador_Plantilla_Administrador {

    private Container contenedor;
    private JPanel panel5, panel6, panel7, panel8, panel9, panel10, panel11, panel12, panel13, panel14, panel15, panel16, panel17;
    private GridLayout grid4;
    private JLabel titulo2;
    public DefaultTableModel modelo;
    private JScrollPane miscroll;
    public JTable tabla;

    public Administrador_Inicio_Loansys_Administrador() {

        super("Inicio");

        contenedor = super.getConteiner();
        panel5 = super.getPanel();

        //  contenido centrar de movimientos de usuario
        modelo = new DefaultTableModel();
        modelo.addColumn("DOCUMENTO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("ACCION");
        modelo.addColumn("FECHA");

        tabla = new JTable(modelo);
        miscroll = new JScrollPane(tabla);
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel10 = new JPanel();

        //distribuccion para los paneles
        panel14 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        panel15 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));
        panel16 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        panel17 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 115, 0));
        panel9 = new JPanel(new BorderLayout());
        panel13 = new JPanel(new BorderLayout());

        panel8 = new JPanel(new BorderLayout());
        panel7 = new JPanel(new BorderLayout());
        panel6 = new JPanel(new BorderLayout());

        grid4 = new GridLayout(2, 1, 1, 1);

        titulo2 = new JLabel("Tabla de  movimientos de Usuarios");
        titulo2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        panel9 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 3));
        panel9.setBorder(new EmptyBorder(90, 5, 0, 950));
        panel10.setBorder(new EmptyBorder(15, 15, 10, 15));

        panel7.setSize(new Dimension(200, 100));
        panel8.setSize(new Dimension(400, 500));
        panel8.setBorder(new EmptyBorder(0, 0, 0, 0));

        panel14.add(titulo2);
         panel9.add(panel14);
           panel9.add(panel15, BorderLayout.EAST);
        panel10.add(miscroll);
        panel7.setBorder(
                BorderFactory.createMatteBorder(
                        0, // arriba
                        0, // izquierda
                        2, // abajo
                        0, // derecha
                        Color.BLACK)
        );
        panel10.setBackground(Color.white);
        panel8.setBackground(Color.white);
        panel7.setBackground(Color.white);
        panel6.setBackground(Color.white);
        panel5.setBackground(Color.white);
        panel5.setLayout(new BorderLayout());
            panel13.add(panel9);
        panel5.add(panel6);
        panel6.add(panel7, BorderLayout.LINE_START);
        panel6.add(panel8, BorderLayout.SOUTH);
        panel7.add(panel9, BorderLayout.LINE_START);
        panel8.add(panel10);

    }
}
