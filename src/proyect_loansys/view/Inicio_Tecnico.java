package proyect_loansys.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Inicio_Tecnico extends Plantilla {

    private JLabel tituloCentral;
    private JLabel imagen;

    public Inicio_Tecnico() {
        super("Inicio Técnico"); 

        JPanel panel5 = super.getPanel();
        panel5.setBackground(new Color(245, 245, 245));
        panel5.setLayout(null);

        tituloCentral = new JLabel(
                "Bienvenido al Sistema de Prestamo de equipos",
                SwingConstants.CENTER);
        tituloCentral.setFont(new Font("Arial", Font.BOLD, 20));
        tituloCentral.setBounds(120, 120, 740, 40);

        imagen = new JLabel();
        imagen.setBounds(340, 200, 300, 250);

        panel5.add(tituloCentral);
        panel5.add(imagen);

        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}