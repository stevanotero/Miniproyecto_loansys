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
import java.awt.Container;
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
import javax.swing.Icon;

public abstract class Usuario_Plantilla extends JFrame {

    // Se conservan los mismos campos públicos que ya usan otras clases
    public JButton iniciod, prestamo, notificacion, cerrarS, inventario;

    // Campos internos (privados, no afectan a quien ya usa esta clase)
    private Container contenedor;
    private JPanel barralateral;
    private JPanel panelContenido;
    private JPanel panel4; // se conserva el nombre para no romper getPanel()
    private JLabel logo;
    private JLabel nombreDelSistema;
    public JLabel textoDeBienvenida;
    private JLabel textoDelPanelPrincipal;

    private String rol;
    private String nombre;
    private String titulo;

    public Usuario_Plantilla(String titulo, String rol, String nombre) {
        super(titulo);
        this.titulo = titulo;
        this.rol = rol;
        this.nombre = nombre;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());

        crearBarraLateral();
        crearPanelContenido();

        contenedor.add(barralateral, BorderLayout.WEST);
        contenedor.add(panelContenido, BorderLayout.CENTER);
    }

    private void crearBarraLateral() {
        barralateral = new JPanel();
        barralateral.setPreferredSize(new Dimension(260, getHeight()));
        barralateral.setBackground(new Color(228, 230, 233));
        barralateral.setLayout(new BoxLayout(barralateral, BoxLayout.Y_AXIS));
        barralateral.setBorder(new EmptyBorder(20, 0, 30, 0));

        // Encabezado con logo y nombre del sistema
        JPanel panelDelEncabezado = new JPanel();
        panelDelEncabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelDelEncabezado.setOpaque(false);
        panelDelEncabezado.setMaximumSize(new Dimension(260, 90));
        panelDelEncabezado.setAlignmentX(Component.CENTER_ALIGNMENT);

        Image imgSena = new ImageIcon(getClass().getResource("/proyect_loansys/img/sena.png"))
                .getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imgSena));

        JPanel panelTextos = new JPanel();
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
        panelTextos.setOpaque(false);

        nombreDelSistema = new JLabel("LoanSys");
        nombreDelSistema.setFont(nombreDelSistema.getFont().deriveFont(Font.BOLD, 22f));

        JLabel lblSubtitulo1 = new JLabel("Sistema de inventario");
        lblSubtitulo1.setFont(lblSubtitulo1.getFont().deriveFont(Font.PLAIN, 11f));
        JLabel lblSubtitulo2 = new JLabel("y préstamo");
        lblSubtitulo2.setFont(lblSubtitulo2.getFont().deriveFont(Font.PLAIN, 11f));

        panelTextos.add(nombreDelSistema);
        panelTextos.add(lblSubtitulo1);
        panelTextos.add(lblSubtitulo2);
        panelDelEncabezado.add(logo);
        panelDelEncabezado.add(panelTextos);
        barralateral.add(panelDelEncabezado);
        barralateral.add(Box.createVerticalStrut(40));

        // Botones del menú (mismos campos públicos, mismo orden que en la plantilla original)
        JLabel ldescu = new JLabel("Descubrir",SwingConstants.CENTER);
        ldescu.setFont(ldescu.getFont().deriveFont(Font.BOLD, 13f));
        ldescu.setAlignmentX(Component.CENTER_ALIGNMENT);
        ldescu.setBorder(new EmptyBorder(0, 20, 10, 0));
        barralateral.add(ldescu);

        //iniciod = crearBotonMenu("Inicio");
        iniciod = crearBotonMenu("Inicio", "/proyect_loansys/img/iconscasa24.png");
        inventario = crearBotonMenu("Inventario", "/proyect_loansys/img/iconsinventario.png");
        prestamo = crearBotonMenu("Historial", "/proyect_loansys/img/iconspréstamo24.png");
        notificacion = crearBotonMenu("Notificación", "/proyect_loansys/img/iconsnotificacion24.png");

        int espacioBotones = 12;
        barralateral.add(iniciod);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(inventario);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(prestamo);
        barralateral.add(Box.createVerticalStrut(espacioBotones));
        barralateral.add(notificacion);
        barralateral.add(Box.createVerticalGlue(),BorderLayout.CENTER);

        // Botón de cerrar sesión con esquinas redondeadas
        cerrarS = new JButton("Cerrar sesión") {
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
        cerrarS.setFont(cerrarS.getFont().deriveFont(Font.BOLD, 13f));
        cerrarS.setForeground(Color.WHITE);
        cerrarS.setHorizontalAlignment(SwingConstants.CENTER);
        cerrarS.setContentAreaFilled(false);
        cerrarS.setBorderPainted(false);
        cerrarS.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension btnSize = new Dimension(220, 42);
        cerrarS.setPreferredSize(btnSize);
        cerrarS.setMinimumSize(btnSize);
        cerrarS.setMaximumSize(btnSize);
        cerrarS.setAlignmentX(Component.CENTER_ALIGNMENT);
        barralateral.add(cerrarS);
    }

    private void crearPanelContenido() {
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(Color.WHITE);

        JPanel barraDeArriba = new JPanel();
        barraDeArriba.setBackground(new Color(218, 220, 224));
        barraDeArriba.setPreferredSize(new Dimension(getWidth(), 80));
        barraDeArriba.setLayout(new BoxLayout(barraDeArriba, BoxLayout.Y_AXIS));
        barraDeArriba.setBorder(new EmptyBorder(15, 30, 15, 30));

        // Se conserva el formato de bienvenida con rol y nombre, igual que la plantilla original
        textoDeBienvenida = new JLabel("" + rol + " " + nombre + "");
        textoDeBienvenida.setFont(textoDeBienvenida.getFont().deriveFont(Font.BOLD, 22f));

        textoDelPanelPrincipal = new JLabel("Panel principal");
        textoDelPanelPrincipal.setFont(textoDelPanelPrincipal.getFont().deriveFont(Font.PLAIN, 12f));
        textoDelPanelPrincipal.setForeground(new Color(120, 120, 120));

        barraDeArriba.add(textoDeBienvenida);
        barraDeArriba.add(Box.createVerticalStrut(5));
        barraDeArriba.add(textoDelPanelPrincipal);
        panelContenido.add(barraDeArriba, BorderLayout.NORTH);

        // panel4 se conserva igual: es donde cada vista hija agrega su contenido específico
        panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panelContenido.add(panel4, BorderLayout.CENTER);
    }
/*
    private JButton crearBotonMenu(String texto) {
        JButton botondelmenu = new JButton(texto);
        botondelmenu.setFont(botondelmenu.getFont().deriveFont(Font.PLAIN, 14f));
        botondelmenu.setForeground(Color.BLACK);
        botondelmenu.setHorizontalAlignment(SwingConstants.LEFT);
        botondelmenu.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        botondelmenu.setContentAreaFilled(false);
        botondelmenu.setBorderPainted(false);
        botondelmenu.setFocusPainted(false);
        botondelmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension btnSize = new Dimension(120, 42);
        botondelmenu.setPreferredSize(btnSize);
        botondelmenu.setMinimumSize(btnSize);
        botondelmenu.setMaximumSize(btnSize);
        botondelmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        return botondelmenu;
    }
*/
    private JButton crearBotonMenu(String texto, String rutaIcono) {
    JButton botondelmenu;

    if (rutaIcono != null) {
        Image img = new ImageIcon(getClass().getResource(rutaIcono))
                .getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        botondelmenu = new JButton(texto, new ImageIcon(img));
    } else {
        botondelmenu = new JButton(texto);
    }

    botondelmenu.setFont(botondelmenu.getFont().deriveFont(Font.PLAIN, 14f));
    botondelmenu.setForeground(Color.BLACK);
    botondelmenu.setHorizontalAlignment(SwingConstants.LEFT);
    botondelmenu.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    botondelmenu.setContentAreaFilled(false);
    botondelmenu.setBorderPainted(false);
    botondelmenu.setFocusPainted(false);
    botondelmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

    Dimension btnSize = new Dimension(220, 42);
    botondelmenu.setPreferredSize(btnSize);
    botondelmenu.setMinimumSize(btnSize);
    botondelmenu.setMaximumSize(btnSize);
    botondelmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
    return botondelmenu;
}

    // Se conservan los mismos métodos públicos que ya usan otras clases
    public Container getContainer() {
        return this.contenedor;
    }

    public JPanel getPanel() {
        return this.panel4;
    }
}
