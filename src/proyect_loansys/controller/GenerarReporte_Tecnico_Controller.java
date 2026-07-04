package proyect_loansys.controller;

import proyect_loansys.view.GenerarReporte_Tecnico;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.Reportes_Tecnico;
import proyect_loansys.model.ReporteTecnico;
import proyect_loansys.model.ReporteTecnicoDao;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GenerarReporte_Tecnico_Controller {

    private GenerarReporte_Tecnico vista;
    private ReporteTecnicoDao dao = new ReporteTecnicoDao();
    private Integer idMantenimientoActual;
    private String ultimoCodigoNotificado;

    public GenerarReporte_Tecnico_Controller(GenerarReporte_Tecnico vista) {
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

        vista.btnCancelar.addActionListener(e -> {
            Reportes_Tecnico vistaReportes = new Reportes_Tecnico();
            new Reportes_Tecnico_Controller(vistaReportes);
            vista.dispose();
        });

        
        
        vista.txtCodigoMantenimiento.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { autocompletarDatos(); }

            @Override
            public void removeUpdate(DocumentEvent e) { autocompletarDatos(); }

            @Override
            public void changedUpdate(DocumentEvent e) { autocompletarDatos(); }
        });

        vista.btnRegistrar.addActionListener(e -> {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(vista, "No se pueden dejar campos vacíos.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (idMantenimientoActual == null) {
                JOptionPane.showMessageDialog(vista,
                        "Debe ingresar un código de mantenimiento válido antes de registrar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            
            ReporteTecnico r = new ReporteTecnico();
            r.setCodigoReporte(dao.generarCodigoReporte());
            r.setIdMantenimiento(idMantenimientoActual);

            int filas = dao.setAgregar(r);
            if (filas > 0) {
                JOptionPane.showMessageDialog(vista,
                        "Reporte registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                Reportes_Tecnico vistaReportes = new Reportes_Tecnico();
                new Reportes_Tecnico_Controller(vistaReportes);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Ocurrió un error al registrar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void autocompletarDatos() {
        String codigoMantenimiento = vista.txtCodigoMantenimiento.getText().trim();

        if (codigoMantenimiento.isEmpty()) {
            limpiarCamposAutomaticos();
            ultimoCodigoNotificado = null;
            return;
        }

        Object[] datos = dao.buscarDatosPorCodigoMantenimiento(codigoMantenimiento);

        if (datos == null) {
            limpiarCamposAutomaticos();
            if (codigoMantenimiento.length() == 5 && !codigoMantenimiento.equals(ultimoCodigoNotificado)) {
                if (dao.existeReporteParaMantenimiento(codigoMantenimiento)) {
                    ultimoCodigoNotificado = codigoMantenimiento;
                    JOptionPane.showMessageDialog(vista,
                            "El reporte de este mantenimiento ya fue realizado.",
                            "Reporte ya generado", JOptionPane.WARNING_MESSAGE);
                }
            }
            return;
        }

        ultimoCodigoNotificado = null;
        idMantenimientoActual = (Integer) datos[0];
        vista.txtEquipo.setText((String) datos[1]);
        vista.txtTecnico.setText((String) datos[2]);
        vista.txtDocumento.setText((String) datos[3]);
        vista.cbTipoMantenimiento.setSelectedItem((String) datos[4]);
        vista.cbEstado.setSelectedItem((String) datos[5]);
        vista.txtDescripcion.setText((String) datos[6]);
    }

    private void limpiarCamposAutomaticos() {
        vista.txtEquipo.setText("");
        vista.txtTecnico.setText("");
        vista.txtDocumento.setText("");
        vista.txtDescripcion.setText("");
        vista.cbTipoMantenimiento.setSelectedIndex(0);
        vista.cbEstado.setSelectedIndex(0);
        idMantenimientoActual = null;
    }

    private boolean camposVacios() {
        return vista.txtEquipo.getText().trim().isEmpty()
                || vista.txtTecnico.getText().trim().isEmpty()
                || vista.txtDocumento.getText().trim().isEmpty()
                || vista.txtCodigoMantenimiento.getText().trim().isEmpty()
                || vista.txtDescripcion.getText().trim().isEmpty()
                || vista.cbEstado.getSelectedItem().equals("Seleccione")
                || vista.cbTipoMantenimiento.getSelectedItem().equals("Seleccione");
    }
}