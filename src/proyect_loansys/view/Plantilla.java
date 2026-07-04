package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public abstract class Plantilla extends JFrame {

    private Container contenedor;
    private JPanel panel1, panel2, panel3, panel4, panel5;
    private JLabel logo, lblBienvenida;
    public JButton historial, Mantenimiento, Reportes, Notificaciones, cerrar_sesion;
    public DefaultTableModel modelo;
    public JTable tabla;

    public Plantilla(String nombre_interfaz) {
        super(nombre_interfaz);

        contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(1400, 70));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel1.setBackground(new Color(220, 220, 225));

        ImageIcon imagen_portada = new ImageIcon("logo_sena.png");
        Image imagen_ajustada = imagen_portada.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(imagen_ajustada));
        logo.setPreferredSize(new Dimension(50, 50));

        lblBienvenida = new JLabel("¡Bienvenido, Técnico!");
        lblBienvenida.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));

        panel1.add(logo);
        panel1.add(lblBienvenida);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(175, 1000));
        panel2.setBackground(new Color(220, 220, 225));
        panel2.setLayout(new BorderLayout());

        panel3 = new JPanel();
        panel3.setBackground(new Color(220, 220, 225));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setBorder(new EmptyBorder(10, 10, 10, 10));

        historial = crearBoton("🏠  Historial Técnico");
        Mantenimiento = crearBoton("🔧  Mantenimiento");
        Reportes = crearBoton("📄  Reportes");
        Notificaciones = crearBoton("🔔  Notificaciones");

        panel3.add(historial);
        panel3.add(Box.createVerticalStrut(8));
        panel3.add(Mantenimiento);
        panel3.add(Box.createVerticalStrut(8));
        panel3.add(Reportes);
        panel3.add(Box.createVerticalStrut(8));
        panel3.add(Notificaciones);

        panel4 = new JPanel();
        panel4.setBackground(new Color(220, 220, 225));
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        cerrar_sesion = new JButton("  »  Cerrar sesión");
        cerrar_sesion.setPreferredSize(new Dimension(150, 35));
        cerrar_sesion.setBackground(new Color(220, 53, 69));
        cerrar_sesion.setForeground(Color.WHITE);
        cerrar_sesion.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        cerrar_sesion.setFocusPainted(false);
        cerrar_sesion.setBorderPainted(false);

        panel4.add(cerrar_sesion);

        panel2.add(panel3, BorderLayout.NORTH);
        panel2.add(panel4, BorderLayout.SOUTH);

        panel5 = new JPanel();
        panel5.setBackground(new Color(245, 245, 245));

        contenedor.add(panel1, BorderLayout.NORTH);
        contenedor.add(panel2, BorderLayout.WEST);
        contenedor.add(panel5, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btn.setPreferredSize(new Dimension(150, 35));
        btn.setBackground(new Color(209, 209, 214));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    public Container getConteiner() {
        return this.contenedor;
    }

    public JPanel getPanel() {
        return this.panel5;
    }

    public JButton getButtonActivacion() {
        return this.Mantenimiento;
    }

    public JButton getButtonModificar() {
        return this.Reportes;
    }
}