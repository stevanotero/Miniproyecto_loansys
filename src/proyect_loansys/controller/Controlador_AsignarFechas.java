/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;
import proyect_loansys.model.PrestamosDao;
import proyect_loansys.model.Solicitudes;
import proyect_loansys.view.VentanaAsignarFechas;

/**
 *
 * @author Alexis
 */
public class Controlador_AsignarFechas implements ActionListener {

    private VentanaAsignarFechas vistaModal;
    private Solicitudes solicitud;
    private Controlador_Solicitudes controladorPadre;
    private PrestamosDao prestamosDao;

    public Controlador_AsignarFechas(VentanaAsignarFechas vistaModal, Solicitudes solicitud, Controlador_Solicitudes controladorPadre) {
        this.vistaModal = vistaModal;
        this.solicitud = solicitud;
        this.controladorPadre = controladorPadre;
        this.prestamosDao = new PrestamosDao();

        this.vistaModal.botonConfirmar.addActionListener(this);
        this.vistaModal.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Dar clic al boton de confirmar fecha para el prestamo
        if (e.getSource() == vistaModal.botonConfirmar) {

            //Comprobar si el usuario ya tiene un préstamo activo
            if (prestamosDao.usuarioTienePrestamoActivo(solicitud.getIdUsuario())) {
                JOptionPane.showMessageDialog(vistaModal,
                        "El usuario ya cuenta con un préstamo activo en el sistema.\n"
                        + "No se le puede aprobar otro elemento hasta que devuelva el que tiene actualmente.",
                        "Préstamo Denegado",
                        JOptionPane.WARNING_MESSAGE);
                vistaModal.dispose();
                return;
            }

            String fechaTexto = vistaModal.textoFechaDevolucion.getText().trim();
            
            //Validacion de campos vacios 
            if (fechaTexto.isEmpty()) {
                JOptionPane.showMessageDialog(vistaModal, "Por favor, ingrese la fecha límite de devolución.");
                return;
            }

            try {
                LocalDate fechaLimite = LocalDate.parse(fechaTexto);
                LocalDate hoy = LocalDate.now();

                long diasDePrestamo = ChronoUnit.DAYS.between(hoy, fechaLimite);
                
                //Validacion sobre fecha antes de la actual
                if (diasDePrestamo < 0) {
                    JOptionPane.showMessageDialog(vistaModal, "Error: La fecha límite no puede ser un día anterior a hoy.");
                    return;
                }

                String rolUsuario = "";
                if (solicitud != null && solicitud.getNombreRol() != null) {
                    rolUsuario = solicitud.getNombreRol().toUpperCase().trim();
                } else {
                    rolUsuario = "OTROS";
                }

                //El aprendiz solo puede solicitar para el mismo día
                if (rolUsuario.contains("APRENDIZ")) {
                    if (diasDePrestamo != 0) {
                        JOptionPane.showMessageDialog(vistaModal,
                                "Los usuarios con rol APRENDIZ solo pueden solicitar elementos para el MISMO DÍA.\n"
                                + "Por favor, asigne la fecha de hoy: " + hoy);
                        return;
                    }
                } //El instructor puede tener un máximo de 2 días
                else if (rolUsuario.contains("INSTRUCTOR")) {
                    if (diasDePrestamo > 2) {
                        JOptionPane.showMessageDialog(vistaModal,
                                "Los INSTRUCTORES pueden tener el elemento un máximo de 2 días.\n"
                                + "La fecha máxima permitida para este préstamo es: " + hoy.plusDays(2));
                        return;
                    }
                }
                
                //Hora del prestamo definida siempre hasta las 5
                String fechaHoraFinal = fechaTexto + " 17:00:00";
                int idCategoriaProvicional = 1;
                boolean exito = prestamosDao.registrarPrestamoAprobado(solicitud, fechaHoraFinal, idCategoriaProvicional);
                
                //Mensaje de exito 
                if (exito) {
                    JOptionPane.showMessageDialog(null, "¡Préstamo aprobado y registrado correctamente!");
                    if (controladorPadre != null) {
                        controladorPadre.listarSolicitudesTabla();

                        Administrador_Auditoria auditoria = new Administrador_Auditoria();
                        auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
                        auditoria.setAccion("Prestamo aprobado");
                        new Administrador_AuditoriaDao().registrarAccion(auditoria);
                    }

                    vistaModal.dispose();
                }
                
                //Validacion sobre el formato incorrecto y  errores inesperados
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(vistaModal, "Formato de fecha inválido. Por favor use: YYYY-MM-DD (Ejemplo: 2026-07-26)");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vistaModal, "Error inesperado en el sistema:\n" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        //Clic al boton cancelar
        if (e.getSource() == vistaModal.botonCancelar) {
            vistaModal.dispose();
        }
    }
}
