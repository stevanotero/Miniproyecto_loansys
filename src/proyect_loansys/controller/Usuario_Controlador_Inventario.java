/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import proyect_loansys.view.Usuario_HistorialPrestamo;
import proyect_loansys.view.Usuario_Inicio;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_Notificacion;
import proyect_loansys.view.Usuario_SolicitarPrestamo;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Notificaciones;

public class Usuario_Controlador_Inventario implements ActionListener{
    
    Usuario_Inventario usuInve;
    Usuario_Inicio inicio;
    Usuario_HistorialPrestamo historial;
    Usuario_Notificacion notificaciones;
    Usuario_SolicitarPrestamo solicitar;
    Usuario_ControladorDatos controladorDatos;
    private String nombreUsuario;
    private String rolUsuario;
    
    public Usuario_Controlador_Inventario(Usuario_Inicio inicio, String nombreUsuario, String rolUsuario){
        this.inicio = inicio;
        this.nombreUsuario = nombreUsuario;
        this.rolUsuario = rolUsuario;
        
        this.usuInve = new Usuario_Inventario(rolUsuario, "", "");
        this.historial = new Usuario_HistorialPrestamo(rolUsuario, "", "");
        this.notificaciones = new Usuario_Notificacion(rolUsuario, "", "");
        this.solicitar = new Usuario_SolicitarPrestamo(rolUsuario, "", "");
        
        this.usuInve.iniciod.addActionListener(this);
        this.usuInve.prestamo.addActionListener(this);
        this.usuInve.notificacion.addActionListener(this);
        
        
        
    }
    @Override
   
    public void actionPerformed (ActionEvent e){
        
        if(e.getSource() == usuInve.iniciod){
            CargarInicio(inicio);
            usuInve.dispose();
        }
        
        if(e.getSource() == usuInve.prestamo){
            CargarHistorial(historial);
            controladorDatos.mostrarH(historial.tabla);
            usuInve.dispose();
        }
    
        
        if (e.getSource() == usuInve.notificacion){
            CargarNotificacion(notificaciones);
            Vista_Notificaciones vistaNo = new Vista_Notificaciones();
            Controlador_Notificaciones controlNo = new Controlador_Notificaciones(vistaNo);
            usuInve.dispose();
        }
        if (e.getSource() == usuInve.cerrarS) {
            usuInve.setVisible(false);
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }

    }
    
    public void CargarInicio(Usuario_Inicio inicio) {
        inicio.setVisible(true);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//

    public void CargarInventario(Usuario_Inventario usuInve) {
        usuInve.setVisible(true);
        usuInve.setExtendedState(JFrame.MAXIMIZED_BOTH);
        usuInve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarHistorial(Usuario_HistorialPrestamo historial) {
        historial.setVisible(true);
        historial.setExtendedState(JFrame.MAXIMIZED_BOTH);
        historial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarNotificacion(Usuario_Notificacion noti) {
        noti.setVisible(true);
        noti.setExtendedState(JFrame.MAXIMIZED_BOTH);
        noti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarSolicitud(Usuario_SolicitarPrestamo solicitar) {
        solicitar.setVisible(true);
        solicitar.setExtendedState(JFrame.MAXIMIZED_BOTH);
        solicitar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarInicioS(Vista_Login sesion) {
        sesion.setVisible(true);
        sesion.setExtendedState(JFrame.MAXIMIZED_BOTH);
        sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
