/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

/**
 *
 * @author Alexis
 */

public class Vista_RestablecerContraseña extends JFrame {

    // Declarar todo lo requerido
    private JPanel fondo;
    private JPanel card;
    private JLabel titulo;
    private JLabel logo;
    private JLabel lCorreo;

    public JTextField textoDelCorreo;
    public JButton botonOlvidar;
    public JButton botonCancelar;

    // Creacion del constructor de la vista
    public Vista_RestablecerContraseña() {
        super("Sistema de prestamos SENA");
        //GridBagLayout permite organizar los componentes en una cuadrícula dinámica de filas y columnas
        fondo = new JPanel(new GridBagLayout());
        // Paneles y el fondo color blanco
        fondo.setBackground(Color.WHITE);

        card = new JPanel();
        //se define el tamaño en pixeles de los que seria la tarjeta
        card.setPreferredSize(new Dimension(700, 650));
        //se pinta igualmente que el color del fondo
        card.setBackground(Color.WHITE);

        //Configura la tarjeta para que todo se organice en fila vertical de arriba pa abajo
        card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));

        // deja un espacio en los bordes
        card.setBorder(new EmptyBorder(30, 60, 30, 60));

        // Texto del titulo
        titulo = new JLabel("Restablecer contraseña");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //El logo del sena
        Image imgSena = new ImageIcon(getClass().getResource("/proyect_loansys/img/candado.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imgSena));
        //AlignmentX permite que este en el centro horizontalmente en el fondo
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Campos para sugerir lo que se ingresa
        lCorreo = crearLabel("Ingrese su correo electrónico");

        // Campos de texto 
        textoDelCorreo = crearCampo();
        

        // Botones con su color y fondo
        botonOlvidar = crearBoton("Restablecer Contraseña", new Color(34, 139, 34), Color.WHITE);
        botonCancelar = crearBoton("Cancelar", new Color(169, 173, 177), Color.BLACK);

        //establecer el cursor punteado
        botonOlvidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


        card.add(Box.createVerticalStrut(80)); 
        card.add(logo);                         
        card.add(Box.createVerticalStrut(20));
        card.add(titulo);                       
        card.add(Box.createVerticalStrut(15));
        
        card.add(lCorreo);                    
        card.add(Box.createVerticalStrut(6));  
        card.add(textoDelCorreo);             
        card.add(Box.createVerticalStrut(20)); 
        

        card.add(botonOlvidar);
        card.add(Box.createVerticalStrut(20));
        card.add(botonCancelar);

        fondo.add(card);
        add(fondo);
    }

    // Metodos de diseño para poner los textos y los botones redondos
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    //Metodo para redonder el los campos a ingresar
    private JTextField crearCampo() {
        JTextField campo = new JTextField() {
            //Se establece que el que pintado lo hacel mismo usuario con
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

        campo.setOpaque(false);
        campo.setMaximumSize(new Dimension(350, 45));
        campo.setFont(new Font("Arial", Font.PLAIN, 15));
        campo.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        return campo;
    }

    //lo mismo pero con el campo de la contraseña
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
        return pass;
    }

    //Se establece los colores de los botones y el texto del mimso
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
