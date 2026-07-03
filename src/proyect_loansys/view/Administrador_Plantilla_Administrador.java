/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juans
 */
public abstract class Administrador_Plantilla_Administrador extends JFrame {

    private Container contenedor;
    private JPanel panel1, panel2, panel3, panel4, panel5;
    private FlowLayout miflow;
    private GridLayout grid1, grid2, grid3, grid4;
    private JLabel titulo, logo, titulo2, nombre;
    public JButton binicio, activacion_usuario, modificar, gestion_roles, registrar_usuario, cerrar_sesion;
    public DefaultTableModel modelo;
    private JScrollPane miscroll;
    public JTable tabla;
    private String Nombre_Usuario = "Stevan Otero";

    public Administrador_Plantilla_Administrador(String nombre_interfaz) {
        super(nombre_interfaz);

        contenedor = getContentPane();
        miflow = new FlowLayout();
        contenedor.setLayout(miflow);

        //Creaccion de paneles//
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel5 = new JPanel();
        // ----------------------- Campos------------------//
        titulo = new JLabel("   LoanSys");
        nombre = new JLabel("    Bienvenido  " + "!" + Nombre_Usuario + "!");
// Ejemplo básico: Fuente "Arial", estilo Negrita, tamaño 20
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));

        //Aqui llamando a la imagen que tengo guardada en documentos, y creo una variable para guardarla
        ImageIcon imagen_portada = new ImageIcon("logo_sena.png");
        //Aqui estoy ajustando el tama;o de la imagen para que cuadre
        Image imagen_ajustada = imagen_portada.getImage().getScaledInstance(50, 52, Image.SCALE_SMOOTH);
        Icon portada = new ImageIcon(imagen_ajustada);

        logo = new JLabel(portada);

// Le damos un tamaño fijo al panel superior
        panel1.setPreferredSize(new Dimension(1400, 75));

// Usamos FlowLayout alineado a la izquierda.
// Todo lo que agreguemos aparecerá de izquierda a derecha.
// gap al final del left
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

// Agregamos primero el título.
// Como es el primer componente, aparecerá primero.
        panel1.add(titulo);

// Agregamos el logo.
// Como es el segundo componente, aparecerá después del título.
        panel1.add(logo);
        panel1.add(nombre);
// Color de fondo del panel.
        panel1.setBackground(Color.white);

//panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.setBorder(
                BorderFactory.createMatteBorder(
                        0, // arriba
                        0, // izquierda
                        1, // abajo
                        0, // derecha
                        Color.GRAY)
        );

        // menu
        // Establecemos el tamaño del panel lateral
        panel2.setPreferredSize(new Dimension(235, 1000));
        panel2.setBackground(Color.white);
        // Utilizamos BorderLayout para dividir el menú en zonas
        panel2.setLayout(new BorderLayout());
        // panel interno menu

        binicio = new JButton("🏠︎ Inicio");
        binicio.setBorder(BorderFactory.createLineBorder(Color.gray, 10, true));// Borde redondeado para el botón
        activacion_usuario = new JButton(" 👤Activacion / Usuario");
        activacion_usuario.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón
        modificar = new JButton("👤Modificar / Usuario");
        modificar.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón
        gestion_roles = new JButton("👤Gestion de roles");
        gestion_roles.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón
        registrar_usuario = new JButton("👤Registrar Usuario");
        registrar_usuario.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));// Borde redondeado para el botón

        cerrar_sesion = new JButton("X Cerrar sesion");
        cerrar_sesion.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        cerrar_sesion.setForeground(Color.white);
        binicio.setBackground(Color.gray);

        activacion_usuario.setBackground(Color.white);
        modificar.setBackground(Color.white);
        gestion_roles.setBackground(Color.white);
        registrar_usuario.setBackground(Color.white);
        cerrar_sesion.setBackground(Color.red);
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel4.setBackground(Color.white);
        grid3 = new GridLayout(1, 1, 1, 1); // distrubuccion
        panel4.setLayout(grid3);
        panel4.add(cerrar_sesion);
        panel3.setBackground(Color.white);
        grid2 = new GridLayout(10, 5, 5, 25); // distribuccion
        panel3.setLayout(grid2);
        panel3.add(binicio);
        panel3.add(activacion_usuario);
        panel3.add(modificar);
        panel3.add(gestion_roles);
        panel3.add(registrar_usuario);

//        panel3.add(cerrar_sesion);
        panel2.add(panel3, BorderLayout.CENTER);
        panel2.add(panel4, BorderLayout.SOUTH);
        //Borde arriba y ala derecha
        panel2.setBorder(
                BorderFactory.createMatteBorder(
                        1, // arriba
                        0, // izquierda
                        0, // abajo
                        1, // derecha
                        Color.GRAY)
        );
        contenedor.setLayout(new BorderLayout());
        contenedor.add(panel1, BorderLayout.NORTH);
        contenedor.add(panel2, BorderLayout.WEST);
        contenedor.add(panel5, BorderLayout.CENTER);

    }

    public Container getConteiner() {
        return this.contenedor;
    }

    public JPanel getPanel() {
        return this.panel5;
    }

    public JButton getButtonActivacion() {
        return this.activacion_usuario;
    }
    
    public  JButton getButtonModificar(){
        return this.modificar;
    }

}
