/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Administrador_Registro_de_usuario extends Administrador_Plantilla_Administrador {

    private Container contenedor;
    public JPanel panel5, panel6, panel7, panel8, panel9, panel10, panel11, panel12,panel13,panel14,panel15;
    private GridLayout grid4;
    public JButton activacion_usuario, modificar;
    private JLabel titulo2;
    public DefaultTableModel modelo;
    private JScrollPane miscroll;
    public JTable tabla;
    public JButton botonRegistro;

    public Administrador_Registro_de_usuario() {
        super("Registro_de_usuario");
        contenedor = super.getConteiner();
        panel5 = super.getPanel();
        activacion_usuario = super.getButtonActivacion();
        modificar = super.getButtonModificar();

        if (panel5.getComponentCount() > 0) {
            panel5.removeAll();
        }

        // agregacion de borders y radio, color 
        binicio.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón
        activacion_usuario.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón
        modificar.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));
        gestion_roles.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));

        //---------------------------------------------------------
// creaccion de la tabla y boton para cambiar estado
        botonRegistro = new JButton("   Registrar usuario");
        botonRegistro.setBorder(BorderFactory.createLineBorder(Color.green, 10, true));// Borde redondeado para el botón
        botonRegistro.setBackground(Color.green);
        registrar_usuario.setBorder(BorderFactory.createLineBorder(Color.gray, 10, true));
        registrar_usuario.setBackground(Color.gray);
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ninguna celda se puede editar
            }
        };
        modelo.addColumn("DOCUMENTO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("CORREO");
        modelo.addColumn("ROL");
        modelo.addColumn("ESTADO");
        tabla = new JTable(modelo);
            tabla.getTableHeader().setReorderingAllowed(false);
        miscroll = new JScrollPane(tabla);
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel10 = new JPanel();
        
        panel14 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        panel15 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 800, 0));
        
        panel9 = new JPanel(new BorderLayout());
        panel8 = new JPanel(new BorderLayout());
        panel7 = new JPanel(new BorderLayout());
        panel6 = new JPanel(new BorderLayout());
        panel13 = new JPanel(new BorderLayout());

        grid4 = new GridLayout(2, 1, 1, 1);

        titulo2 = new JLabel(" Registrar usuario");
        titulo2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
     
        panel9 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 3));
        panel9.setBorder(new EmptyBorder(90, 0, 0, 950));
        panel10.setBorder(new EmptyBorder(15, 15, 10, 15));
        
        
        panel14.add(titulo2);
        panel15.add(botonRegistro);
        
        panel7.setSize(new Dimension(200, 100));
        panel8.setSize(new Dimension(400, 500));
        panel8.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel9.add(panel14);
        panel9.add(panel15,BorderLayout.EAST);
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
