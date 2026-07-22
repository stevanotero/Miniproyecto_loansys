/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import proyect_loansys.model.Solicitudes;
import proyect_loansys.model.PrestamosDao; 
import proyect_loansys.view.VentanaMotivoRechazo;

/**
 *
 * @author Alexis
 */
public class Controlador_RechazarSolicitud implements ActionListener {

    private VentanaMotivoRechazo vistaRechazo;
    private Solicitudes solicitud;
    private Controlador_Solicitudes controladorPadre;
    private PrestamosDao prestamosDao; 

    public Controlador_RechazarSolicitud(VentanaMotivoRechazo vistaRechazo, Solicitudes solicitud, Controlador_Solicitudes controladorPadre) {
        this.vistaRechazo = vistaRechazo;
        this.solicitud = solicitud;
        this.controladorPadre = controladorPadre;
        this.prestamosDao = new PrestamosDao(); 

        // Escuchadores de botones
        this.vistaRechazo.botonConfirmarRechazo.addActionListener(this);
        this.vistaRechazo.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaRechazo.botonCancelar) {
            vistaRechazo.dispose();
        }

        if (e.getSource() == vistaRechazo.botonConfirmarRechazo) {
            // Capturamos el texto limpiando espacios al inicio y al final
           String motivo = vistaRechazo.areaMotivoRechazo.getText().trim();
           
            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El motivo del rechazo no puede quedar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (motivo.length() < 10){
            JOptionPane.showMessageDialog(null, "El motivo es muy corto. Minimo 10 caracteres (Llevas: " + motivo.length() + ").", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (motivo.length() > 60) {
                JOptionPane.showMessageDialog(null, "El motivo es muy largo. Máximo 60 caracteres (Llevas: " + motivo.length() + ").", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean exito = prestamosDao.rechazarSolicitudConNotificacion(solicitud.getIdSolicitud(), solicitud.getIdUsuario(), motivo, solicitud.getNombreElemento());

            if (exito) {
                JOptionPane.showMessageDialog(null, "La solicitud ha sido rechazada y el usuario notificado.");
                vistaRechazo.dispose();
                
                // Refrescamos la tabla principal de solicitudes pendientes
                if (controladorPadre != null) {
                    controladorPadre.listarSolicitudesTabla();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al procesar el rechazo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}