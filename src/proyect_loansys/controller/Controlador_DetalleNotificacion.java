/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;
import proyect_loansys.model.Notificaciones;
import proyect_loansys.model.NotificacionesDAO;
import proyect_loansys.view.Ventana_DetalleNotificacion;

/**
 *
 * @author Alexis
 */
public class Controlador_DetalleNotificacion implements ActionListener {

    private Ventana_DetalleNotificacion vistaDetalle;
    private Notificaciones notificacion;
    private NotificacionesDAO notificacionesDao;
    private Controlador_Notificaciones controladorPadre;

    public Controlador_DetalleNotificacion(Ventana_DetalleNotificacion vistaDetalle, Notificaciones notificacion, Controlador_Notificaciones controladorPadre) {
        this.vistaDetalle = vistaDetalle;
        this.notificacion = notificacion;
        this.controladorPadre = controladorPadre;
        this.notificacionesDao = new NotificacionesDAO();
        cargarCampos();
        this.vistaDetalle.botonMarcarLeido.addActionListener(this);
        this.vistaDetalle.botonCerrar.addActionListener(this);
    }

    private void cargarCampos() {
        if (notificacion != null) {
            vistaDetalle.textoRemitente.setText(notificacion.getNombreRemitente());
            vistaDetalle.textoRolRemitente.setText(notificacion.getRolRemitente());
            vistaDetalle.textoTipoNotificacion.setText(notificacion.getNombreTipoNotificacion());
            vistaDetalle.textoEstadoLectura.setText(notificacion.getNombreEstadoLectura());
            vistaDetalle.areaMensaje.setText(notificacion.getMensaje());

            // Si el estado ya es 1 (Leído) o el texto indica "Leído", deshabilitamos el botón
            if (notificacion.getIdEstadoLectura() == 1 || "Leído".equalsIgnoreCase(notificacion.getNombreEstadoLectura())) {
                vistaDetalle.botonMarcarLeido.setEnabled(false);
                vistaDetalle.botonMarcarLeido.setText("Ya Leído");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaDetalle.botonCerrar) {
            vistaDetalle.dispose();
        }

        if (e.getSource() == vistaDetalle.botonMarcarLeido) {
            boolean exito = notificacionesDao.marcarComoLeido(notificacion.getIdNotificacion());

            if (exito) {
                // Registrar acción en Auditoría
                Administrador_Auditoria auditoria = new Administrador_Auditoria();
                auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
                auditoria.setAccion("Lectura de notificación ID: " + notificacion.getIdNotificacion());
                new Administrador_AuditoriaDao().registrarAccion(auditoria);

                JOptionPane.showMessageDialog(null, "La notificación se ha marcado como leída.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Actualizar componentes visuales de la ventana modal
                vistaDetalle.textoEstadoLectura.setText("Leído");
                vistaDetalle.botonMarcarLeido.setEnabled(false);
                vistaDetalle.botonMarcarLeido.setText("Ya Leído");
                if (controladorPadre != null) {
                    controladorPadre.listarNotificacionesTabla();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar el estado de la notificación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}