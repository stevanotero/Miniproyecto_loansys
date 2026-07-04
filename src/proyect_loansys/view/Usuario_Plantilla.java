/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public abstract class Usuario_Plantilla extends JFrame {

    private JPanel panel1, panel2, panel3, panel4;
    private Container contenedor;
    private JLabel lnombreLogo, lnombrelogo2, ltiInve, espacio, espacio2, espacio3,
            espacio4, lbienvp, rolp, nombrep, ldescu, lbienvp4;

    private JLabel espacios1, espacios2, espacios3, espacios4, espacios5, espacios6,
            espacios7, espacios8, espacios9, espacios10, espacios11, espacios12;

    public JButton iniciod, prestamo, notificacion, cerrarS, inventario;

    private GridLayout grid1, grid2, grid3;
    private FlowLayout miflow1;
    private String rol;
    private String nombre;
    private String titulo;

    public Usuario_Plantilla(String titulo, String rol, String nombre) {

        super(titulo);
        /////////////////1///////////////////////////////////
        contenedor = getContentPane();
        miflow1 = new FlowLayout();
        contenedor.setLayout(miflow1);
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        grid1 = new GridLayout(2, 1, 10, 2);
        panel1.setLayout(grid1);
        panel1.setBackground(Color.decode("#D1D1D6"));

        Icon imagen1 = new ImageIcon("SenaCalidadsinfondo.png");
        lnombreLogo = new JLabel(imagen1, SwingConstants.NORTH_EAST);
        lnombrelogo2 = new JLabel("");
        ltiInve = new JLabel("");
        espacio = new JLabel("");
        lbienvp = new JLabel("     LoanSys" + " " + "Sistema de prestamo");
        lbienvp.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        this.rol = rol;
        this.nombre = nombre;
        rolp = new JLabel("¡Bienvenido," + "  " + "[" + rol + "]" + "  " + "[" + nombre + "!]");
        nombrep = new JLabel("");
        espacio2 = new JLabel("");

        panel1.add(lnombreLogo);
        lnombreLogo.setForeground(Color.black);
        panel1.add(lnombrelogo2);
        lnombrelogo2.setForeground(Color.black);
        panel1.add(ltiInve);
        ltiInve.setForeground(Color.black);
        panel1.add(espacio);
        espacio.setForeground(Color.black);
        panel1.add(lbienvp);
        lbienvp.setForeground(Color.black);
        panel1.add(rolp);
        rolp.setForeground(Color.black);
        panel1.add(nombrep);
        nombrep.setForeground(Color.black);
        panel1.add(espacio2);
        espacio2.setForeground(Color.black);
        contenedor.setLayout(new BorderLayout());

        contenedor.add(panel1, BorderLayout.NORTH);

        //-----------------------2----------------------//
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(235, 1000));
        panel2.setLayout(new BorderLayout());

        ldescu = new JLabel("Descubrir", SwingConstants.CENTER);

        Icon imagen2 = new ImageIcon("iconscasa24.png");
        iniciod = new JButton("Inicio", imagen2);

        Icon imagen3 = new ImageIcon("iconsinventario.png");
        inventario = new JButton("Inventario", imagen3);

        Icon imagen4 = new ImageIcon("iconspréstamo24.png");
        prestamo = new JButton("Historial de Prestamo", imagen4);

        Icon imagen5 = new ImageIcon("iconsnotificacion24.png");
        notificacion = new JButton("Notificación", imagen5);

        espacios2 = new JLabel("");
        espacios3 = new JLabel("");
        espacios4 = new JLabel("");
        espacios5 = new JLabel("");
        espacios6 = new JLabel("");
        espacios7 = new JLabel("");
        espacios8 = new JLabel("");
        cerrarS = new JButton("Cerrar Sesión");

        panel3 = new JPanel();

        //grid3 = new GridLayout(2,1,1,1);
        panel3.setBackground(Color.decode("#D1D1D6")); //Cambiar el color al menu

        grid2 = new GridLayout(15, 1, 5, 10);

        panel3.setLayout(grid2);
        panel3.add(ldescu);
        ldescu.setForeground(Color.black);
        ldescu.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        panel3.add(iniciod);
        iniciod.setForeground(Color.black);
        iniciod.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        iniciod.setBackground(Color.decode("#D1D1D6")); //ABABDA

        panel3.add(inventario);
        inventario.setForeground(Color.black);
        inventario.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        inventario.setBackground(Color.decode("#D1D1D6"));

        panel3.add(prestamo);
        prestamo.setForeground(Color.black);
        prestamo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        prestamo.setBackground(Color.decode("#D1D1D6"));

        panel3.add(notificacion);
        notificacion.setForeground(Color.black);
        notificacion.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        notificacion.setBackground(Color.decode("#D1D1D6"));

        panel3.add(espacios2);
        panel3.add(espacios3);
        panel3.add(espacios4);
        panel3.add(espacios5);
        panel3.add(espacios6);
        panel3.add(espacios7);
        panel3.add(espacios8);
        panel3.add(cerrarS);
        cerrarS.setForeground(Color.white);
        cerrarS.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        cerrarS.setBackground(Color.decode("#F36E67"));

        panel2.add(panel3, BorderLayout.CENTER);

        contenedor.add(panel2, BorderLayout.WEST);
        //////////////////3/////////////////////////////////
        ///
        panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(100, 70));
        //panel4.setLayout(new BorderLayout());
        //panel4.setLayout(grid3);
        panel4.setBackground(Color.WHITE);
        contenedor.add(panel4);

    }

    public Container getContainer() {
        return this.contenedor;
    }

    public JPanel getPanel() {
        return this.panel4;
    }

}
