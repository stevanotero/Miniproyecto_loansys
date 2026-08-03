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
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Alexis
 */
public class Vista_Login extends JFrame {

    // Declarar todo lo requerido
    private JPanel fondo;
    private JPanel card;
    private JLabel titulo;
    private JLabel logo;
    private JLabel subtitulo;
    private JLabel lDocumento;
    private JLabel lCorreo;
    private JLabel lOlvidarContraseña;
    private JLabel lContraseña;
    public JTextField textoDelDocumento;
    public JPasswordField textoDeLaContraseña;
    public JButton botonRegistrar;
    public JButton botonLogin;
    public JButton botonOlvidar;

    // Creacion del constructor de la vista
    public Vista_Login() {
        super("Sistema de prestamos SENA");
        fondo = new JPanel(new GridBagLayout());
        fondo.setBackground(Color.WHITE);

        card = new JPanel();
        card.setPreferredSize(new Dimension(700, 650));
        card.setBackground(Color.WHITE);
        card.setLayout(new javax.swing.BoxLayout(card, javax.swing.BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(30, 60, 30, 60));

        // Texto del titulo
        titulo = new JLabel("Sistema de prestamos SENA");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //El logo del sena
        System.out.println(getClass().getName());
        System.out.println(getClass().getClassLoader());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(getClass().getResource(""));
        System.out.println(getClass().getResource("/proyect_loansys/img/sena.png"));
        Image imgSena = new ImageIcon(getClass().getResource("/proyect_loansys/img/sena.png")).getImage().
                getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imgSena));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        

        //EL subtitulo para sugerirle al usuario
        subtitulo = new JLabel("Iniciar sesión al sistema");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 24));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        

        //Campos para sugerir lo que se ingresa
        lDocumento = crearLabel("Ingrese el número de documento");
        lContraseña = crearLabel("Ingrese la contraseña");
        
        

        // Campos de texto con placeholder texto de sugerencia en espacio vacío
        textoDelDocumento = crearCampo("Ej: 10001234567");
        textoDeLaContraseña = crearPassword();

        // Botones 
        botonLogin = crearBoton("Iniciar sesión", new Color(34, 139, 34), Color.WHITE);
        botonOlvidar = crearBoton("¿Olvidaste tu contraseña?", new Color(169, 173, 177), Color.BLACK);
        botonRegistrar = crearBoton("Regístrate aquí", new Color(112, 117, 113), Color.WHITE);

        botonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonOlvidar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Limitar a solo números Y a un máximo de 11 caracteres
        textoDelDocumento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char tecla = e.getKeyChar(); 
                if (!Character.isDigit(tecla) || textoDelDocumento.getText().length() >= 11) {
                    e.consume(); 
                }
            }
        });

        // Todo el orden final que se va agregar al campo de la tarjeta
        card.add(Box.createVerticalStrut(20));
        card.add(titulo);
        card.add(Box.createVerticalStrut(20));
        card.add(logo);
        card.add(Box.createVerticalStrut(20));
        card.add(subtitulo);
        card.add(Box.createVerticalStrut(30));

        // El documento
        card.add(lDocumento);                
        card.add(Box.createVerticalStrut(6));  
        card.add(textoDelDocumento);          
        card.add(Box.createVerticalStrut(20)); 

        // La contraseña
        card.add(lContraseña);                
        card.add(Box.createVerticalStrut(6));  
        card.add(textoDeLaContraseña);        
        card.add(Box.createVerticalStrut(35)); 

        card.add(botonLogin);
        card.add(Box.createVerticalStrut(20));
        card.add(botonOlvidar);
        card.add(Box.createVerticalStrut(20));
        card.add(botonRegistrar);

        fondo.add(card);
        add(fondo);
    }

    // Metodos de diseño para poner los textos y los botones redondos
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    private JTextField crearCampo(String placeholder) {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
                super.paintComponent(g);

                
                if (getText().isEmpty() && !hasFocus()) {
                    Graphics2D gPlaceholder = (Graphics2D) g.create();
                    gPlaceholder.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    gPlaceholder.setColor(new Color(170, 170, 170)); 
                    gPlaceholder.setFont(getFont().deriveFont(Font.ITALIC));
                    int paddingLeft = 14;
                    int baseline = (getHeight() - gPlaceholder.getFontMetrics().getHeight()) / 2 + gPlaceholder.getFontMetrics().getAscent();
                    gPlaceholder.drawString(placeholder, paddingLeft, baseline);
                    gPlaceholder.dispose();
                }
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

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                campo.repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                campo.repaint();
            }
        });

        campo.setOpaque(false);
        campo.setMaximumSize(new Dimension(350, 45));
        campo.setFont(new Font("Arial", Font.PLAIN, 15));
        campo.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        return campo;
    }

    // Mismo método para el campo de la contraseña
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

    // Configuración de estilo para botones
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