/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class Usuario_SolicitarPrestamo extends Usuario_Plantilla{
    private Container contenedor;
    private JPanel panel4,panel5,panel6,panel7,panel8,panel9,panel010,panel011,
            panel012,panel013,panel014,panel015,panel016;
    public JLabel texto1,texto2;
    public JButton volver,solicitar;
    public JTable tabla;
    public DefaultTableModel modelo;
    public JScrollPane miscroll;
    
    public Usuario_SolicitarPrestamo(String titulo, String rol, String nombre){
        
        super(titulo, rol, nombre);
        contenedor = super.getContainer();
        panel4 = super.getPanel();
        
        
        //Panel Principal
        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(1100, 600));
        panel5.setBackground(Color.white);
        
        
        //Primer Panel de mensaje disponible
        panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        panel6.setPreferredSize(new Dimension(1100, 30));
        panel6.setBackground(Color.white);
        
        
        
        panel8 = new JPanel();
        panel8.setPreferredSize(new Dimension(300, 30));
        texto1 = new JLabel("",SwingConstants.CENTER);
        panel8.setLayout(new BorderLayout());
        
        panel8.setBackground(Color.white);
        panel8.add(texto1);
        panel6.add(panel8);
        
        ////////////////////////////////////////////////////////////////////////
        //Panel 2 de la tabla
        
        panel7 = new JPanel();
        panel7.setPreferredSize(new Dimension(1100, 350));
        panel7.setBackground(Color.white);
        
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        modelo.addColumn("Codigo Elemento");
        modelo.addColumn("Nombre Elemento");
        modelo.addColumn("Categoria");
        modelo.addColumn("Descripción");
     
        
        
        panel9 = new JPanel();
        panel9.setLayout(new BorderLayout());
        panel9.setPreferredSize(new Dimension(1070, 340));
        
        tabla = new JTable(modelo);
        miscroll=new JScrollPane(tabla);
        
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel9.add(miscroll);
        panel9.setBackground(Color.white);
        
        panel7.add(panel9);
        
       
        ////////////////////////////////////////////////////////////////////////
        // Panel 3 sobre los botones        
        panel011 = new JPanel();
        solicitar = new JButton("solicitar");
        panel011.setPreferredSize(new Dimension(80, 30));
        panel011.setBackground(Color.white);
        panel011.add(solicitar);
        
        panel012 = new JPanel();
        volver = new JButton("Volver");
        panel012.add(volver);
        panel012.setPreferredSize(new Dimension(80, 30));
        panel012.setBackground(Color.white);
        
        
        panel010 = new JPanel();
        panel010.setBackground(Color.white);
        panel010.setPreferredSize(new Dimension(800, 60));
        
        ////////////////////////////////////////////////////////////////////////
        
        //Panel de imagen//
        panel013 = new JPanel();
        panel013.setPreferredSize(new Dimension(400, 100));
        panel013.setBackground(Color.BLACK);
        
        
        
        panel014 = new JPanel();
        texto2 = new JLabel("Imagen");
        panel014.setPreferredSize(new Dimension(120, 90));
        texto2.setForeground(Color.black);
        panel014.setBackground(Color.white);
        panel014.add(texto2);
        
        panel013.add(panel014);
        ////////////////////////////////////////
        panel010.add(panel012);
        panel010.add(panel011);
        //////////////////////////////////////////
        panel5.add(panel6);
        
        panel5.add(panel013);
        panel5.add(panel7,BorderLayout.CENTER);
        panel5.add(panel010);
        
        
        
         panel4.add(panel5);
        
    }
}

