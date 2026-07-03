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

public class Administrador_Cambio_de_rol extends Administrador_Plantilla_Administrador {
    
   private Container contenedor;
    public JPanel panel5, panel6, panel7, panel8, panel9, panel10, panel11, panel12;
    private GridLayout grid4;
    public JButton activacion_usuario, modificar;
    private JLabel titulo2;
    public DefaultTableModel modelo;
    private JScrollPane miscroll;
    public JTable tabla;
    public JButton botonC;
    
    public Administrador_Cambio_de_rol() {
        super("Cambio_de_rol");
        contenedor = super.getConteiner();
        panel5 = super.getPanel();
        activacion_usuario = super.getButtonActivacion();
        modificar = super.getButtonModificar();
        
        
        if (panel5.getComponentCount() > 0) {
            panel5.removeAll();
        }

        // agregacion de borders y radio, color 
        binicio.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón
        activacion_usuario.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botó
        modificar.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));
        gestion_roles.setBorder(BorderFactory.createLineBorder(Color.gray, 10, true));
        gestion_roles.setBackground(Color.gray);
        
        //---------------------------------------------------------
        
// creaccion de la tabla y boton para cambiar estado
        botonC = new JButton("  Cambiar de rol");
        botonC.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10, true));// Borde redondeado para el botón
        botonC.setBackground(Color.white);

        modelo = new DefaultTableModel();
         modelo.addColumn("DOCUMENTO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
         modelo.addColumn("CORREO");
        modelo.addColumn("ROL");
        modelo.addColumn("ESTADO");
        tabla = new JTable(modelo);
        miscroll = new JScrollPane(tabla);
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      

        panel10 = new JPanel();
        panel9 = new JPanel();
        panel8 = new JPanel(new BorderLayout());
        panel7 = new JPanel(new BorderLayout());
        panel6 = new JPanel(new BorderLayout());

        grid4 = new GridLayout(2, 1, 1, 1);

        titulo2 = new JLabel("  Cambio de rol");
        titulo2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));

        panel9.setBorder(new EmptyBorder(90, 5, 0, 950));
        panel10.setBorder(new EmptyBorder(50, 0, 0, 0));

        panel7.setSize(new Dimension(200, 100));
        panel8.setSize(new Dimension(400, 500));
        panel8.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel9.add(titulo2);
        panel10.add(miscroll);
        panel10.add(botonC, BorderLayout.WEST);
        panel7.setBorder(
                BorderFactory.createMatteBorder(
                        0, // arriba
                        0, // izquierda
                        2, // abajo
                        0, // derecha
                        Color.BLACK)
        );
        panel10.setBackground(Color.white);
        panel9.setBackground(Color.white);
        panel8.setBackground(Color.white);
        panel7.setBackground(Color.white);
        panel6.setBackground(Color.white);
        panel5.setBackground(Color.white);

        panel5.add(panel6);
        panel6.add(panel7, BorderLayout.LINE_START);
        panel6.add(panel8, BorderLayout.SOUTH);
        panel7.add(panel9, BorderLayout.LINE_START);
        panel8.add(panel10);
        
        
    }
    
}
