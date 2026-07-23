package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Inicio_Tecnico extends Plantilla {

    private JLabel tituloCentral;
    private JLabel imagen;

    public Inicio_Tecnico() {
        super("Inicio Técnico");

        JPanel panel5 = super.getPanel();
        panel5.setBackground(Color.WHITE);
        panel5.setLayout(null);

        tituloCentral = new JLabel(
                "Bienvenido al Sistema de Prestamo de equipos",
                SwingConstants.CENTER);
        tituloCentral.setFont(new Font("Arial", Font.BOLD, 26));
        tituloCentral.setBounds(120, 100, 740, 70);

        ImageIcon logoGrande = new ImageIcon(getClass().getResource("/proyect_loansys/img/sena.png"));
        Image escalada = logoGrande.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagen = new JLabel(new ImageIcon(escalada));
        imagen.setBounds(390, 200, 200, 200);

        panel5.add(tituloCentral);
        panel5.add(imagen);

        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}