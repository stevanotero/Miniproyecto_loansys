package proyect_loansys.controller;

import proyect_loansys.view.Reportes_Tecnico;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.DescargarReporte_Tecnico;
import proyect_loansys.view.GenerarReporte_Tecnico;
import proyect_loansys.model.ReporteTecnicoDao;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import proyect_loansys.view.Mantenimiento;


public class Reportes_Tecnico_Controller {

    private Reportes_Tecnico vista;
    private ReporteTecnicoDao dao = new ReporteTecnicoDao();

    public Reportes_Tecnico_Controller(Reportes_Tecnico vista) {
        this.vista = vista;

        cargarTarjetas();
        cargarTabla(dao.listarTabla());

//abrirhistorial
        vista.historial.addActionListener(e -> {
            Historial_Tecnico vistaHistorial = new Historial_Tecnico();
            new Historial_Tecnico_Controller(vistaHistorial);
            vista.dispose();
        });

        vista.Mantenimiento.addActionListener(e -> {
            Mantenimiento vistaMantenimiento = new Mantenimiento();
            new ControllerMantenimiento(vistaMantenimiento);
            vista.dispose();
        });

//botongenerar
        vista.btnGenerarReporte.addActionListener(e -> {
            GenerarReporte_Tecnico vistaReporte = new GenerarReporte_Tecnico();
            new GenerarReporte_Tecnico_Controller(vistaReporte);
            vista.dispose();
        });

//botonexportar
        vista.btnExportarReporte.addActionListener(e -> {
            DescargarReporte_Tecnico dialog = new DescargarReporte_Tecnico(vista);
            new DescargarReporte_Tecnico_Controller(dialog);
            dialog.setVisible(true);
        });

//buscador en tiempo real
        vista.txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });
    }

    private void buscar() {
        String texto = vista.txtBuscar.getText().trim();
        if (texto.isEmpty() || texto.equals("Buscar elemento...")) {
            cargarTabla(dao.listarTabla());
        } else {
            cargarTabla(dao.buscarTabla(texto));
        }
    }

    private void cargarTarjetas() {
        vista.lblNumeroTotal.setText(String.valueOf(dao.contarTotalReportes()));
        vista.lblNumeroRealizados.setText(String.valueOf(dao.contarReportesPorEstado("Realizado")));
        vista.lblNumeroProceso.setText(String.valueOf(dao.contarReportesPorEstado("En mantenimiento")));
    }

    private void cargarTabla(List<Object[]> datos) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
    }
}
