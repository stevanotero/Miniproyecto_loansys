/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

/**
 *
 * @author Alexis
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;

public class Vista_NuevaContraseña extends JFrame {
    //Declarar todo lo requerido
    private JPanel fondo;
    private JPanel card;
    private JLabel titulo;
    private JLabel logo;
    private JLabel lnuevaContraseña;
    private JLabel lconfirmarContraseña;
    public JPasswordField ingresarNuevaContraseña;
    public JPasswordField confirmarContraseña;
    public JButton botonGuardar;
    public JButton botonCancelar;

    //creacion del constructor de la vista
    public Vista_NuevaContraseña() {
        super("Sistema de prestamos SENA");
        
        fondo = new JPanel(new GridBagLayout());
        fondo.setBackground(Color.WHITE);

        card = new JPanel();
        card.setPreferredSize(new Dimension(700, 650));
        card.setBackground(Color.WHITE);

        card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(30, 60, 30, 60));

      
        titulo = new JLabel("Nueva contraseña");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        Image imgSena = new ImageIcon(getClass().getResource("/proyect_loansys/img/candadoAbierto.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imgSena));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lnuevaContraseña = crearLabel("Ingresa tu nueva contraseña");
        ingresarNuevaContraseña = crearPassword(); 
        lconfirmarContraseña = crearLabel("Confirma tu contraseña");
        confirmarContraseña = crearPassword(); 

        // Botones de acción con su informacion
        botonGuardar = crearBoton("Guardar cambios", new Color(34, 139, 34), Color.WHITE);
        botonCancelar = crearBoton("Cancelar", new Color(169, 173, 177), Color.BLACK);

        botonGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Organisar todo como el logo despues el titulo
        card.add(Box.createVerticalStrut(40)); 
        card.add(logo);                             
        card.add(Box.createVerticalStrut(15));
        card.add(titulo);                           
        card.add(Box.createVerticalStrut(25));
        
        //Agregar la primera sugerencia que se va a poner y el campo
        card.add(lnuevaContraseña);                 
        card.add(Box.createVerticalStrut(6));       
        card.add(ingresarNuevaContraseña);          
        card.add(Box.createVerticalStrut(20));      
        
        //Agregar los label que sugiere que se va a poner
        card.add(lconfirmarContraseña);             
        card.add(Box.createVerticalStrut(6));       
        card.add(confirmarContraseña);              
        card.add(Box.createVerticalStrut(35));      
        
        //Agregar botones
        card.add(botonGuardar);
        card.add(Box.createVerticalStrut(15));
        card.add(botonCancelar);

        fondo.add(card);
        add(fondo);
    }
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    // Metodo para redondear los campos de contraseña
    private JPasswordField crearPassword() {
        JPasswordField pass = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(215, 215, 215));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
            }
        };

        pass.setOpaque(false);
        pass.setMaximumSize(new Dimension(350, 45));
        pass.setFont(new Font("Arial", Font.PLAIN, 15));
        pass.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        pass.setAlignmentX(Component.CENTER_ALIGNMENT); 
        return pass;
    }

    // Establece los colores de los botones y el diseño redondeado de todos
    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setBackground(fondo);
        boton.setForeground(textoColor); 
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setMaximumSize(new Dimension(350, 45));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return boton;
    }
}