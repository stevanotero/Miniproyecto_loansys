
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import proyect_loansys.view.Vista_Principal;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Image;

/**
 *
 * @author Alexis
 */

public class Vista_Inicio extends Vista_Principal{
    
    public Vista_Inicio(){
    super();
    
    
    }
    @Override
    protected JPanel crearPanelCentro() {
        //logo y organización del panel
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(Color.WHITE);
        panelCentro.add(Box.createVerticalGlue());
        
        // texto del centro con su fuente
        JLabel textoLinea1 = new JLabel("Bienvenido al Sistema de");
        textoLinea1.setFont(textoLinea1.getFont().deriveFont(Font.BOLD, 45f));
        textoLinea1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textoLinea2 = new JLabel("Inventario y Préstamos");
        textoLinea2.setFont(textoLinea2.getFont().deriveFont(Font.BOLD, 45f));
        textoLinea2.setAlignmentX(Component.CENTER_ALIGNMENT);

         // importación de la imagen con su estructura
        Image imgLogoCentro = new ImageIcon(getClass().getResource("/proyect_loansys/img/sena.png"))
        .getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel logoCentro = new JLabel(new ImageIcon(imgLogoCentro));
        logoCentro.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //agregarlo
        panelCentro.add(textoLinea1);
        panelCentro.add(Box.createVerticalStrut(10)); 
        panelCentro.add(textoLinea2);
        panelCentro.add(Box.createVerticalStrut(50));
        panelCentro.add(logoCentro);
        panelCentro.add(Box.createVerticalGlue());

        return panelCentro; 
    }
}

