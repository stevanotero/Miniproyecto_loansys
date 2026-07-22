package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import proyect_loansys.model.UsuarioLogin;

/**
 * @author Alexis
 */
public class VentanaEstadoUsuarioModal extends JDialog {

    public JButton botonActivar;   
    public JButton botonDesactivar; 
    public JButton botonCancelar;
    private JLabel lblCorreo;
    private JLabel lblNombre;
    private JLabel lblEstadoActual;

    public VentanaEstadoUsuarioModal(Frame padre, UsuarioLogin usuario) {
        super(padre, true);
        setTitle("Gestionar Estado de Mora del Usuario");
        setSize(480, 310);
        setLocationRelativeTo(padre);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("CAMBIAR ESTADO DE MORA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBounds(30, 20, 380, 25);
        panel.add(lblTitulo);

        lblNombre = new JLabel("Persona: " + usuario.getNombreUsuario());
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNombre.setBounds(30, 60, 400, 22);
        panel.add(lblNombre);

        lblCorreo = new JLabel("Correo: " + usuario.getCorreo());
        lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCorreo.setBounds(30, 90, 400, 22);
        panel.add(lblCorreo);

        lblEstadoActual = new JLabel("Estado de mora actual: " + usuario.getDescripcionMora());
        lblEstadoActual.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstadoActual.setForeground(usuario.getIdEstadoMora() == 1 ? new Color(39, 174, 96) : new Color(192, 57, 43));
        lblEstadoActual.setBounds(30, 120, 400, 22);
        panel.add(lblEstadoActual);

        // Botones adaptados al estado de mora
        botonActivar = crearBotonRedondo("Al día", new Color(39, 174, 96), Color.WHITE);
        botonActivar.setBounds(30, 185, 115, 38);
        panel.add(botonActivar);

        botonDesactivar = crearBotonRedondo("En mora", new Color(231, 76, 60), Color.WHITE);
        botonDesactivar.setBounds(160, 185, 115, 38);
        panel.add(botonDesactivar);

        botonCancelar = crearBotonRedondo("Cancelar", new Color(149, 165, 166), Color.WHITE);
        botonCancelar.setBounds(290, 185, 115, 38);
        panel.add(botonCancelar);

        add(panel);
    }

    private JButton crearBotonRedondo(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }
}