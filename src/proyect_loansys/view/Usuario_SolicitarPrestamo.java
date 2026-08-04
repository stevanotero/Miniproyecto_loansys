/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class Usuario_SolicitarPrestamo extends Usuario_Plantilla{
    private Container contenedor;
    private JPanel panel4,panel5,panel6,panel7,panel8,panel9,panel010,panel011,
            panel012,panel013,panel014,panel015,panel016;
    public JLabel texto1,texto2;
    public JButton volver,solicitar;
    public JTable tabla;
    public DefaultTableModel modelo;
    public JScrollPane miscroll;
    
    public Usuario_SolicitarPrestamo(String titulo, String rol, String nombre){
        
        super(titulo, rol, nombre);
        contenedor = super.getContainer();
        panel4 = super.getPanel();
        
        
        //Panel Principal
        panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(1100, 600));
        panel5.setBackground(Color.white);
        
        
        //Primer Panel de mensaje disponible
        panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        panel6.setPreferredSize(new Dimension(1000, 30));
        panel6.setBackground(Color.white);
        
        
        
        panel8 = new JPanel();
        panel8.setPreferredSize(new Dimension(300, 50));
        texto1 = new JLabel("",SwingConstants.CENTER);
        panel8.setLayout(new BorderLayout());
        texto1.setFont(texto1.getFont().deriveFont(Font.BOLD,22f));
        panel8.setBackground(Color.WHITE);
        panel8.add(texto1);
        panel6.add(panel8);
        
        ////////////////////////////////////////////////////////////////////////
        //Panel 2 de la tabla
        
        panel7 = new JPanel();
        panel7.setPreferredSize(new Dimension(1000, 200));
        panel7.setBackground(new Color(198, 198, 198));
        
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            } //para no poder editar los campos de la tabla
        };
        modelo.addColumn("Codigo Elemento");
        modelo.addColumn("Nombre Elemento");
        modelo.addColumn("Categoria");
        modelo.addColumn("Descripción");
     
        
        
        panel9 = new JPanel();
        panel9.setLayout(new BorderLayout());
        panel9.setPreferredSize(new Dimension(990, 170));

        tabla = new JTable(modelo);
        tabla.setRowHeight(40);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.setGridColor(new Color(235, 235, 235));

// Que las columnas se repartan el espacio sobrante (evita la columna vacía)
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(180);   // Codigo
        tabla.getColumnModel().getColumn(0).setMaxWidth(180);

        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);  // Nombre Elemento (antes muy angosto)
        tabla.getColumnModel().getColumn(1).setMaxWidth(250);

        tabla.getColumnModel().getColumn(2).setPreferredWidth(140);  // Categoria
        tabla.getColumnModel().getColumn(2).setMaxWidth(160);

        tabla.getColumnModel().getColumn(3).setPreferredWidth(250);  // Descripcion
// sin maxWidth, para que absorba el espacio restante
        miscroll=new JScrollPane(tabla);
        
        miscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        miscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel9.add(miscroll);
        panel9.setBackground(Color.white);
        
        panel7.add(panel9);
        
       
        ////////////////////////////////////////////////////////////////////////
        // Panel 3 sobre los botones        
        panel011 = new JPanel();
        panel011.setLayout(new BorderLayout());
        solicitar = crearBoton("Solicitar", new Color(34, 139, 34),Color.WHITE);
        panel011.setPreferredSize(new Dimension(200, 50));
        panel011.setBackground(new Color(198, 198, 198));
        panel011.add(solicitar);
        
        panel012 = new JPanel();
        panel012.setLayout(new BorderLayout());
        volver = crearBoton("Volver", new Color(220, 53, 69),Color.WHITE);
        panel012.add(volver);
        panel012.setPreferredSize(new Dimension(200, 50));
        panel012.setBackground(Color.white);
        
        
        panel010 = new JPanel();
        panel010.setBackground(Color.WHITE);
        panel010.setPreferredSize(new Dimension(800, 60));
        
        ////////////////////////////////////////////////////////////////////////
        
        //Panel de imagen//
        panel013 = new JPanel();
        panel013.setPreferredSize(new Dimension(400, 200));
        panel013.setBackground(Color.WHITE);
        
        
        
        panel014 = new JPanel();
        texto2 = new JLabel("");
        panel014.setPreferredSize(new Dimension(200, 190));
        texto2.setForeground(Color.black);
        panel014.setBackground(new Color(198,198,198));
        panel014.add(texto2);
        
        panel013.add(panel014);
        ////////////////////////////////////////
        panel010.add(panel012);
        panel010.add(panel011);
        //////////////////////////////////////////
        panel5.add(panel6);
        
        panel5.add(panel013);
        panel5.add(panel7,BorderLayout.CENTER);
        panel5.add(panel010);
        
        
        
         panel4.add(panel5);
        
    }
    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto) {
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