/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alexis
 */
public class VentanaGestionPrestamo extends JDialog {

    public JTextField textoIdPrestamo;
    public JTextField textoNombreElemento;
    public JTextField textoUsuario;
    public JTextField textoFechaInicio;
    public JTextField textoFechaDevolucion;

    public JButton botonRegistrarDevolucion;
    public JButton botonCancelar;

    private JLabel lblTitulo;
    private JLabel lblIdPrestamo;
    private JLabel lblElemento;
    private JLabel lblUsuario;
    private JLabel lblFechaInicio;
    private JLabel lblFechaDevolucion;
    private JPanel panelPrincipal;

    public VentanaGestionPrestamo(Frame padre) {
        super(padre, true);
        initComponents();
        setTitle("Gestionar Devolución de Elemento");
        setSize(450, 400); // Ajustado el tamaño ya que son 5 campos en total
        setLocationRelativeTo(padre);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        panelPrincipal.setBackground(Color.WHITE);

        lblTitulo = new JLabel("DETALLES DEL PRÉSTAMO");

        lblIdPrestamo = new JLabel("ID Préstamo:");
        textoIdPrestamo = crearCampoRedondo();

        lblElemento = new JLabel("Elemento:");
        textoNombreElemento = crearCampoRedondo();

        lblUsuario = new JLabel("Usuario:");
        textoUsuario = crearCampoRedondo();

        lblFechaInicio = new JLabel("Hora Inicio:");
        textoFechaInicio = crearCampoRedondo();

        lblFechaDevolucion = new JLabel("Hora Fin:");
        textoFechaDevolucion = crearCampoRedondo();

        // Botones con estilos circulares modernos acordes al flujo
        botonRegistrarDevolucion = crearBotonRedondo("Registrar Devolución", new Color(46, 204, 113), Color.WHITE);
        botonCancelar = crearBotonRedondo("Cancelar", new Color(230, 235, 240), Color.BLACK);

        panelPrincipal.setLayout(null);

        // Título de la ventana modal
        lblTitulo.setFont(new Font("Segoe UI", 1, 16));
        lblTitulo.setBounds(115, 20, 250, 25);
        panelPrincipal.add(lblTitulo);

        // ID del Préstamo
        lblIdPrestamo.setFont(new Font("Segoe UI", 1, 13));
        lblIdPrestamo.setBounds(40, 65, 130, 25);
        textoIdPrestamo.setBounds(180, 65, 220, 25);
        panelPrincipal.add(lblIdPrestamo);
        panelPrincipal.add(textoIdPrestamo);

        // Nombre del Elemento
        lblElemento.setFont(new Font("Segoe UI", 1, 13));
        lblElemento.setBounds(40, 105, 130, 25);
        textoNombreElemento.setBounds(180, 105, 220, 25);
        panelPrincipal.add(lblElemento);
        panelPrincipal.add(textoNombreElemento);

        // Nombre del Usuario
        lblUsuario.setFont(new Font("Segoe UI", 1, 13));
        lblUsuario.setBounds(40, 145, 130, 25);
        textoUsuario.setBounds(180, 145, 220, 25);
        panelPrincipal.add(lblUsuario);
        panelPrincipal.add(textoUsuario);

        // Fecha y Hora de Inicio
        lblFechaInicio.setFont(new Font("Segoe UI", 1, 13));
        lblFechaInicio.setBounds(40, 185, 130, 25);
        textoFechaInicio.setBounds(180, 185, 220, 25);
        panelPrincipal.add(lblFechaInicio);
        panelPrincipal.add(textoFechaInicio);

        // Fecha y Hora Límite de Devolución
        lblFechaDevolucion.setFont(new Font("Segoe UI", 1, 13));
        lblFechaDevolucion.setBounds(40, 225, 130, 25);
        textoFechaDevolucion.setBounds(180, 225, 220, 25);
        panelPrincipal.add(lblFechaDevolucion);
        panelPrincipal.add(textoFechaDevolucion);

        // Posicionamiento de Botones
        botonRegistrarDevolucion.setBounds(40, 290, 190, 35);
        panelPrincipal.add(botonRegistrarDevolucion);
        botonCancelar.setBounds(250, 290, 150, 35);
        panelPrincipal.add(botonCancelar);

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
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
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
