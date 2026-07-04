package proyect_loansys.controller;

import proyect_loansys.view.Inicio_Tecnico;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.Reportes_Tecnico;

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

        
    }
}