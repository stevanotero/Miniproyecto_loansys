/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Cursor;


public abstract class Vista_Principal extends JFrame {

    //Cosas de la barra lateral
    protected JPanel barralateral;
    private JLabel logo;
    private JLabel NombredelSistema;
    public JButton botonInicio;
    public JButton botonInventario;
    public JButton botonPrestamos;
    public JButton botonDevoluciones;
    public JButton botonReportes;
    public JButton botonNotificaciones;
    public JButton botonUsuarios;
    public JButton botonSolicitudes;
    public JButton botonCerrarSesion;
    // Cosas del panel central
    private JPanel panelContenido;
    private JLabel textoDeBienvenida;
    private JLabel textoDelPanelPrincipal;

    public Vista_Principal() {
        super("Panel principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        crearbarralateral();
        crearPanelContenido();
        add(barralateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    private void crearbarralateral() {
        barralateral = new JPanel();
        barralateral.setPreferredSize(new Dimension(260, getHeight()));
        barralateral.setBackground(new Color(228, 230, 233));
        barralateral.setLayout(new BoxLayout(barralateral, BoxLayout.Y_AXIS));
        barralateral.setBorder(new EmptyBorder(20, 0, 30, 0));

        //encabezado y logo del texto
        JPanel panelDelEncabezado = new JPanel();
        panelDelEncabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelDelEncabezado.setOpaque(false);
        panelDelEncabezado.setMaximumSize(new Dimension(260, 90));
        panelDelEncabezado.setAlignmentX(Component.CENTER_ALIGNMENT);
        Image imgSena = new ImageIcon(getClass().getResource("/proyect_loansys/img/sena.png")).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imgSena));

        //desarrollo del panel superior que esta al lado del logo
        JPanel panelTextos = new JPanel();
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
        panelTextos.setOpaque(false);

        //titulo del texto del titulo del sistema
        NombredelSistema = new JLabel("LoanSys");
        //tipo de letra y asignación del tamaño de letra
        NombredelSistema.setFont(NombredelSistema.getFont().deriveFont(Font.BOLD, 22f));

        //subtitulos debajo del titulo
        JLabel lblSubtitulo1 = new JLabel("Sistema de inventario");
        lblSubtitulo1.setFont(lblSubtitulo1.getFont().deriveFont(Font.PLAIN, 11f));
        JLabel lblSubtitulo2 = new JLabel("y préstamo");
        lblSubtitulo2.setFont(lblSubtitulo2.getFont().deriveFont(Font.PLAIN, 11f));

        //agregar textos del panel
        panelTextos.add(NombredelSistema);
        panelTextos.add(lblSubtitulo1);
        panelTextos.add(lblSubtitulo2);
        panelDelEncabezado.add(logo);
        panelDelEncabezado.add(panelTextos);
        barralateral.add(panelDelEncabezado);
        barralateral.add(Box.createVerticalStrut(40)); // Espacio antes del menu

        //Los botones de la barra lateral
        botonInicio = crearBotonMenu("Inicio");
        botonInventario = crearBotonMenu("Inventario");
        botonPrestamos = crearBotonMenu("Préstamos");
        botonDevoluciones = crearBotonMenu("Devoluciones");
        botonReportes = crearBotonMenu("Reportes");
        botonNotificaciones = crearBotonMenu("Notificaciones");
        botonUsuarios = crearBotonMenu("Usuarios");
        botonSolicitudes = crearBotonMenu("Solicitudes");

        //separacion de los botones agregandolo al sistema con buena distribucion
        int espacioBotones = 12;
        barralateral.add(botonInicio);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonInventario);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonPrestamos);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonDevoluciones);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonReportes);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonNotificaciones);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonUsuarios);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(botonSolicitudes);
        barralateral.add(Box.createVerticalGlue()); //el boton de cerrar sesion se envia hacia abajo y se agrega

        // crear metodo de redondes para aplicar su forma al boton de cerrrar sesion
        botonCerrarSesion = new JButton("Cerrar sesión") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(241, 107, 97));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        //establecer el color, el tipo de letra y lo centrado al boton con su tamaño asignado
        botonCerrarSesion.setFont(botonCerrarSesion.getFont().deriveFont(Font.BOLD, 13f));
        botonCerrarSesion.setForeground(Color.WHITE);
        botonCerrarSesion.setHorizontalAlignment(SwingConstants.CENTER);
        botonCerrarSesion.setContentAreaFilled(false);
        botonCerrarSesion.setBorderPainted(false);
        botonCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //se le establece el tamaño y el centrado al boton de cerrar sesion
        Dimension btnSize = new Dimension(220, 42);
        botonCerrarSesion.setPreferredSize(btnSize);
        botonCerrarSesion.setMinimumSize(btnSize);
        botonCerrarSesion.setMaximumSize(btnSize);
        botonCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        barralateral.add(botonCerrarSesion);
    }

    //metodo para crear el panel de contenido
    private void crearPanelContenido() {
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(Color.WHITE);

        JPanel barraDeArriba = new JPanel();
        barraDeArriba.setBackground(new Color(218, 220, 224));
        barraDeArriba.setPreferredSize(new Dimension(getWidth(), 80));
        barraDeArriba.setLayout(new BoxLayout(barraDeArriba, BoxLayout.Y_AXIS));
        barraDeArriba.setBorder(new EmptyBorder(15, 30, 15, 30));

        textoDeBienvenida = new JLabel("¡Bienvenido, Asesor!");
        textoDeBienvenida.setFont(textoDeBienvenida.getFont().deriveFont(Font.BOLD, 22f));

        textoDelPanelPrincipal = new JLabel("Panel principal");
        textoDelPanelPrincipal.setFont(textoDelPanelPrincipal.getFont().deriveFont(Font.PLAIN, 12f));
        textoDelPanelPrincipal.setForeground(new Color(120, 120, 120));

        barraDeArriba.add(textoDeBienvenida);
        barraDeArriba.add(Box.createVerticalStrut(5));
        barraDeArriba.add(textoDelPanelPrincipal);
        panelContenido.add(barraDeArriba, BorderLayout.NORTH);

        JPanel panelCentroPersonalizado = crearPanelCentro();
        if (panelCentroPersonalizado != null) {
            panelContenido.add(panelCentroPersonalizado, BorderLayout.CENTER);
        }
    }

    //metodo para aplicar los bordes redondos de los botones
    private JButton crearBotonMenu(String texto) {
        JButton botondelmenu = new JButton(texto);

        // Configuramos la apariencia
        botondelmenu.setFont(botondelmenu.getFont().deriveFont(Font.PLAIN, 14f));
        botondelmenu.setForeground(Color.BLACK);
        botondelmenu.setHorizontalAlignment(SwingConstants.LEFT);

        // Bordes y comportamiento
        botondelmenu.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        botondelmenu.setContentAreaFilled(false);
        botondelmenu.setBorderPainted(false);
        botondelmenu.setFocusPainted(false);
        botondelmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //tamaño de los botones
        Dimension btnSize = new Dimension(220, 42);
        botondelmenu.setPreferredSize(btnSize);
        botondelmenu.setMinimumSize(btnSize);
        botondelmenu.setMaximumSize(btnSize);
        botondelmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botondelmenu;
    }

    public void setTextoBienvenida(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            textoDeBienvenida.setVisible(false);
        } else {
            textoDeBienvenida.setText(texto);
            textoDeBienvenida.setVisible(true);
        }
    }

    public void setTextoModulo(String texto) {
        textoDelPanelPrincipal.setText(texto);
    }

    protected abstract JPanel crearPanelCentro();
}
