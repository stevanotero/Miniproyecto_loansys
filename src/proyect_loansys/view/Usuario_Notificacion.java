/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel
 */
public class Usuario_Notificacion extends Usuario_Plantilla{
     private Container contenedor;
    private JPanel panel4, panel5,panel6,panel7,panel8,panel9;
    private JLabel texto1;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JScrollPane miscroll;
    
    

    public Usuario_Notificacion(String titulo, String rol, String nombre) {
        
        super(titulo, rol, nombre);
        contenedor = super.getContainer();
        panel4 = super.getPanel();
        
        panel5 = new JPanel();
        //panel5.setLayout(null);
        panel5.setPreferredSize(new Dimension(1100, 600));
        panel5.setBackground(Color.white);
        
        ////////////////////////////////////////////////////////////////////////
        
        panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(1100, 150));
        panel6.setBackground(Color.white);
        
        
        
        panel8 = new JPanel();
        panel8.setPreferredSize(new Dimension(400, 130));
        texto1 = new JLabel("Notificaciones",SwingConstants.CENTER);
        panel8.setLayout(new BorderLayout());
        
        panel8.setBackground(Color.white);
        panel8.add(texto1);
        panel6.add(panel8);
        
        ////////////////////////////////////////////////////////////////////////
        
        
        panel7 = new JPanel();
        panel7.setPreferredSize(new Dimension(1100, 600));
        panel7.setBackground(Color.white);
        
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Notificador");
        modelo.addColumn("Mensaje");
     
        
        
        panel9 = new JPanel();
        panel9.setLayout(new BorderLayout());
        panel9.setPreferredSize(new Dimension(1070, 420));
        
        tabla = new JTable(modelo);
        miscroll=new JScrollPane(tabla);
        
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel9.add(miscroll);
        panel9.setBackground(Color.white);
        
        panel7.add(panel9);
        
        
        
        
        ////////////////////////////////////////////////////////////////////////
        
        
        //
        //panel5.add(panel8,BorderLayout.CENTER);
        panel5.add(panel6,BorderLayout.NORTH);
        panel5.add(panel7,BorderLayout.CENTER);
        
        panel4.add(panel5);
    }
}
