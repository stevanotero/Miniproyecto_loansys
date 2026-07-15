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
import javax.swing.JTextArea;

/**
 * @author Alexis
 */
public class VentanaMotivoRechazo extends JDialog {

    public JButton botonConfirmarRechazo;
    public JButton botonCancelar;
    public JTextArea areaMotivoRechazo;
    private JLabel lblTitulo;
    private JLabel lblMotivo;
    private JPanel panelPrincipal;

    public VentanaMotivoRechazo(Frame padre) {
        super(padre, true); 
        initComponents();
        setTitle("Rechazar Solicitud de Elemento");
        setSize(450, 320); 
        setLocationRelativeTo(padre);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        
        lblTitulo = new JLabel("MOTIVO DEL RECHAZO");
        
        lblMotivo = new JLabel("Diligencie la razón por la cual rechaza esta solicitud:");
        areaMotivoRechazo = crearAreaTextoRedondaEditable();

        // Botones circulares manteniendo tu paleta de colores (Confirmar Rechazo en Rojo)
        botonConfirmarRechazo = crearBotonRedondo("Confirmar Rechazo", new Color(220, 53, 69), Color.WHITE);
        botonCancelar = crearBotonRedondo("Cancelar", new Color(230, 235, 240), Color.BLACK);
        
        panelPrincipal.setLayout(null); 
        
        // Título de la modal
        lblTitulo.setFont(new Font("Segoe UI", 1, 16));
        lblTitulo.setBounds(130, 20, 220, 25);
        panelPrincipal.add(lblTitulo);
        
        // Etiqueta guía
        lblMotivo.setFont(new Font("Segoe UI", 1, 13));
        lblMotivo.setBounds(40, 65, 350, 25);
        panelPrincipal.add(lblMotivo);

        // Área de texto para la justificación del rechazo
        areaMotivoRechazo.setBounds(40, 100, 360, 100);
        panelPrincipal.add(areaMotivoRechazo);

        // Botones de acción bien posicionados
        botonConfirmarRechazo.setBounds(55, 225, 160, 35);
        panelPrincipal.add(botonConfirmarRechazo);
        
        botonCancelar.setBounds(235, 225, 160, 35);
        panelPrincipal.add(botonCancelar);
        
        getContentPane().add(panelPrincipal);
    }

    private JTextArea crearAreaTextoRedondaEditable() {
        JTextArea area = new JTextArea() {
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
        area.setEditable(true);
        area.setOpaque(false);
        area.setLineWrap(true);        // Hace que el texto salte de línea automáticamente al llegar al borde
        area.setWrapStyleWord(true);   // Evita que corte las palabras a la mitad al saltar de línea
        area.setFont(new Font("Arial", Font.PLAIN, 13));
        area.setForeground(new Color(60, 60, 60));
        area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10)); // Margen interno cómodo para escribir
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
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }
}