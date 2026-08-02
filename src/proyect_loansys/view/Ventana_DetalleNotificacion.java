/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Alexis
 */
public class Ventana_DetalleNotificacion extends JDialog {

    public JTextField textoRemitente;
    public JTextField textoRolRemitente;
    public JTextField textoTipoNotificacion;
    public JTextField textoEstadoLectura;
    public JTextArea areaMensaje;
    public JButton botonMarcarLeido;
    public JButton botonCerrar;
    private JLabel lblTitulo;
    private JLabel lblRemitente;
    private JLabel lblRol;
    private JLabel lblTipo;
    private JLabel lblEstado;
    private JLabel lblMensaje;
    private JPanel panelPrincipal;
    private JScrollPane scrollMensaje;

    public Ventana_DetalleNotificacion(Frame padre) {
        super(padre, true);
        initComponents();
        setTitle("Detalles de la Notificación");
        setSize(450, 440);
        setLocationRelativeTo(padre);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);

        lblTitulo = new JLabel("DETALLE DE LA NOTIFICACIÓN");

        lblRemitente = new JLabel("Enviado por:");
        textoRemitente = crearCampoRedondo();

        lblRol = new JLabel("Rol:");
        textoRolRemitente = crearCampoRedondo();

        lblTipo = new JLabel("Tipo:");
        textoTipoNotificacion = crearCampoRedondo();

        lblEstado = new JLabel("Estado:");
        textoEstadoLectura = crearCampoRedondo();

        lblMensaje = new JLabel("Mensaje:");
        areaMensaje = new JTextArea();
        areaMensaje.setEditable(false);
        areaMensaje.setLineWrap(true);
        areaMensaje.setWrapStyleWord(true);
        areaMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        areaMensaje.setForeground(new Color(60, 60, 60));
        areaMensaje.setBackground(new Color(245, 246, 248));

        scrollMensaje = new JScrollPane(areaMensaje);
        scrollMensaje.setBorder(BorderFactory.createLineBorder(new Color(215, 215, 215), 1, true));

        // Botones estilizados
        botonMarcarLeido = crearBotonRedondo("Marcar como Leído", new Color(46, 204, 113), Color.WHITE);
        botonCerrar = crearBotonRedondo("Cerrar", new Color(230, 235, 240), Color.BLACK);

        panelPrincipal.setLayout(null);

        // Título de la ventana
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setBounds(95, 15, 280, 25);
        panelPrincipal.add(lblTitulo);

        // Remitente
        lblRemitente.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblRemitente.setBounds(40, 55, 130, 25);
        textoRemitente.setBounds(180, 55, 220, 25);
        panelPrincipal.add(lblRemitente);
        panelPrincipal.add(textoRemitente);

        // Rol del Remitente
        lblRol.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblRol.setBounds(40, 90, 130, 25);
        textoRolRemitente.setBounds(180, 90, 220, 25);
        panelPrincipal.add(lblRol);
        panelPrincipal.add(textoRolRemitente);

        // Tipo de Notificación
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTipo.setBounds(40, 125, 130, 25);
        textoTipoNotificacion.setBounds(180, 125, 220, 25);
        panelPrincipal.add(lblTipo);
        panelPrincipal.add(textoTipoNotificacion);

        // Estado de Lectura
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setBounds(40, 160, 130, 25);
        textoEstadoLectura.setBounds(180, 160, 220, 25);
        panelPrincipal.add(lblEstado);
        panelPrincipal.add(textoEstadoLectura);

        // Mensaje
        lblMensaje.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMensaje.setBounds(40, 195, 130, 25);
        scrollMensaje.setBounds(180, 195, 220, 85);
        panelPrincipal.add(lblMensaje);
        panelPrincipal.add(scrollMensaje);

        // Posicionamiento de Botones
        botonMarcarLeido.setBounds(40, 310, 190, 35);
        panelPrincipal.add(botonMarcarLeido);

        botonCerrar.setBounds(250, 310, 150, 35);
        panelPrincipal.add(botonCerrar);

        getContentPane().add(panelPrincipal);
    }

    private JTextField crearCampoRedondo() {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(245, 246, 248));
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
        campo.setEditable(false);
        campo.setOpaque(false);
        campo.setFont(new Font("Arial", Font.PLAIN, 12));
        campo.setForeground(new Color(60, 60, 60));
        campo.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        return campo;
    }

    private JButton crearBotonRedondo(String texto, Color fondo, Color textoColor) {
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
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }
}
