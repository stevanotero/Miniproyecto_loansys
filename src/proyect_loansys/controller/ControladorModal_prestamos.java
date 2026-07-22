/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proyect_loansys.model.Prestamos;
import proyect_loansys.view.VentanaGestionPrestamo;
import proyect_loansys.view.VentanaObservacionesDevolucion;

/**
 * @author Alexis
 */
public class ControladorModal_prestamos implements ActionListener {

    private VentanaGestionPrestamo vistaModal;
    private Prestamos prestamo;
    private Controlador_Prestamos controladorPadre;

    public ControladorModal_prestamos(VentanaGestionPrestamo vistaModal, Prestamos prestamo, Controlador_Prestamos controladorPadre) {
        this.vistaModal = vistaModal;
        this.prestamo = prestamo;
        this.controladorPadre = controladorPadre;

        this.vistaModal.botonRegistrarDevolucion.addActionListener(this);
        this.vistaModal.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaModal.botonRegistrarDevolucion) {
            vistaModal.dispose(); 
            
            // Abre la segunda modal de observaciones
            VentanaObservacionesDevolucion modalObs = new VentanaObservacionesDevolucion(null);
            Controlador_ObservacionesDevolucion controlObs = new Controlador_ObservacionesDevolucion(modalObs, this.prestamo, this.controladorPadre);
            modalObs.setLocationRelativeTo(null);
            modalObs.setVisible(true);
        }

        if (e.getSource() == vistaModal.botonCancelar) {
            vistaModal.dispose();
        }
    }
}