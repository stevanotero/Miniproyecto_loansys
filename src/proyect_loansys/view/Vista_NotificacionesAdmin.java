/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Alexis
 */

public class Vista_NotificacionesAdmin extends Administrador_Plantilla_Administrador {

    // Componentes públicos requeridos por el Controlador
    public JTable tablaNotificaciones;
    public DefaultTableModel modeloTabla;
    public JComboBox<String> comboTipoNotificacion;
    public JTextField txtDocumentoDestinatario;
    public JTextArea txtAreaMensaje;
    public JButton btnEnviarNotificacion;
    private JPanel panel5; 

    public Vista_NotificacionesAdmin() {
        // Inicializar la plantilla base pasando el título de la vista
        super("Notificaciones");

        // Obtener el panel donde se renderiza el contenido central
        panel5 = super.getPanel();
        panel5.setLayout(new BorderLayout());
        panel5.setBackground(Color.WHITE);

        // Insertar el contenido completo del módulo de notificaciones
        panel5.add(crearPanelCentro(), BorderLayout.CENTER);
    }

    private JPanel crearPanelCentro() {
        // Panel contenedor con márgenes
        JPanel panelPrincipalCentrado = new JPanel(new GridLayout(1, 2, 30, 0));
        panelPrincipalCentrado.setBackground(Color.WHITE);
        panelPrincipalCentrado.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Tonos de gris para las tarjetas y campos
        Color colorFondoTarjetas = new Color(220, 220, 220);
        Color colorCampos = new Color(198, 198, 198);

        // ==================== TARJETA IZQUIERDA: LISTADO ====================
        JPanel panelIzquierdo = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
            }
        };
        panelIzquierdo.setBackground(colorFondoTarjetas);
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título del listado
        JLabel lbTituloListado = new JLabel("Todas las notificaciones", JLabel.CENTER);
        lbTituloListado.setFont(new Font("Arial", Font.BOLD, 20));
        lbTituloListado.setForeground(new Color(30, 30, 30));
        lbTituloListado.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        panelIzquierdo.add(lbTituloListado, BorderLayout.NORTH);

        // Modelo de tabla no editable
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Mensaje de la Notificación");

        // Configuración de la tabla
        tablaNotificaciones = new JTable(modeloTabla);
        tablaNotificaciones.setRowHeight(38);
        tablaNotificaciones.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaNotificaciones.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablaNotificaciones.getTableHeader().setReorderingAllowed(false);
        tablaNotificaciones.setGridColor(new Color(235, 235, 235));
        tablaNotificaciones.getColumnModel().getColumn(0).setPreferredWidth(120);
        tablaNotificaciones.getColumnModel().getColumn(0).setMaxWidth(160);

        JScrollPane scrollTabla = new JScrollPane(tablaNotificaciones);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        panelIzquierdo.add(scrollTabla, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
            }
        };
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBackground(colorFondoTarjetas);
        panelDerecho.setOpaque(false);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Título del Formulario
        JLabel lbTituloCrear = new JLabel("Crear nueva notificación");
        lbTituloCrear.setFont(new Font("Arial", Font.BOLD, 20));
        lbTituloCrear.setForeground(new Color(30, 30, 30));
        lbTituloCrear.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Inicializar controles estilizados
        txtDocumentoDestinatario = crearCampo(colorCampos);

        String[] opcionesDb = {
            "Préstamo Aprobado",
            "Devolución Pendiente",
            "Elemento en Mantenimiento",
            "Solicitud Recibida",
            "Alerta de Daño",
            "Solicitud Rechazada"
        };
        comboTipoNotificacion = crearCombo(opcionesDb, colorCampos);
        JScrollPane scrollAreaMensaje = crearAreaTexto(colorCampos);
        btnEnviarNotificacion = crearBoton("Enviar notificación", new Color(46, 204, 113), Color.WHITE);

        // Bloque del formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(colorFondoTarjetas);
        panelFormulario.setOpaque(false);
        panelFormulario.setMaximumSize(new Dimension(350, 420));
        panelFormulario.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelFormulario.add(crearGrupoCampo("Tipo de notificación", comboTipoNotificacion, colorFondoTarjetas));
        panelFormulario.add(Box.createVerticalStrut(15));

        panelFormulario.add(crearGrupoCampo("Destinatario", txtDocumentoDestinatario, colorFondoTarjetas));
        panelFormulario.add(Box.createVerticalStrut(15));

        panelFormulario.add(crearGrupoCampo("Mensaje", scrollAreaMensaje, colorFondoTarjetas));
        panelFormulario.add(Box.createVerticalStrut(20));

        panelFormulario.add(btnEnviarNotificacion);

        // Ensamblado del panel derecho
        panelDerecho.add(lbTituloCrear);
        panelDerecho.add(Box.createVerticalStrut(20));
        panelDerecho.add(panelFormulario);

        // Agregar las dos tarjetas al panel contenedor
        panelPrincipalCentrado.add(panelIzquierdo);
        panelPrincipalCentrado.add(panelDerecho);

        return panelPrincipalCentrado;
    }

    // Método para redondear campos JTextField
    private JTextField crearCampo(Color fondo) {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        campo.setOpaque(false);
        campo.setMaximumSize(new Dimension(350, 40));
        campo.setPreferredSize(new Dimension(350, 40));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(new Color(40, 40, 40));
        campo.setCaretColor(Color.BLACK);
        campo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return campo;
    }

    // Método para redondear el JComboBox
    private JComboBox<String> crearCombo(String[] items, Color fondo) {
        JComboBox<String> combo = new JComboBox<>(items) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        combo.setOpaque(false);
        combo.setMaximumSize(new Dimension(350, 40));
        combo.setPreferredSize(new Dimension(350, 40));
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setForeground(new Color(40, 40, 40));
        combo.setBackground(fondo);
        combo.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = new JButton() {
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(new Color(60, 60, 60));
                        int[] xPoints = {getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() / 2};
                        int[] yPoints = {getHeight() / 2 - 2, getHeight() / 2 - 2, getHeight() / 2 + 3};
                        g2.fillPolygon(xPoints, yPoints, 3);
                        g2.dispose();
                    }
                };
                btn.setContentAreaFilled(false);
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setPreferredSize(new Dimension(25, 25));
                return btn;
            }
        });

        return combo;
    }

    // Método para el área de texto redondeada
    private JScrollPane crearAreaTexto(Color fondo) {
        txtAreaMensaje = new JTextArea(5, 20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        txtAreaMensaje.setOpaque(false);
        txtAreaMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaMensaje.setLineWrap(true);
        txtAreaMensaje.setWrapStyleWord(true);
        txtAreaMensaje.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        String placeholder = "Escriba el mensaje que desea enviar...";
        txtAreaMensaje.setText(placeholder);
        txtAreaMensaje.setForeground(new Color(110, 110, 110));

        txtAreaMensaje.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (txtAreaMensaje.getText().equals(placeholder)) {
                    txtAreaMensaje.setText("");
                    txtAreaMensaje.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (txtAreaMensaje.getText().isEmpty()) {
                    txtAreaMensaje.setText(placeholder);
                    txtAreaMensaje.setForeground(new Color(110, 110, 110));
                }
            }
        });

        JScrollPane scroll = new JScrollPane(txtAreaMensaje) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setMaximumSize(new Dimension(350, 120));
        scroll.setPreferredSize(new Dimension(350, 120));

        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scroll.getVerticalScrollBar().setOpaque(false);
        scroll.getVerticalScrollBar().setBackground(new Color(0, 0, 0, 0));

        return scroll;
    }

    // Método para redondear botones
    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setMaximumSize(new Dimension(240, 40));
        boton.setPreferredSize(new Dimension(240, 40));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }

    // Agrupador de label y componente
    private JPanel crearGrupoCampo(String titulo, JComponent control, Color fondoTarjeta) {
        JPanel grupo = new JPanel();
        grupo.setLayout(new BoxLayout(grupo, BoxLayout.Y_AXIS));
        grupo.setBackground(fondoTarjeta);
        grupo.setOpaque(false);
        grupo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(50, 50, 50));

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        control.setAlignmentX(Component.LEFT_ALIGNMENT);

        int altoControl = control.getMaximumSize().height;
        grupo.setMaximumSize(new Dimension(350, altoControl + 22));
        grupo.setPreferredSize(new Dimension(350, altoControl + 22));

        grupo.add(label);
        grupo.add(Box.createVerticalStrut(5));
        grupo.add(control);

        return grupo;
    }
}