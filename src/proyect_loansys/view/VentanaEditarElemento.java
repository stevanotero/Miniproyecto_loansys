/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class VentanaEditarElemento extends JDialog {

    // Componentes públicos para que el controlador pueda leerlos y escribir en ellos
    public JTextField textoCodigo;
    public JTextField textoNombre;
    public JTextField textoCategoria;
    public JComboBox<String> ListaEstado;
    public JTextField textoDescripcion;
    public JButton botonGuardar;
    public JButton botonCancelar;

    public VentanaEditarElemento(Frame padre) {
        super(padre, "Modificar Elemento", true); // 'true' la hace modal
        this.setSize(400, 500);
        this.setLocationRelativeTo(padre);
        this.setResizable(false);

        // Contenedor principal con margen elegante
        JPanel panelRaiz = new JPanel();
        panelRaiz.setLayout(new BoxLayout(panelRaiz, BoxLayout.Y_AXIS));
        panelRaiz.setBackground(Color.WHITE);
        panelRaiz.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // Título de la ventana emergente
        JLabel lblTitulo = new JLabel("Editar Detalles del Elemento");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRaiz.add(lblTitulo);
        panelRaiz.add(Box.createVerticalStrut(20));

        // texto del codigo
        textoCodigo = crearCampoFormulario(panelRaiz, "Código del Elemento:");
        textoCodigo.setEnabled(false);

        // texto del nombre
        textoNombre = crearCampoFormulario(panelRaiz, "Nombre del Elemento:");

        // texto de la categoria
        textoNombre = crearCampoFormulario(panelRaiz, "Categoría:");
        textoNombre.setEnabled(false);

        // lista del estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setForeground(Color.GRAY);
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRaiz.add(lblEstado);
        panelRaiz.add(Box.createVerticalStrut(5));

        ListaEstado = new JComboBox<>(new String[]{"Disponible", "En Mantenimiento", "Dañado"});
        ListaEstado.setMaximumSize(new Dimension(340, 35));
        ListaEstado.setPreferredSize(new Dimension(340, 35));
        ListaEstado.setBackground(Color.WHITE);
        ListaEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ListaEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRaiz.add(ListaEstado);
        panelRaiz.add(Box.createVerticalStrut(15));

        // texto de la descripcion
        textoDescripcion = crearCampoFormulario(panelRaiz, "Descripción:");

        // botones de cancelar y guardar cambios
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Botón Cancelar Redondeado
        botonCancelar = new JButton("Cancelar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        configurarBoton(botonCancelar, new Color(220, 220, 220), Color.BLACK);

        // Boton Guardar Redondeado
        botonGuardar = new JButton("Guardar Cambios") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        configurarBoton(botonGuardar, new Color(40, 167, 69), Color.WHITE);

        panelBotones.add(botonCancelar);
        panelBotones.add(botonGuardar);

        panelRaiz.add(Box.createVerticalGlue());
        panelRaiz.add(panelBotones);

        this.add(panelRaiz);
    }

    // Metodo para configurar propiedades visuales del botón
    private void configurarBoton(JButton boton, Color fondo, Color texto) {
        boton.setPreferredSize(new Dimension(140, 38));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false); 
        boton.setBorderPainted(false);     
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JTextField crearCampoFormulario(JPanel contenedor, String textoLabel) {
        JLabel label = new JLabel(textoLabel);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(Color.GRAY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        contenedor.add(label);
        contenedor.add(Box.createVerticalStrut(5));

        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(340, 35));
        campo.setPreferredSize(new Dimension(340, 35));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
        contenedor.add(campo);
        contenedor.add(Box.createVerticalStrut(15));
        return campo;
    }
}
