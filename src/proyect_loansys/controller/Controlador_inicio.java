/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Solicitudes;
import proyect_loansys.view.Vista_Inicio;

/**
 *
 * @author Alexis
 */
public class Controlador_inicio implements ActionListener {

    public Vista_Inicio vista = new Vista_Inicio();

    public Controlador_inicio(Vista_Inicio vista) {
        this.vista = vista;
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonPrestamos.addActionListener(this);
        this.vista.botonDevoluciones.addActionListener(this);
        this.vista.botonReportes.addActionListener(this);
        this.vista.botonNotificaciones.addActionListener(this);
        this.vista.botonUsuarios.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //dar clic a cerrar sesion 
        if (e.getSource() == vista.botonCerrarSesion) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
        
        if(e.getSource() == vista.botonInventario){
        vista.dispose();
        Vista_Inventario vistaInventario = new Vista_Inventario();
        Controlador_inventario controladorIn = new Controlador_inventario(vistaInventario);
        vistaInventario.setVisible(true);
        }
        
        if(e.getSource() == vista.botonSolicitudes){
        vista.dispose();
        Vista_Solicitudes vistaSolicitud = new Vista_Solicitudes();
        Controlador_Solicitudes controladorSol = new Controlador_Solicitudes(vistaSolicitud);
        vistaSolicitud.setVisible(true);
        }
        
    }

}
