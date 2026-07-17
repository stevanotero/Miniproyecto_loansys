/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juans
 */
public abstract class Administrador_Plantilla_Administrador extends JFrame {

    private Container contenedor;
    private JPanel panel1, panel2, panel5;
    private JLabel titulo, logo, nombre;
    private JLabel subtituloBarraLateral1, subtituloBarraLateral2;
    public JButton binicio, activacion_usuario, modificar, gestion_roles, registrar_usuario, cerrar_sesion;
    public DefaultTableModel modelo;
    private JScrollPane miscroll;
    public JTable tabla;
    private String Nombre_Usuario = "";

    public Administrador_Plantilla_Administrador(String nombre_interfaz) {
        super(nombre_interfaz);

        contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());

     
        panel5 = new JPanel();

        crearBarraSuperior();
        crearBarraLateral();

        contenedor.add(panel1, BorderLayout.NORTH);
        contenedor.add(panel2, BorderLayout.WEST);
        contenedor.add(panel5, BorderLayout.CENTER);
    }


    private void crearBarraSuperior() {
        panel1 = new JPanel();
        panel1.setBackground(new Color(218, 220, 224));
        panel1.setPreferredSize(new Dimension(1400, 80));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(new EmptyBorder(15, 30, 15, 30));

        String textoBienvenida = (Nombre_Usuario == null || Nombre_Usuario.isEmpty())
                ? "¡Bienvenido!"
                : "¡Bienvenido, " + Nombre_Usuario + "!";
        
        nombre = new JLabel(textoBienvenida);
        nombre.setFont(nombre.getFont().deriveFont(Font.BOLD, 22f));



        panel1.add(nombre);
        panel1.add(Box.createVerticalStrut(5));

    }


    // botones de menu en columna y boton de cerrar sesion redondeado
    // pegado abajo.

    private void crearBarraLateral() {
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(260, 1000));
        panel2.setBackground(new Color(228, 230, 233));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBorder(new EmptyBorder(20, 0, 30, 0));

        // Encabezado con logo y titulo del sistema
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelEncabezado.setOpaque(false);
        panelEncabezado.setMaximumSize(new Dimension(260, 90));
        panelEncabezado.setAlignmentX(Component.CENTER_ALIGNMENT);

  
        ImageIcon imagen_portada = new ImageIcon(getClass().getResource("/proyect_loansys/img/logo_sena.png"));
        Image imagen_ajustada = imagen_portada.getImage().getScaledInstance(50, 52, Image.SCALE_SMOOTH);
        Icon portada = new ImageIcon(imagen_ajustada);
        logo = new JLabel(portada);

        JPanel panelTextos = new JPanel();
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
        panelTextos.setOpaque(false);

        titulo = new JLabel("LoanSys");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));

        subtituloBarraLateral1 = new JLabel("Sistema de inventario");
        subtituloBarraLateral1.setFont(subtituloBarraLateral1.getFont().deriveFont(Font.PLAIN, 11f));
        subtituloBarraLateral2 = new JLabel("y préstamo");
        subtituloBarraLateral2.setFont(subtituloBarraLateral2.getFont().deriveFont(Font.PLAIN, 11f));

        panelTextos.add(titulo);
        panelTextos.add(subtituloBarraLateral1);
        panelTextos.add(subtituloBarraLateral2);

        panelEncabezado.add(logo);
        panelEncabezado.add(panelTextos);

        panel2.add(panelEncabezado);
        panel2.add(Box.createVerticalStrut(40));

        // Los botones siguen siendo los MISMOS objetos publicos de siempre,
        // solo que ahora se crean con el estilo de Vista_Principal.
        binicio = crearBotonMenu("🏠︎ Inicio");
        activacion_usuario = crearBotonMenu("👤 Activacion / Usuario");
        modificar = crearBotonMenu("👤 Modificar / Usuario");
        gestion_roles = crearBotonMenu("👤 Gestion de roles");
        registrar_usuario = crearBotonMenu("👤 Registrar Usuario");

        int espacioBotones = 12;
        panel2.add(binicio);
        panel2.add(Box.createVerticalStrut(espacioBotones));
        panel2.add(activacion_usuario);
        panel2.add(Box.createVerticalStrut(espacioBotones));
        panel2.add(modificar);
        panel2.add(Box.createVerticalStrut(espacioBotones));
        panel2.add(gestion_roles);
        panel2.add(Box.createVerticalStrut(espacioBotones));
        panel2.add(registrar_usuario);

        // Esto empuja el boton de cerrar sesion hasta el fondo, igual que en Vista_Principal
        panel2.add(Box.createVerticalGlue());

        cerrar_sesion = new JButton("Cerrar sesión") {
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
        cerrar_sesion.setFont(cerrar_sesion.getFont().deriveFont(Font.BOLD, 13f));
        cerrar_sesion.setForeground(Color.WHITE);
        cerrar_sesion.setHorizontalAlignment(SwingConstants.CENTER);
        cerrar_sesion.setContentAreaFilled(false);
        cerrar_sesion.setBorderPainted(false);
        cerrar_sesion.setFocusPainted(false);
        cerrar_sesion.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension btnSizeCerrar = new Dimension(220, 42);
        cerrar_sesion.setPreferredSize(btnSizeCerrar);
        cerrar_sesion.setMinimumSize(btnSizeCerrar);
        cerrar_sesion.setMaximumSize(btnSizeCerrar);
        cerrar_sesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel2.add(cerrar_sesion);
    }

    // Metodo auxiliar que crea cada boton del menu con el mismo estilo
    // que crearBotonMenu(...) de Vista_Principal.
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(boton.getFont().deriveFont(Font.PLAIN, 14f));
        boton.setForeground(Color.BLACK);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension btnSize = new Dimension(220, 42);
        boton.setPreferredSize(btnSize);
        boton.setMinimumSize(btnSize);
        boton.setMaximumSize(btnSize);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }



    public Container getConteiner() {
        return this.contenedor;
    }

    public JPanel getPanel() {
        return this.panel5;
    }

    public JButton getButtonActivacion() {
        return this.activacion_usuario;
    }

    public JButton getButtonModificar() {
        return this.modificar;
    }


    public void setNombreUsuario(String nombreUsuario) {
        this.Nombre_Usuario = nombreUsuario;
        if (nombre != null) {
            nombre.setText((nombreUsuario == null || nombreUsuario.isEmpty())
                    ? "¡Bienvenido!"
                    : "¡Bienvenido, " + nombreUsuario + "!");
        }
    }
}