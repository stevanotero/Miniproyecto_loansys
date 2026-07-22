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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;

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
        titulo = new JLabel("Sistema de prestamos SENA");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //El logo del sena
        Image imgSena = new ImageIcon(getClass().getResource("/proyect_loansys/img/sena.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imgSena));
        //AlignmentX permite que este en el centro horizontalmente en el fondo
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //EL subtitulo para sugerirle al usuario
        subtitulo = new JLabel("Iniciar sesión al sistema");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 24));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Campos para sugerir lo que se ingresa
        lDocumento = crearLabel("Ingrese el número de documento");
        lContraseña = crearLabel("Ingrese la contraseña");

        // Campos de texto 
        textoDelDocumento = crearCampo();
        textoDeLaContraseña = crearPassword();

        // Botones 
        botonLogin = crearBoton("Iniciar sesión", new Color(34, 139, 34), Color.WHITE);
        botonOlvidar = crearBoton("¿Olvidaste tu contraseña?", new Color(169, 173, 177), Color.BLACK);
        botonRegistrar = crearBoton("Regístrate aquí", new Color(112, 117, 113), Color.WHITE);
        
        botonLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonOlvidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        //Limitar solo numeros  en el campo de contraseña mediante un metodo
        textoDelDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char tecla = e.getKeyChar(); // Averigua qué tecla presiono el usuario

                // Si la tecla no es un numero del 0 al 9 se cancela la accion
                if (!Character.isDigit(tecla)) {
                    e.consume(); // No permite que se ingrese la letra
                }
            }
        });

        // Todo el orden final que se va agregar al campo de la tarjeta
        // El createVerticalStrut permite poner el espacio entre lo que esta dentro del panel de la tarjeta
        card.add(Box.createVerticalStrut(20));
        card.add(titulo);
        card.add(Box.createVerticalStrut(20));
        card.add(logo);
        card.add(Box.createVerticalStrut(20));
        card.add(subtitulo);
        card.add(Box.createVerticalStrut(30));

        // El documento
        card.add(lDocumento);                 // El subtitulo del documento
        card.add(Box.createVerticalStrut(6));  // El separador invisible
        card.add(textoDelDocumento);          // Ponemos el campo redondo
        card.add(Box.createVerticalStrut(20)); // Espacio grande para separar del siguiente bloque

        // La contrasñea
        card.add(lContraseña);                // El subtitulo en la contraseña
        card.add(Box.createVerticalStrut(6));  // el separador invisible
        card.add(textoDeLaContraseña);        // Se pone el campo redondo
        card.add(Box.createVerticalStrut(35)); // Espacio grande para separar del siguiente bloque

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

    //Metodo para redonder el los campos a ingresar
    private JTextField crearCampo() {
        JTextField campo = new JTextField() {
            //Se establece que el que pintado lo hacel mismo usuario con
            @Override
            protected void paintComponent(Graphics g) {
                //Clonamos la brocha y se convierte en un herramientra avanzada ya que al convertirlo a 2d permite realizar mas cosas
                Graphics2D g2 = (Graphics2D) g.create();
                //Se activa el Anti-Aliasing. Esto suaviza los bordes para que las curvas no se vean pixeladas
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                //se define como de rendondo van a estar las esquinas de los campos
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                //Se suaviza el color de los bordes
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                //se le pone al g2 el color gris 
                g2.setColor(new Color(215, 215, 215));
                //Los ultimos dos números (10, 10) dicen qué tan redondas son las esquinas.
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
            }
        };

        //Se pone la caja original en java invisible
        campo.setOpaque(false);
        //se limita el tamaño de los campos
        campo.setMaximumSize(new Dimension(350, 45));
        //se le establece el tipo de letra
        campo.setFont(new Font("Arial", Font.PLAIN, 15));
        //Para que no este pegada la informacion ingresada a los bordes de los campos
        campo.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        //se entra el campo de texto ya terminado
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

    //se establece los colores de los botones y el texto del mimso
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
        boton.setForeground(textoColor); //aplica el color asignado a las letras
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setMaximumSize(new Dimension(350, 45));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return boton;
    }
}
