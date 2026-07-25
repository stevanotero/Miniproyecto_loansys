package proyect_loansys.controller;

import proyect_loansys.view.Inicio_Tecnico;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.Mantenimiento;
import proyect_loansys.view.Reportes_Tecnico;
import proyect_loansys.view.Vista_NotificacionesTecnico;


public class Inicio_Tecnico_Controller {

    private Inicio_Tecnico vista;

    public Inicio_Tecnico_Controller(Inicio_Tecnico vista) {
        this.vista = vista;

        vista.historial.addActionListener(e -> {
            Historial_Tecnico vistaHistorial = new Historial_Tecnico();
            new Historial_Tecnico_Controller(vistaHistorial);
            vista.dispose();
        });

        vista.Reportes.addActionListener(e -> {
            Reportes_Tecnico vistaReportes = new Reportes_Tecnico();
            new Reportes_Tecnico_Controller(vistaReportes);
            vista.dispose();
        });

        vista.Mantenimiento.addActionListener(e -> {
            Mantenimiento vistaMantenimiento = new Mantenimiento();
            new ControllerMantenimiento(vistaMantenimiento);
            vista.dispose();
        });
        
        vista.Notificaciones.addActionListener(e -> {
            Vista_NotificacionesTecnico vistaNo = new Vista_NotificacionesTecnico();
            new Controlador_NotificacionesTecnico(vistaNo);
            vista.setVisible(false);
            vistaNo.setVisible(true);
        });

    }
}
