/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Usuario_Inicio extends Usuario_Plantilla{
    private Container contenedor;
    private JPanel panel4,panel5,panel6;
    private JLabel bienve4;
    private GridLayout grid4;
    private JLabel espacios1,espacios2,espacios3,espacios4,espacios5,espacios6,
            espacios7,espacios8,espacios9,espacios10,espacios11,espacios12;
    
    
    
    public Usuario_Inicio(String titulo, String rol, String nombre) {
        super(titulo, rol, nombre);
        contenedor = super.getContainer();
        panel4 = super.getPanel();
        
        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(800, 800));
        bienve4 = new JLabel("Bienvenido al Sistema de Prestamos de equipo",SwingConstants.CENTER);
//       
        panel5.setLayout(new BorderLayout());
    
        
        bienve4.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,30));
        panel5.setBackground(Color.white);
        panel5.setLayout(new BorderLayout());
        panel5.add(bienve4, BorderLayout.NORTH);
        /////////////////////////////////////////////////////////
        panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(800, 600));
        Icon imagen4 = new ImageIcon("logosenagrande.png");
        espacios2 = new JLabel(imagen4,SwingConstants.CENTER);
        panel6.setBackground(Color.white);
        
        panel6.add(espacios2);
        panel5.add(panel6);
        
        
        
       
        panel4.add(panel5, BorderLayout.NORTH);
        
        
        
        
    }
}
