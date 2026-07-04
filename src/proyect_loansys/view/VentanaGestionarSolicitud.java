/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

import javax.swing.JDialog;
import javax.swing.BorderFactory;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaGestionarSolicitud extends JDialog {

    public JButton botonAprobar;
    public JButton botonRechazar;
    public JButton botonCancelar;
    public JTextField textoCodigoElemento;
    public JTextField textoNombreElemento;
    public JTextField textoUsuario;
    public JTextField textoDocumento;
    public JTextField textoRol;
    public JTextField textoFecha;
    public JTextField textoIdSolicitud;
    private JLabel lblIdSol;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblElemento;
    private JLabel lblUsuario;
    private JLabel lblDocumento;
    private JLabel lblRol;
    private JLabel lblFecha;
    private JPanel panelPrincipal;

    public VentanaGestionarSolicitud(Frame padre) {
        super(padre, true); 
        initComponents();
        setTitle("Gestionar Solicitud de Elemento");
        setSize(450, 480); 
        setLocationRelativeTo(padre);
        setResizable(false);
    }

    private void initComponents() {
        panelPrincipal = new javax.swing.JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        
        lblTitulo = new JLabel("DETALLES DE LA SOLICITUD");
        
        lblIdSol = new javax.swing.JLabel("ID Solicitud:");
        textoIdSolicitud = crearCampoRedondo();
        
        lblCodigo = new JLabel("Código Elemento:");
        textoCodigoElemento = crearCampoRedondo();

        lblElemento = new JLabel("Elemento:");
        textoNombreElemento = crearCampoRedondo();

        lblUsuario = new JLabel("Solicitante:");
        textoUsuario = crearCampoRedondo();

        lblDocumento = new JLabel("Documento:");
        textoDocumento = crearCampoRedondo();

        lblRol = new JLabel("Rol SENA:");
        textoRol = crearCampoRedondo();

        lblFecha = new JLabel("Fecha Envío:");
        textoFecha = crearCampoRedondo();

        // Estilos de botones circulares
        botonAprobar = crearBotonRedondo("Aprobar", new Color(40, 167, 69), Color.WHITE);
        botonRechazar = crearBotonRedondo("Rechazar", new Color(220, 53, 69), Color.WHITE);
        botonCancelar = crearBotonRedondo("Cancelar", new Color(230, 235, 240), Color.BLACK);
        
        panelPrincipal.setLayout(null); 
        
        // Titulo de la modal
        lblTitulo.setFont(new Font("Segoe UI", 1, 16));
        lblTitulo.setBounds(110, 20, 250, 25);
        panelPrincipal.add(lblTitulo);
        
        // ID de la solicitud
        lblIdSol.setFont(new Font("Segoe UI", 1, 13));
        lblIdSol.setBounds(40, 65, 130, 25);
        textoIdSolicitud.setBounds(180, 65, 220, 25);
        panelPrincipal.add(lblIdSol);
        panelPrincipal.add(textoIdSolicitud);

        //Codigo del elemento
        lblCodigo.setFont(new Font("Segoe UI", 1, 13));
        lblCodigo.setBounds(40, 105, 130, 25);
        textoCodigoElemento.setBounds(180, 105, 220, 25);
        panelPrincipal.add(lblCodigo);
        panelPrincipal.add(textoCodigoElemento);

        //Nombre del elemento
        lblElemento.setFont(new Font("Segoe UI", 1, 13));
        lblElemento.setBounds(40, 145, 130, 25);
        textoNombreElemento.setBounds(180, 145, 220, 25);
        panelPrincipal.add(lblElemento);
        panelPrincipal.add(textoNombreElemento);

        // Nombres de los solicitante
        lblUsuario.setFont(new Font("Segoe UI", 1, 13));
        lblUsuario.setBounds(40, 185, 130, 25);
        textoUsuario.setBounds(180, 185, 220, 25);
        panelPrincipal.add(lblUsuario);
        panelPrincipal.add(textoUsuario);

        // Documento de identificacion
        lblDocumento.setFont(new Font("Segoe UI", 1, 13));
        lblDocumento.setBounds(40, 225, 130, 25);
        textoDocumento.setBounds(180, 225, 220, 25);
        panelPrincipal.add(lblDocumento);
        panelPrincipal.add(textoDocumento);

        //Roles que cubren en el sena
        lblRol.setFont(new Font("Segoe UI", 1, 13));
        lblRol.setBounds(40, 265, 130, 25);
        textoRol.setBounds(180, 265, 220, 25);
        panelPrincipal.add(lblRol);
        panelPrincipal.add(textoRol);

        //Fecha del envio
        lblFecha.setFont(new Font("Segoe UI", 1, 13));
        lblFecha.setBounds(40, 305, 130, 25);
        textoFecha.setBounds(180, 305, 220, 25);
        panelPrincipal.add(lblFecha);
        panelPrincipal.add(textoFecha);

        //Botones bien puestos
        botonAprobar.setBounds(40, 365, 100, 35);
        panelPrincipal.add(botonAprobar);
        botonRechazar.setBounds(165, 365, 100, 35);
        panelPrincipal.add(botonRechazar);  
        botonCancelar.setBounds(290, 365, 110, 35);
        panelPrincipal.add(botonCancelar);
        
        getContentPane().add(panelPrincipal);
    }

    private JTextField crearCampoRedondo() {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(245, 246, 248));
                g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(215, 215, 215));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10);
                g2.dispose();
            }
        };
        campo.setEditable(false);
        campo.setOpaque(false);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setForeground(new Color(60, 60, 60));
        campo.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        return campo;
    }

    private JButton crearBotonRedondo(String texto, Color fondo, Color textoColor) {
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
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        return boton;
    }
}