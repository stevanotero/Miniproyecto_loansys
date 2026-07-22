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
 * @author Alexis
 */
public class VentanaAsignarFechas extends JDialog {

    public JButton botonConfirmar;
    public JButton botonCancelar;
    public JTextField textoFechaDevolucion;
    private JLabel lblTitulo;
    private JLabel lblFechaDevolucion;
    private JPanel panelPrincipal;

    public VentanaAsignarFechas(Frame padre) {
        super(padre, true);
        initComponents();
        setTitle("Asignar Fecha de Devolución");
        setSize(450, 260);
        setLocationRelativeTo(padre);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        panelPrincipal.setBackground(Color.WHITE);

        lblTitulo = new JLabel("ASIGNAR FECHA DE DEVOLUCIÓN");

        lblFechaDevolucion = new JLabel("Fecha Límite de Devolución (YYYY-MM-DD):");
        textoFechaDevolucion = crearCampoRedondoEditable();

        // Estilos de botones circulares siguiendo tu paleta de colores
        botonConfirmar = crearBotonRedondo("Confirmar", new Color(40, 167, 69), Color.WHITE);
        botonCancelar = crearBotonRedondo("Cancelar", new Color(230, 235, 240), Color.BLACK);

        panelPrincipal.setLayout(null);

        // Título de la modal
        lblTitulo.setFont(new Font("Segoe UI", 1, 16));
        lblTitulo.setBounds(90, 20, 280, 25);
        panelPrincipal.add(lblTitulo);

        // Etiqueta de la fecha
        lblFechaDevolucion.setFont(new Font("Segoe UI", 1, 13));
        lblFechaDevolucion.setBounds(40, 70, 300, 25);
        panelPrincipal.add(lblFechaDevolucion);

        // Campo de texto para escribir la fecha
        textoFechaDevolucion.setBounds(40, 105, 360, 30);
        panelPrincipal.add(textoFechaDevolucion);

        // Botones de acción
        botonConfirmar.setBounds(75, 165, 140, 35);
        panelPrincipal.add(botonConfirmar);

        botonCancelar.setBounds(235, 165, 140, 35);
        panelPrincipal.add(botonCancelar);

        getContentPane().add(panelPrincipal);
    }

    private JTextField crearCampoRedondoEditable() {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Usamos fondo blanco puro para notar que este campo sí es editable a diferencia de la otra modal
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
        campo.setEditable(true); // Permitimos la escritura del asesor
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
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }
}
