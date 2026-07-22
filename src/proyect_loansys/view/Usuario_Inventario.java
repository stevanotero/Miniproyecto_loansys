/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Elemento;

public class Usuario_Inventario extends Usuario_Plantilla {

    private Usuario_Dao elementoDao = new Usuario_Dao();

    private Container contenedor;
    private JPanel panel4, panel5, panel6, panel7, panel8, panel9, panel010, panel011,
            panel012, panel013, panel014, panel015, panel016, panel017, panel018,
            panel019, panel020, panel021, panel022, panel023, panel024, panel025,
            panel026, panel027, panel028, panel029, panel030, panel031, panel032,
            panel033, panel034, panel035, panel036, panel037, panel038, panel039,
            panel040, panel041, panel042, panel043;

    public JLabel inventario, fEstado, codigo, estado1, estado2, estado3, estado4, estado5,
            estado6, estado7, estado8, estado9, estado10, estado11, estado, estado12, estado13, text1, text2, text3, text4;
    public JTextField lcodigo;
    public String lista[] = {"", "Disponible", "Mantenimiento", "Ocupado", "Dañado", "No Disponible"};
    public JComboBox listaEstado;
    public JButton buscador, perifericos, computadores, herramientas, maquinas, prueba,
            prueba1, prueba2, prueba3, prueba4, prueba5, prueba6, prueba7, prueba8, prueba9, prueba10,
            prueba11, prueba12, prueba13;
    private GridLayout grid, grid2, grid3, grid4, grid5, grid6, grid7;
    private JScrollPane miscroll;
    

    public Usuario_Inventario(String titulo, String rol, String nombre) {
        super(titulo, rol, nombre);

        contenedor = super.getContainer();
        panel4 = super.getPanel();

        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(1100, 600));
        panel5.setBackground(Color.WHITE);
        panel5.setLayout(new BorderLayout(10, 10));

        // ========== PANEL SUPERIOR - FILTROS (panel6) ==========
        panel6 = new JPanel(new BorderLayout());
        panel6.setPreferredSize(new Dimension(1100, 80));
        panel6.setBackground(Color.WHITE);
        panel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel izquierdo del filtro (panel9)
        panel9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panel9.setBackground(Color.WHITE);

        fEstado = new JLabel("Filtro de estado");
        fEstado.setFont(fEstado.getFont().deriveFont(Font.BOLD, 12f));
        listaEstado = new JComboBox(lista);

        panel9.add(fEstado);
        panel9.add(listaEstado);

        // Panel derecho del filtro (panel010)
        panel010 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        panel010.setBackground(Color.WHITE);

        codigo = new JLabel("busqueda por codigo de Elemento");
        codigo.setFont(codigo.getFont().deriveFont(Font.BOLD, 12f));
        lcodigo = new JTextField(15);

        Image imabusqude = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgIconBuscar.png")).getImage();
        Icon imagen1 = new ImageIcon(imabusqude);
        buscador = crearBoton("Buscar...", imagen1, new Color(228, 230, 233), Color.black);

        panel010.add(codigo);
        panel010.add(lcodigo);
        panel010.add(buscador);

        panel6.add(panel9, BorderLayout.WEST);
        panel6.add(panel010, BorderLayout.EAST);

        // ========== PANEL CENTRAL - CATEGORÍAS (panel7) ==========
        panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panel7.setPreferredSize(new Dimension(1100, 180));
        panel7.setBackground(Color.WHITE);

        // Categoría: Computadores (panel013)
        panel013 = new JPanel(new BorderLayout());
        panel013.setPreferredSize(new Dimension(200, 160));
        panel013.setBackground(Color.WHITE);
        panel013.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        panel017 = new JPanel(new BorderLayout());
        panel017.setPreferredSize(new Dimension(200, 180));
        panel017.setBackground(Color.WHITE);

        panel021 = new JPanel();
        panel021.setPreferredSize(new Dimension(20, 40));
        panel021.setLayout(new BorderLayout());
        text1 = new JLabel("Computadores", SwingConstants.CENTER);
        panel021.setBackground(Color.red);
        panel021.add(text1);
        text1.setForeground(Color.BLACK);
        panel017.add(panel021, BorderLayout.NORTH);

        panel022 = new JPanel();
        panel022.setPreferredSize(new Dimension(100, 80));
        panel022.setLayout(new BorderLayout());
        Image imgCom = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgIconoCompu.png")).getImage();
        Icon imagen7 = new ImageIcon(imgCom);
        computadores = crearBoton("", imagen7, new Color(255, 255, 255), Color.white);
        computadores.setHorizontalTextPosition(SwingConstants.CENTER);
        panel022.add(computadores);
        panel017.add(panel022, BorderLayout.CENTER);

        panel013.add(panel017, BorderLayout.CENTER);

        // Categoría: Periféricos (panel014)
        panel014 = new JPanel(new BorderLayout());
        panel014.setPreferredSize(new Dimension(200, 160));
        panel014.setBackground(Color.WHITE);
        panel014.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        panel018 = new JPanel(new BorderLayout());
        panel018.setPreferredSize(new Dimension(200, 180));
        panel018.setBackground(Color.WHITE);

        panel023 = new JPanel();
        panel023.setPreferredSize(new Dimension(20, 40));
        panel023.setLayout(new BorderLayout());
        text2 = new JLabel("Perifericos", SwingConstants.CENTER);
        panel023.setBackground(Color.blue);
        panel023.add(text2);
        text2.setForeground(Color.BLACK);
        panel018.add(panel023, BorderLayout.NORTH);

        panel024 = new JPanel();
        panel024.setPreferredSize(new Dimension(100, 80));
        panel024.setLayout(new BorderLayout());
        Image imgPeri = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgIcoMa.png")).getImage();
        Icon imagen8 = new ImageIcon(imgPeri);
        perifericos = crearBoton("", imagen8, new Color(255, 255, 255), Color.white);
        perifericos.setHorizontalTextPosition(SwingConstants.CENTER);
        panel024.add(perifericos);
        panel018.add(panel024, BorderLayout.CENTER);

        panel014.add(panel018, BorderLayout.CENTER);

        // Categoría: Herramientas (panel015)
        panel015 = new JPanel(new BorderLayout());
        panel015.setPreferredSize(new Dimension(200, 160));
        panel015.setBackground(Color.WHITE);
        panel015.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        panel019 = new JPanel(new BorderLayout());
        panel019.setPreferredSize(new Dimension(200, 180));
        panel019.setBackground(Color.WHITE);

        panel025 = new JPanel();
        panel025.setPreferredSize(new Dimension(20, 40));
        panel025.setLayout(new BorderLayout());
        text3 = new JLabel("Herramientas", SwingConstants.CENTER);
        panel025.setBackground(Color.YELLOW);
        panel025.add(text3);
        text3.setForeground(Color.BLACK);
        panel019.add(panel025, BorderLayout.NORTH);

        panel026 = new JPanel();
        panel026.setPreferredSize(new Dimension(100, 80));
        panel026.setLayout(new BorderLayout());
        Image imgHerra = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgHerramienta.png")).getImage();
        Icon imagen9 = new ImageIcon(imgHerra);
        herramientas = crearBoton("", imagen9, new Color(255, 255, 255), Color.white);
        herramientas.setHorizontalTextPosition(SwingConstants.CENTER);
        panel026.add(herramientas);
        panel019.add(panel026, BorderLayout.CENTER);

        panel015.add(panel019, BorderLayout.CENTER);

        // Categoría: Máquinas (panel016)
        panel016 = new JPanel(new BorderLayout());
        panel016.setPreferredSize(new Dimension(200, 160));
        panel016.setBackground(Color.WHITE);
        panel016.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        panel020 = new JPanel(new BorderLayout());
        panel020.setPreferredSize(new Dimension(200, 180));
        panel020.setBackground(Color.WHITE);

        panel027 = new JPanel();
        panel027.setPreferredSize(new Dimension(20, 40));
        panel027.setLayout(new BorderLayout());
        text4 = new JLabel("Maquinas", SwingConstants.CENTER);
        panel027.setBackground(Color.GREEN);
        panel027.add(text4);
        text4.setForeground(Color.BLACK);
        panel020.add(panel027, BorderLayout.NORTH);

        panel028 = new JPanel();
        panel028.setPreferredSize(new Dimension(100, 80));
        panel028.setLayout(new BorderLayout());
        Image imgMaqui = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgIcoProye.png")).getImage();
        Icon imagen10 = new ImageIcon(imgMaqui);
        maquinas = crearBoton("", imagen10, new Color(255, 255, 255), Color.white);
        maquinas.setHorizontalTextPosition(SwingConstants.CENTER);
        panel028.add(maquinas);
        panel020.add(panel028, BorderLayout.CENTER);

        panel016.add(panel020, BorderLayout.CENTER);

        // Agregar categorías al panel7
        panel7.add(panel013);
        panel7.add(panel014);
        panel7.add(panel015);
        panel7.add(panel016);

        // ========== PANEL INFERIOR - ELEMENTOS (panel8) ==========
        panel8 = new JPanel(new BorderLayout());
        panel8.setPreferredSize(new Dimension(1100, 280));
        panel8.setBackground(Color.WHITE);

        // Panel con GridLayout para los botones
        panel029 = new JPanel(new GridLayout(4, 4, 5, 5));
        panel029.setBackground(Color.WHITE);
        panel029.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear paneles con botones de prueba
        panel030 = new JPanel();
        panel030.setPreferredSize(new Dimension(200, 200));
        estado = new JLabel("");
        Image imgTaladro = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgTaladrosena.png")).getImage();
        Icon imagen5 = new ImageIcon(imgTaladro);
        estado.setHorizontalAlignment(SwingConstants.CENTER);
        prueba = crearBoton("", imagen5, new Color(255, 255, 255), Color.white);
        panel030.setLayout(new BorderLayout());
        panel030.add(estado, BorderLayout.NORTH);
        panel030.add(prueba);
        panel030.setBackground(new Color(228, 230, 233));

        panel031 = new JPanel();
        panel031.setPreferredSize(new Dimension(200, 200));
        estado1 = new JLabel("");
        Image imgMul = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgMultimetro.png")).getImage();
        Icon imagen6 = new ImageIcon(imgMul);
        prueba1 = crearBoton("", imagen6, new Color(255, 255, 255), Color.white);
        estado1.setHorizontalAlignment(SwingConstants.CENTER);
        panel031.setLayout(new BorderLayout());
        panel031.add(estado1, BorderLayout.NORTH);
        panel031.setBackground(new Color(228, 230, 233));
        panel031.add(prueba1);

        panel032 = new JPanel();
        panel032.setPreferredSize(new Dimension(200, 200));
        estado2 = new JLabel("");
        Image imgCompu = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgLaptop.png")).getImage();
        Icon imagen11 = new ImageIcon(imgCompu);
        prueba2 = crearBoton("", imagen11, new Color(255, 255, 255), Color.white);
        estado2.setHorizontalAlignment(SwingConstants.CENTER);
        panel032.setLayout(new BorderLayout());
        panel032.add(estado2, BorderLayout.NORTH);
        panel032.setBackground(new Color(228, 230, 233));
        panel032.add(prueba2);

        panel033 = new JPanel();
        panel033.setPreferredSize(new Dimension(200, 200));
        estado3 = new JLabel("");
        panel033.setLayout(new BorderLayout());
        Image imgOsi = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgOsciloscopio.png")).getImage();
        Icon imagen12 = new ImageIcon(imgOsi);
        prueba3 = crearBoton("", imagen12, new Color(255, 255, 255), Color.white);
        estado3.setHorizontalAlignment(SwingConstants.CENTER);
        panel033.add(estado3, BorderLayout.NORTH);
        panel033.setBackground(new Color(228, 230, 233));
        panel033.add(prueba3);

        panel034 = new JPanel();
        panel034.setPreferredSize(new Dimension(200, 200));
        panel034.setLayout(new BorderLayout());
        estado4 = new JLabel("");
        Image imgCau = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgCautin.png")).getImage();
        Icon imagen13 = new ImageIcon(imgCau);
        prueba4 = crearBoton("", imagen13, new Color(255, 255, 255), Color.white);
        estado4.setHorizontalAlignment(SwingConstants.CENTER);
        panel034.add(estado4, BorderLayout.NORTH);
        panel034.setBackground(new Color(228, 230, 233));
        panel034.add(prueba4);

        panel035 = new JPanel();
        panel035.setPreferredSize(new Dimension(200, 200));
        panel035.setLayout(new BorderLayout());
        estado5 = new JLabel("");
        Image imgTorq = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgTorque.png")).getImage();
        Icon imagen14 = new ImageIcon(imgTorq);
        prueba5 = crearBoton("", imagen14, new Color(255, 255, 255), Color.white);
        estado5.setHorizontalAlignment(SwingConstants.CENTER);
        panel035.add(estado5, BorderLayout.NORTH);
        panel035.setBackground(new Color(228, 230, 233));
        panel035.add(prueba5);

        panel036 = new JPanel();
        panel036.setPreferredSize(new Dimension(200, 200));
        panel036.setLayout(new BorderLayout());
        estado6 = new JLabel("");
        Image imgPboar = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgProtoboard.png")).getImage();
        Icon imagen15 = new ImageIcon(imgPboar);
        prueba6 = crearBoton("", imagen15, new Color(255, 255, 255), Color.white);
        estado6.setHorizontalAlignment(SwingConstants.CENTER);
        panel036.add(estado6, BorderLayout.NORTH);
        panel036.setBackground(new Color(228, 230, 233));
        panel036.add(prueba6);

        panel037 = new JPanel();
        panel037.setPreferredSize(new Dimension(200, 200));
        panel037.setLayout(new BorderLayout());
        estado7 = new JLabel("");
        Image imgArdu = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgArduino.png")).getImage();
        Icon imagen16 = new ImageIcon(imgArdu);
        prueba7 = crearBoton("", imagen16, new Color(255, 255, 255), Color.white);
        estado7.setHorizontalAlignment(SwingConstants.CENTER);
        panel037.add(estado7, BorderLayout.NORTH);
        panel037.setBackground(new Color(228, 230, 233));
        panel037.add(prueba7);

        panel038 = new JPanel();
        panel038.setPreferredSize(new Dimension(200, 200));
        panel038.setLayout(new BorderLayout());
        estado8 = new JLabel("");
        Image imgPinza = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgPinza.png")).getImage();
        Icon imagen17 = new ImageIcon(imgPinza);
        prueba8 = crearBoton("", imagen17, new Color(255, 255, 255), Color.white);
        estado8.setHorizontalAlignment(SwingConstants.CENTER);
        panel038.add(estado8, BorderLayout.NORTH);
        panel038.setBackground(new Color(228, 230, 233));
        panel038.add(prueba8);

        panel039 = new JPanel();
        panel039.setPreferredSize(new Dimension(200, 200));
        panel039.setLayout(new BorderLayout());
        estado9 = new JLabel("");
        Image imgRouter = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgRouter.png")).getImage();
        Icon imagen18 = new ImageIcon(imgRouter);
        prueba9 = crearBoton("", imagen18, new Color(255, 255, 255), Color.white);
        estado9.setHorizontalAlignment(SwingConstants.CENTER);
        panel039.add(estado9, BorderLayout.NORTH);
        panel039.setBackground(new Color(228, 230, 233));
        panel039.add(prueba9);

        panel040 = new JPanel();
        panel040.setPreferredSize(new Dimension(200, 200));
        panel040.setLayout(new BorderLayout());
        estado10 = new JLabel("");
        Image imgProyector = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgProyector.png")).getImage();
        Icon imagen19 = new ImageIcon(imgProyector);
        prueba10 = crearBoton("", imagen19, new Color(255, 255, 255), Color.white);
        estado10.setHorizontalAlignment(SwingConstants.CENTER);
        panel040.add(estado10, BorderLayout.NORTH);
        panel040.setBackground(new Color(228, 230, 233));
        panel040.add(prueba10);

        panel041 = new JPanel();
        panel041.setPreferredSize(new Dimension(200, 200));
        panel041.setLayout(new BorderLayout());
        estado11 = new JLabel("");
        Image imgMouse = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgMouse.png")).getImage();
        Icon imagen20 = new ImageIcon(imgMouse);
        prueba11 = crearBoton("", imagen20, new Color(255, 255, 255), Color.white);
        estado11.setHorizontalAlignment(SwingConstants.CENTER);
        panel041.add(estado11, BorderLayout.NORTH);
        panel041.setBackground(new Color(228, 230, 233));
        panel041.add(prueba11);

        panel042 = new JPanel();
        panel042.setPreferredSize(new Dimension(200, 200));
        panel042.setLayout(new BorderLayout());
        estado12 = new JLabel("");
        Image imgPorL = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgLaptop.png")).getImage();
        Icon imagen21 = new ImageIcon(imgPorL);
        prueba12 = crearBoton("", imagen21, new Color(255, 255, 255), Color.white);
        estado12.setHorizontalAlignment(SwingConstants.CENTER);
        panel042.add(estado12, BorderLayout.NORTH);
        panel042.setBackground(new Color(228, 230, 233));
        panel042.add(prueba12);

        panel043 = new JPanel();
        panel043.setPreferredSize(new Dimension(200, 200));
        panel043.setLayout(new BorderLayout());
        estado13 = new JLabel("");
        Image imgPorA = new ImageIcon(getClass().getResource("/proyect_loansys/img/imgLaptop.png")).getImage();
        Icon imagen22 = new ImageIcon(imgPorA);
        prueba13 = crearBoton("", imagen22, new Color(255, 255, 255), Color.white);
        estado13.setHorizontalAlignment(SwingConstants.CENTER);
        panel043.add(estado13, BorderLayout.NORTH);
        panel043.setBackground(new Color(228, 230, 233));
        panel043.add(prueba13);

        // Agregar todos los botones al panel029
        panel029.add(panel043);
        panel029.add(panel042);
        panel029.add(panel041);
        panel029.add(panel040);
        panel029.add(panel039);
        panel029.add(panel038);
        panel029.add(panel037);
        panel029.add(panel036);
        panel029.add(panel035);
        panel029.add(panel034);
        panel029.add(panel033);
        panel029.add(panel032);
        panel029.add(panel031);
        panel029.add(panel030);

        // Scroll pane para los elementos
        miscroll = new JScrollPane(panel029);
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        miscroll.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        panel8.add(miscroll, BorderLayout.CENTER);

        // ========== AGREGAR PANELES PRINCIPALES ==========
        panel5.add(panel6, BorderLayout.NORTH);
        panel5.add(panel7, BorderLayout.CENTER);
        panel5.add(panel8, BorderLayout.SOUTH);

        panel4.add(panel5);
    }

    private JButton crearBoton(String texto, Icon icono, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto, icono) {
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
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}