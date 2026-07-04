package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DescargarReporte_Tecnico extends JDialog {

    public JTextField txtCodigoReporte;
    public JButton btnDescargar;
    public JButton btnCancelar;

    public DescargarReporte_Tecnico(java.awt.Frame parent) {
        super(parent, true);
        setTitle("Exportar Reporte");
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.setBackground(new Color(245, 245, 245));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(30, 20, 420, 270);
        panel.setBackground(new Color(215, 215, 215));

        JLabel lblTitulo = new JLabel("Exportar reporte", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(0, 25, 420, 30);

        txtCodigoReporte = new JTextField();
        txtCodigoReporte.setBounds(110, 100, 200, 30);
        txtCodigoReporte.setBackground(Color.WHITE);

        JLabel lblPlaceholder = new JLabel("Codigo Reporte", SwingConstants.CENTER);
        lblPlaceholder.setBounds(110, 75, 200, 20);
        lblPlaceholder.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPlaceholder.setForeground(new Color(100, 100, 100));

        btnDescargar = new JButton("Descargar");
        btnDescargar.setBounds(50, 200, 120, 35);
        btnDescargar.setBackground(new Color(40, 167, 69));
        btnDescargar.setForeground(Color.WHITE);
        btnDescargar.setFocusPainted(false);
        btnDescargar.setBorderPainted(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 200, 120, 35);
        btnCancelar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);

        panel.add(lblTitulo);
        panel.add(lblPlaceholder);
        panel.add(txtCodigoReporte);
        panel.add(btnDescargar);
        panel.add(btnCancelar);

        contenedor.add(panel);
        getContentPane().add(contenedor);
        getContentPane().setBackground(new Color(245, 245, 245));

        getContentPane().add(contenedor);
        getContentPane().setBackground(new Color(245, 245, 245));
    }
}
    

