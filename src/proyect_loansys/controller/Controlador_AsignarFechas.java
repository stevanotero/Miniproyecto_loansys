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
import proyect_loansys.model.PrestamosDao; 
import proyect_loansys.model.Solicitudes;
import proyect_loansys.view.VentanaAsignarFechas;

/**
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
        
        if (e.getSource() == vistaModal.botonConfirmar) {
            String fechaTexto = vistaModal.textoFechaDevolucion.getText().trim(); 

            if (fechaTexto.isEmpty()) {
                JOptionPane.showMessageDialog(vistaModal, "Por favor, ingrese la fecha límite de devolución.");
                return;
            }

            try {
                LocalDate fechaLimite = LocalDate.parse(fechaTexto);
                LocalDate hoy = LocalDate.now();

                long diasDePrestamo = ChronoUnit.DAYS.between(hoy, fechaLimite);

                if (diasDePrestamo < 0) {
                    JOptionPane.showMessageDialog(vistaModal, "Error: La fecha límite no puede ser un día anterior a hoy.");
                    return;
                }

                String rolUsuario = "";
                if (solicitud != null && solicitud.getNombreRol() != null) {
                    rolUsuario = solicitud.getNombreRol().toUpperCase().trim();
                } else {
                    rolUsuario = "OTROS"; // Rol por defecto si no se detecta ninguno en la tabla
                }

                //Condicion de que el aprendiz solo se puede el mismo dia
                if (rolUsuario.contains("APRENDIZ")) {
                    if (diasDePrestamo != 0) {
                        JOptionPane.showMessageDialog(vistaModal, 
                            "⚠️ REGLA DE SEGURIDAD:\n" +
                            "Los usuarios con rol APRENDIZ solo pueden solicitar elementos para el MISMO DÍA.\n" +
                            "Por favor, asigne la fecha de hoy: " + hoy);
                        return; 
                    }
                } 
                
                //Condicion de que el instructor puede tener un maximo de 2 dia
                else if (rolUsuario.contains("INSTRUCTOR")) {
                    if (diasDePrestamo > 2) {
                        JOptionPane.showMessageDialog(vistaModal, 
                            "⚠️ REGLA DE SEGURIDAD:\n" +
                            "Los INSTRUCTORES pueden tener el elemento un máximo de 2 días.\n" +
                            "La fecha máxima permitida para este préstamo es: " + hoy.plusDays(2));
                        return; 
                    }
                }

                String fechaHoraFinal = fechaTexto + " 17:00:00";
                int idCategoriaProvicional = 1; 

                // Ejecutamos la inserción en la BD
                boolean exito = prestamosDao.registrarPrestamoAprobado(solicitud, fechaHoraFinal, idCategoriaProvicional);

                if (exito) {
                    JOptionPane.showMessageDialog(null, "¡Préstamo aprobado y registrado correctamente!");
                    
                    if (controladorPadre != null) {
                        controladorPadre.listarSolicitudesTabla();
                    }
                    
                    vistaModal.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Error interno en el DAO al procesar la aprobación.");
                }

            } catch (DateTimeParseException ex) {
                // Captura específicamente si la fecha está mal digitada
                JOptionPane.showMessageDialog(vistaModal, "Formato de fecha inválido. Por favor use: YYYY-MM-DD (Ejemplo: 2026-07-07)");
            } catch (NullPointerException ex) {
                // Alerta si algún objeto del modelo llegó completamente vacío
                JOptionPane.showMessageDialog(vistaModal, "Error: Hay un dato de la solicitud o elemento que llega en NULL.");
                ex.printStackTrace();
            } catch (Exception ex) {
                // Alerta general para errores de SQL o base de datos
                JOptionPane.showMessageDialog(vistaModal, "Error inesperado en el sistema:\n" + ex.getMessage());
                ex.printStackTrace();
            }
        }

        if (e.getSource() == vistaModal.botonCancelar) {
            vistaModal.dispose();
        }
    }
}