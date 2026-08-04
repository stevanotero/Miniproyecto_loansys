/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alexis
 */
public class VentanaObservacionesDevolucion extends JDialog {

    public JTextArea textoObservaciones;
    public JButton botonConfirmar;
    public JButton botonCancelar;
    public JComboBox<String> estadoElemento;
    private JLabel lblInstruccion;
    private JLabel textoEstado;
    private JPanel panelPrincipal;

    public VentanaObservacionesDevolucion(Frame padre) {
        super(padre, true);
        initComponents();
        setTitle("Observaciones de la Devolución");
        setSize(400, 330); // Tamaño ajustado para dar espacio al ComboBox
        setLocationRelativeTo(padre);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setLayout(null); // Diseño libre / absoluto

        // Label para el estado
        textoEstado = new JLabel("Estado de entrega:");
        textoEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        textoEstado.setBounds(25, 15, 335, 20);
        panelPrincipal.add(textoEstado);

        // JComboBox con las opciones actualizadas
        estadoElemento = new JComboBox<>(new String[]{
            "Buen estado",
            "Con novedad",
            "Ligeros daños",
            "Dañado"
        });
        estadoElemento.setBounds(25, 38, 335, 32);
        estadoElemento.setBackground(Color.WHITE);
        estadoElemento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelPrincipal.add(estadoElemento);

        // Label para las observaciones
        lblInstruccion = new JLabel("Ingrese las observaciones del estado del elemento:");
        lblInstruccion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblInstruccion.setBounds(25, 80, 335, 20);
        panelPrincipal.add(lblInstruccion);

         // Creamos el JTextArea redondeado personalizado
        textoObservaciones = crearAreaRedonda();

         // Configuramos el JScrollPane para que respete la transparencia del JTextArea
        JScrollPane scroll = new JScrollPane(textoObservaciones);
        scroll.setBounds(25, 105, 335, 110);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        panelPrincipal.add(scroll);

        // Botones circulares con los colores del flujo original
        botonConfirmar = crearBotonRedondo("Confirmar", new Color(52, 152, 219), Color.WHITE);
        botonCancelar = crearBotonRedondo("Cancelar", new Color(230, 235, 240), Color.BLACK);

        botonConfirmar.setBounds(45, 230, 140, 35);
        panelPrincipal.add(botonConfirmar);

        botonCancelar.setBounds(200, 230, 140, 35);
        panelPrincipal.add(botonCancelar);

        getContentPane().add(panelPrincipal);
    }

    private JTextArea crearAreaRedonda() {
        JTextArea area = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(245, 246, 248));
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(215, 215, 215));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 12, 12);
                g2.dispose();
            }
        };
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false); 
        area.setFont(new Font("Arial", Font.PLAIN, 13));
        area.setForeground(new Color(60, 60, 60));
        area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        return area;
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