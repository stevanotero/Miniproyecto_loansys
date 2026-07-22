/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import proyect_loansys.model.Prestamos;
import proyect_loansys.model.PrestamosActivosDao;
import proyect_loansys.view.VentanaObservacionesDevolucion;

/**
 *
 * @author Alexis
 */
public class Controlador_ObservacionesDevolucion implements ActionListener {

    private VentanaObservacionesDevolucion vistaModal;
    private Prestamos prestamo;
    private Controlador_Prestamos controladorPadre;
    private PrestamosActivosDao prestamosDao;

    public Controlador_ObservacionesDevolucion(VentanaObservacionesDevolucion vistaModal, Prestamos prestamo, Controlador_Prestamos controladorPadre) {
        this.vistaModal = vistaModal;
        this.prestamo = prestamo;
        this.controladorPadre = controladorPadre;
        this.prestamosDao = new PrestamosActivosDao();

        // Escuchadores de los botones de la segunda modal
        this.vistaModal.botonConfirmar.addActionListener(this);
        this.vistaModal.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaModal.botonConfirmar) {
            String observaciones = vistaModal.textoObservaciones.getText().trim();

            // Validacion si no vacío o lleno de puros espacios en blanco
            if (observaciones.isEmpty()) {
                JOptionPane.showMessageDialog(vistaModal, 
                        "Por favor, ingrese una observación del estado de entrega. No se permiten espacios en blanco.", 
                        "Validación de Entrada", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validación mínimo de 20 caracteres
            if (observaciones.length() < 20) {
                JOptionPane.showMessageDialog(vistaModal, 
                        "La observación debe ser más detallada (Mínimo 20 caracteres).\nCaracteres actuales: " + observaciones.length(), 
                        "Texto Demasiado Corto", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validacion Máximo de 200 caracteres
            if (observaciones.length() > 100) {
                JOptionPane.showMessageDialog(vistaModal, 
                        "La observación no puede exceder los 200 caracteres para evitar errores.\nCaracteres actuales: " + observaciones.length(), 
                        "Texto Demasiado Largo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Si pasa todas las validaciones, procede con la Base de Datos
            boolean exito = prestamosDao.registrarDevolucionCompleta(prestamo, observaciones);

            if (exito) {
                JOptionPane.showMessageDialog(null, "¡Devolución registrada e historial actualizado con éxito!");
                
                if (controladorPadre != null) {
                    controladorPadre.listarPrestamosTabla(); // Recarga la tabla principal
                }
                vistaModal.dispose(); // Cierra la ventana final
            } else {
                JOptionPane.showMessageDialog(vistaModal, "Error al procesar los inserts de la devolución en la Base de Datos.");
            }
        }

        if (e.getSource() == vistaModal.botonCancelar) {
            vistaModal.dispose();
        }
    }
}