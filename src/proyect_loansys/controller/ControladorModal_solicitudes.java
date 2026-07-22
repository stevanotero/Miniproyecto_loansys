/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proyect_loansys.model.Solicitudes;
import proyect_loansys.view.VentanaGestionarSolicitud; 
import proyect_loansys.view.VentanaAsignarFechas;    
import proyect_loansys.view.VentanaMotivoRechazo;

/**
 *
 * @author Alexis
 */
public class ControladorModal_solicitudes implements ActionListener {

    private VentanaGestionarSolicitud vistaModal;
    private Solicitudes solicitud;
    private Controlador_Solicitudes controladorPadre; 

    //El constructor recibe los 3 parámetros obligatoriamente
    public ControladorModal_solicitudes(VentanaGestionarSolicitud vistaModal, Solicitudes solicitud, Controlador_Solicitudes controladorPadre) {
        this.vistaModal = vistaModal;
        this.solicitud = solicitud;          
        this.controladorPadre = controladorPadre; 
        this.vistaModal.botonAprobar.addActionListener(this);
        this.vistaModal.botonRechazar.addActionListener(this);
        this.vistaModal.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaModal.botonAprobar) {
            vistaModal.dispose();
            VentanaAsignarFechas modalFechas = new VentanaAsignarFechas(null);
            Controlador_AsignarFechas controllerAsignar = new Controlador_AsignarFechas(modalFechas, this.solicitud, this.controladorPadre);
            modalFechas.setVisible(true);
        }
        
        //clic al boton de rechazar
        if (e.getSource() == vistaModal.botonRechazar) {
            vistaModal.dispose();
            VentanaMotivoRechazo modalRechazo = new VentanaMotivoRechazo(null);
            Controlador_RechazarSolicitud controladorRechazo = new Controlador_RechazarSolicitud(modalRechazo, this.solicitud, this.controladorPadre);
            modalRechazo.setVisible(true);
        }

        // clic al botón cancelar
        if (e.getSource() == vistaModal.botonCancelar) {
            vistaModal.dispose();
        }
    }
}
