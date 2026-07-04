package proyect_loansys.controller;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.HistorialMantenimientoDao;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.Reportes_Tecnico;
        
        
public class Historial_Tecnico_Controller {

    private Historial_Tecnico vista;
    private HistorialMantenimientoDao dao = new HistorialMantenimientoDao();

    public Historial_Tecnico_Controller(Historial_Tecnico vista) {
        this.vista = vista;

        //abrirhistorial
        vista.Reportes.addActionListener(e -> {
            Reportes_Tecnico vistaReportes = new Reportes_Tecnico();
            new Reportes_Tecnico_Controller(vistaReportes);
            vista.dispose();
        });

        
        
        vista.getBtnRealizados().addActionListener(e -> {
            cargarTabla(dao.listarHistorialPorEstado("Realizado"));
        });


        
        vista.getBtnRevision().addActionListener(e -> {
            cargarTabla(dao.listarHistorialPorEstado("En mantenimiento"));
        });


        
        vista.getTxtBuscar().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscar();
            }
        });

        cargarTabla(dao.listarHistorial());
        actualizarContadores();
    }

    private void cargarTabla(List<Object[]> datos) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
    }

    private void buscar() {
        String texto = vista.getTxtBuscar().getText().trim();
        if (texto.isEmpty() || texto.equals("Buscar")) {
            cargarTabla(dao.listarHistorial());
        } else {
            cargarTabla(dao.buscarHistorial(texto));
        }
    }

    private void actualizarContadores() {
        int realizados = dao.contarPorEstado("Realizado");
        int enRevision = dao.contarPorEstado("En mantenimiento");
        vista.getLblNumeroRealizados().setText(String.valueOf(realizados));
        vista.getLblNumeroRevision().setText(String.valueOf(enRevision));
    }
}