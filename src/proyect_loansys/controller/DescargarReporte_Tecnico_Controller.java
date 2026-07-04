package proyect_loansys.controller;

import proyect_loansys.view.DescargarReporte_Tecnico;
import proyect_loansys.model.ReporteTecnicoDao;
import proyect_loansys.model.GeneradorReportePDF;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;

public class DescargarReporte_Tecnico_Controller {

    private DescargarReporte_Tecnico vista;
    private ReporteTecnicoDao reporteDao = new ReporteTecnicoDao();
    private GeneradorReportePDF generador = new GeneradorReportePDF();

    public DescargarReporte_Tecnico_Controller(DescargarReporte_Tecnico vista) {
        this.vista = vista;

        vista.btnCancelar.addActionListener(e -> {
            vista.dispose();
        });

        vista.btnDescargar.addActionListener(e -> {
            String codigo = vista.txtCodigoReporte.getText().trim();

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Tienes que poner un código.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            Object[] datos = reporteDao.buscarDatosParaPDF(codigo);

            if (datos == null) {
                JOptionPane.showMessageDialog(
                        vista,
                        "No existe ningún reporte con ese código.", "No encontrado",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            JFileChooser guardarComo = new JFileChooser();
            guardarComo.setApproveButtonText("Guardar");
            guardarComo.setSelectedFile(new File("reporte_" + codigo + ".pdf"));
            int resultado = guardarComo.showSaveDialog(vista);

            if (resultado != JFileChooser.APPROVE_OPTION) {
                return;
            }

            String ruta = guardarComo.getSelectedFile().getAbsolutePath();

            
            
            
            boolean exito = generador.generarPDF(ruta, datos);

            if (exito) {
                JOptionPane.showMessageDialog(
                        vista,
                        "El reporte se descargó correctamente\n en: Documentos",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "Ocurrió un error al generar el PDF.", "Error", JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
