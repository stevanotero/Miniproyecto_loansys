/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Usuario_Inventario extends Usuario_Plantilla{
     private Container contenedor;
    private JPanel panel4,panel5,panel6,panel7,panel8,panel9,panel010,panel011,
            panel012,panel013,panel014,panel015,panel016,panel017,panel018,
            panel019,panel020,panel021,panel022,panel023,panel024,panel025,
            panel026,panel027,panel028,panel029,panel030,panel031,panel032,
            panel033,panel034,panel035,panel036,panel037,panel038,panel039,
            panel040,panel041,panel042,panel043;
    
    private JLabel inventario,fEstado,codigo,
            text1,text2,text3,text4;
    private JTextField lcodigo;
    private String lista[] = {"","Disponible","Mantenimiento","Ocupado"};
    private JComboBox listaEstado;
    public JButton buscador,perifericos,computadores,herramientas,maquinas,prueba,
            prueba1,prueba2,prueba3,prueba4,prueba5,prueba6,prueba7,prueba8,prueba9,prueba10,
            prueba11,prueba12,prueba13;
    private GridLayout grid,grid2,grid3,grid4,grid5,grid6,grid7;
    private JScrollPane miscroll;
    
    
    public Usuario_Inventario(String titulo, String rol, String nombre) {
        super(titulo, rol, nombre);
        
        contenedor = super.getContainer();
        panel4 = super.getPanel();
        
        panel5 = new JPanel();
        
       
        panel5.setPreferredSize(new Dimension(1100, 600));
        grid = new GridLayout(3,1,3,3);
        panel5.setBackground(Color.WHITE);
        panel5.setLayout(grid);
        
        
        //////////////////////////////////////////////////////////
        
        panel6 = new JPanel();
        
        grid2 = new GridLayout(1,2,4,4);
        panel6.setLayout(grid2);
        
        
        
        panel6.setBackground(Color.white);
        
        
        /////////////////////////////////////
        
        panel7 = new JPanel();
        grid4 = new GridLayout(1,4,4,4);
        panel7.setLayout(grid4);
        panel7.setBackground(Color.WHITE);
     
//////////////////////////////////////////////////
        panel8 = new JPanel();
        panel8.setBackground(Color.white);
        panel8.setLayout(null);
        
        
        /////////////////////////////////////////
        
        //Panel del primer grid Del panel 1
        panel9 = new JPanel();
        
        panel9.setBackground(Color.white);
        /////////////////////////////////////
        // Panel de Primer grid Del panel 1
        panel010 = new JPanel();
        panel010.setBackground(Color.white);
        //////////////////////////
        
        panel011 = new JPanel();
        panel011.setPreferredSize(new Dimension(550, 20));
        grid3 = new GridLayout(1,4,7,2);
        panel011.setLayout(grid3);
        
        fEstado = new JLabel("Filtro de estado");
        listaEstado = new JComboBox(lista);
        codigo = new JLabel("busqueda por nombre");
        lcodigo = new JTextField("");
        panel011.add(fEstado);
        panel011.add(listaEstado);
        panel011.add(codigo);
        panel011.add(lcodigo);
        panel011.setBackground(Color.white);
        
        //////////////////////////////////////
        panel012 = new JPanel();
        panel012.setPreferredSize(new Dimension(500, 20));
        panel012.setLayout(new BorderLayout());
        Icon imagen1 = new ImageIcon("iconscasa24.png");
        buscador = new JButton("Buscar...",imagen1);
        panel012.add((buscador),BorderLayout.EAST);
        panel012.setBackground(Color.white);
        ///////////////////////////////////
        panel013 = new JPanel();
        
        panel013.setBackground(Color.WHITE);
        //////////////////////////////////
        panel014 = new JPanel();
        
        panel014.setBackground(Color.WHITE);
        ///////////////////////////////////
        panel015 = new JPanel();
        
        panel015.setBackground(Color.WHITE);
        ///////////////////////////////////
        panel016 = new JPanel();
        
        panel016.setBackground(Color.WHITE);
        ///////////////////////////////////
        panel017 = new JPanel();
        panel017.setPreferredSize(new Dimension(200, 180));
        panel017.setLayout(new BorderLayout());
        panel017.setBackground(Color.WHITE);
        
        panel021 = new JPanel();
        panel021.setPreferredSize(new Dimension(20, 40));
        panel021.setLayout(new BorderLayout());
        text1 = new JLabel("Computadores",SwingConstants.CENTER);
        
        
        panel021.setBackground(Color.WHITE);
        panel021.add(text1);
        text1.setForeground(Color.BLACK);
        
        panel017.add(panel021,BorderLayout.NORTH);
        
        panel022 = new JPanel();
        panel022.setPreferredSize(new Dimension(100, 80));
        panel022.setLayout(new BorderLayout());
        Icon image1 = new ImageIcon("iconscasa24.png");
        computadores = new JButton("",image1);
        computadores.setIcon(imagen1);
        computadores.setHorizontalTextPosition(SwingConstants.CENTER);
        panel022.add(computadores);
        panel017.add(panel022,BorderLayout.CENTER);
        
        ///////////////////////////////////////////////////
        panel018 = new JPanel();
        panel018.setLayout(new BorderLayout());
        panel018.setPreferredSize(new Dimension(200, 180));
        panel018.setLayout(new BorderLayout());
        panel018.setBackground(Color.WHITE);
        
        
        panel023 = new JPanel();
        panel023.setPreferredSize(new Dimension(20, 40));
        panel023.setLayout(new BorderLayout());
        text2 = new JLabel("Perifericos",SwingConstants.CENTER);
        
        
        panel023.setBackground(Color.WHITE);
        panel023.add(text2);
        text2.setForeground(Color.BLACK);
        
        panel018.add(panel023,BorderLayout.NORTH);
        
        panel024 = new JPanel();
        panel024.setPreferredSize(new Dimension(100, 80));
        panel024.setLayout(new BorderLayout());
        Icon image2 = new ImageIcon("iconscasa24.png");
        perifericos = new JButton("",image2);
        perifericos.setIcon(imagen1);
        perifericos.setHorizontalTextPosition(SwingConstants.CENTER);
        panel024.add(perifericos);
        panel018.add(panel024,BorderLayout.CENTER);
        
        ///////////////////////////////////////
        panel019 = new JPanel();
        panel019.setPreferredSize(new Dimension(200, 180));
        panel019.setLayout(new BorderLayout());
        panel019.setBackground(Color.WHITE);
        
        panel025 = new JPanel();
        panel025.setPreferredSize(new Dimension(20, 40));
        panel025.setLayout(new BorderLayout());
        text3 = new JLabel("Herramientas",SwingConstants.CENTER);
        
        
        panel025.setBackground(Color.WHITE);
        panel025.add(text3);
        text3.setForeground(Color.BLACK);
        
        panel019.add(panel025,BorderLayout.NORTH);
        
        panel026 = new JPanel();
        panel026.setPreferredSize(new Dimension(100, 80));
        panel026.setLayout(new BorderLayout());
        Icon image3 = new ImageIcon("iconscasa24.png");
        herramientas = new JButton("",image3);
        herramientas.setIcon(imagen1);
        herramientas.setHorizontalTextPosition(SwingConstants.CENTER);
        panel026.add(herramientas);
        panel019.add(panel026,BorderLayout.CENTER);
        
        //////////////////////////////////////////////////////////////
        panel020 = new JPanel();
        panel020.setPreferredSize(new Dimension(200, 180));
        //maquinas;
        panel020.setLayout(new BorderLayout());
        panel020.setBackground(Color.WHITE);
        
        panel020 = new JPanel();
        panel020.setPreferredSize(new Dimension(200, 180));
        panel020.setLayout(new BorderLayout());
        panel020.setBackground(Color.WHITE);
        
        panel027 = new JPanel();
        panel027.setPreferredSize(new Dimension(20, 40));
        panel027.setLayout(new BorderLayout());
        text4 = new JLabel("Maquinas",SwingConstants.CENTER);
        
        
        panel027.setBackground(Color.WHITE);
        panel027.add(text4);
        text4.setForeground(Color.BLACK);
        
        panel020.add(panel027,BorderLayout.NORTH);
        
        panel028 = new JPanel();
        panel028.setPreferredSize(new Dimension(100, 80));
        panel028.setLayout(new BorderLayout());
        Icon image4 = new ImageIcon("iconscasa24.png");
        maquinas = new JButton("",image4);
        maquinas.setIcon(imagen1);
        maquinas.setHorizontalTextPosition(SwingConstants.CENTER);
        panel028.add(maquinas);
        panel020.add(panel028,BorderLayout.CENTER);
        ////////////////////////////////////////////////////
        
        //panel8    
        
        //Scroll de del panel8
        miscroll = new JScrollPane();
        miscroll.setBounds(10, 10, 1080,180);
        
        panel029 = new JPanel();
        panel029.setPreferredSize(new Dimension(1080, 600));
        
        
        panel029.setBackground(Color.white);
        miscroll.setViewportView(panel029);
       
        
        panel029.setBackground(Color.WHITE);

        panel8.add(miscroll);
        
        /////////////////////////////////////////////////////
        
        
        
        panel030 = new JPanel();

        panel030.setPreferredSize(new Dimension(200, 145));
        prueba = new JButton("1");
        panel030.setLayout(new BorderLayout());
        panel030.add(prueba);
        panel030.setBackground(Color.CYAN);
        
        panel031 = new JPanel();
        panel031.setPreferredSize(new Dimension(200, 145));
        prueba1 = new JButton("2");
        panel031.setLayout(new BorderLayout());
        panel031.add(prueba1);
        
        panel032 = new JPanel();
        panel032.setPreferredSize(new Dimension(200, 145));
        prueba2 = new JButton("3");
        panel032.setLayout(new BorderLayout());
        panel032.add(prueba2);
        
        panel033 = new JPanel();
        panel033.setPreferredSize(new Dimension(200, 145));
        prueba3 = new JButton("4");
        panel033.setLayout(new BorderLayout());
        panel033.add(prueba3);
        
        
        panel034 = new JPanel();
        panel034.setPreferredSize(new Dimension(200, 145));
        prueba4 = new JButton("5");
        panel034.setLayout(new BorderLayout());
        panel034.add(prueba4);
        
        panel035 = new JPanel();
        panel035.setPreferredSize(new Dimension(200, 145));
        prueba5 = new JButton("6");
        panel035.setLayout(new BorderLayout());
        panel035.add(prueba5);
        
        panel036 = new JPanel();
        panel036.setPreferredSize(new Dimension(200, 145));
        prueba6 = new JButton("7");
        panel036.setLayout(new BorderLayout());
        panel036.add(prueba6);
        
        panel037 = new JPanel();
        panel037.setPreferredSize(new Dimension(200, 145));
        prueba7 = new JButton("8");
        panel037.setLayout(new BorderLayout());
        panel037.add(prueba7);
        
        panel038 = new JPanel();
        panel038.setPreferredSize(new Dimension(200, 145));
        prueba8 = new JButton("9");
        panel038.setLayout(new BorderLayout());
        panel038.add(prueba8);
        
        panel039 = new JPanel();
        panel039.setPreferredSize(new Dimension(200, 145));
        prueba9 = new JButton("10");
        panel039.setLayout(new BorderLayout());
        panel039.add(prueba9);
        
        
        panel040 = new JPanel();
        panel040.setPreferredSize(new Dimension(200, 145));
        prueba10 = new JButton("11");
        panel040.setLayout(new BorderLayout());
        panel040.add(prueba10);
        
        panel041 = new JPanel();
        panel041.setPreferredSize(new Dimension(200, 145));
        prueba11 = new JButton("12");
        panel041.setLayout(new BorderLayout());
        panel041.add(prueba11);
        
        panel042 = new JPanel();
        panel042.setPreferredSize(new Dimension(200, 145));
        prueba12 = new JButton("13");
        panel042.setLayout(new BorderLayout());
        panel042.add(prueba12);
        
        panel043 = new JPanel();
        panel043.setPreferredSize(new Dimension(200, 145));
        prueba13 = new JButton("14");
        panel043.setLayout(new BorderLayout());
        panel043.add(prueba13);
        
        
        
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
        
        
        panel6.add(panel9);
        panel9.add(panel011);
        
        ////////////////////////
        panel6.add(panel010);
        
        
        panel010.add(panel012);
        //////////////////////////////////////
        panel5.add(panel6);
        /////////////////////
        /////////////////////
        
        panel013.add(panel017);
        
        panel7.add(panel013);
        
        //////////////////////////
        panel014.add(panel018);
        panel7.add(panel014);
        
        /////////////////////////
        panel015.add(panel019);
        panel7.add(panel015);
        
        ///////////////////////////
        panel016.add(panel020);
        panel7.add(panel016);
        
        ////////////////////////////
        panel5.add(panel7);
        
        /////////////////////////
        
        panel5.add(panel8);
        panel4.add(panel5);
    }
}
