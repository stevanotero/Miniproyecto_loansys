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
    private Object controladorPadre;

    // Se cambia el tercer parámetro a Object
    public Controlador_DetalleNotificacion(Ventana_DetalleNotificacion vistaDetalle, Notificaciones notificacion, Object controladorPadre) {
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
        }
    }

    private void refrescarTablaPadre() {
        if (controladorPadre != null) {
            if (controladorPadre instanceof Controlador_Notificaciones) {
                ((Controlador_Notificaciones) controladorPadre).listarNotificacionesTabla();
            } else if (controladorPadre instanceof Controlador_NotificacionesTecnico) {
                ((Controlador_NotificacionesTecnico) controladorPadre).listarNotificacionesTabla();
            } else if (controladorPadre instanceof Controlador_NotificacionesUsuario) {
                ((Controlador_NotificacionesUsuario) controladorPadre).listarNotificacionesTabla();
            } else if (controladorPadre instanceof Administrador_ControladorNotificaciones) {
                ((Administrador_ControladorNotificaciones) controladorPadre).listarNotificacionesTabla();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaDetalle.botonCerrar) {
            vistaDetalle.dispose();
        }

        if (e.getSource() == vistaDetalle.botonMarcarLeido) {
            boolean exito = notificacionesDao.eliminarNotificacion(notificacion.getIdNotificacion());

            if (exito) {
                try {
                    Administrador_Auditoria auditoria = new Administrador_Auditoria();
                    auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
                    auditoria.setAccion("Lectura y eliminación de notificación ID: " + notificacion.getIdNotificacion());
                    new Administrador_AuditoriaDao().registrarAccion(auditoria);
                } catch (Exception exAud) {
                    System.err.println("Error en auditoría: " + exAud.getMessage());
                }

                JOptionPane.showMessageDialog(null, "Notificación leída y eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                vistaDetalle.dispose();
                refrescarTablaPadre();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al eliminar la notificación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
