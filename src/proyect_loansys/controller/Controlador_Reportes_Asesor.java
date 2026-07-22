/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

/**
 *
 * @author juans
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.PDFExporter;
import proyect_loansys.model.ReporteInventarioDao;
import proyect_loansys.view.Vista_Reportes_Asesor;

public class Controlador_Reportes_Asesor implements ActionListener {

    public Vista_Reportes_Asesor vista;
    private ReporteInventarioDao reporteDao = new ReporteInventarioDao();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador_Reportes_Asesor(Vista_Reportes_Asesor vista) {
        this.vista = vista;

        this.vista.btnEstadoGeneral.addActionListener(this);
        this.vista.btnFrecuenciaUso.addActionListener(this);
        this.vista.btnAlertasMantenimiento.addActionListener(this);
        this.vista.btnExportarPDF.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        // Carga inicial
        limpiarTabla();
        getListarEstadoGeneral(vista.tablaReportes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnEstadoGeneral) {
            limpiarTabla();
            getListarEstadoGeneral(vista.tablaReportes);
        }

        if (e.getSource() == vista.btnFrecuenciaUso) {
            limpiarTabla();
            getListarFrecuenciaUso(vista.tablaReportes);
        }

        if (e.getSource() == vista.btnAlertasMantenimiento) {
            limpiarTabla();
            getListarAlertasMantenimiento(vista.tablaReportes);
        }

        if (e.getSource() == vista.btnExportarPDF) {
            exportarReportePDF();
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiarTabla();
        }
    }

    // ==================== CARGAR REPORTES ====================

    public void getListarEstadoGeneral(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setColumnCount(0);
        modelo.addColumn("Código");
        modelo.addColumn("Nombre Elemento");
        modelo.addColumn("Categoría");
        modelo.addColumn("Estado");

        List<Object[]> lista = reporteDao.estadoGeneralInventario();
        Object[] fila = new Object[4];

        for (Object[] dato : lista) {
            fila[0] = dato[0];
            fila[1] = dato[1];
            fila[2] = dato[2];
            fila[3] = dato[3];
            modelo.addRow(fila);
        }
    }

    public void getListarFrecuenciaUso(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setColumnCount(0);
        modelo.addColumn("Código");
        modelo.addColumn("Nombre Elemento");
        modelo.addColumn("Veces Prestado");

        List<Object[]> lista = reporteDao.frecuenciaUso();
        Object[] fila = new Object[3];

        for (Object[] dato : lista) {
            fila[0] = dato[0];
            fila[1] = dato[1];
            fila[2] = dato[2];
            modelo.addRow(fila);
        }
    }

    public void getListarAlertasMantenimiento(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setColumnCount(0);
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Tipo Mantenimiento");
        modelo.addColumn("Estado");

        List<Object[]> lista = reporteDao.alertasMantenimiento();
        Object[] fila = new Object[4];

        for (Object[] dato : lista) {
            fila[0] = dato[0];
            fila[1] = dato[1];
            fila[2] = dato[2];
            fila[3] = dato[3];
            modelo.addRow(fila);
        }
    }

    public void limpiarTabla() {
        modelo = (DefaultTableModel) vista.tablaReportes.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        modelo.setColumnCount(0);
    }

    // ==================== GENERAR PDF ====================
    private void exportarReportePDF() {
        String tituloReporte = "";

        if (vista.btnEstadoGeneral.getBackground().equals(new Color(0, 153, 76))) {
            tituloReporte = "Estado General del Inventario";
        } else if (vista.btnFrecuenciaUso.getBackground().equals(new Color(0, 102, 204))) {
            tituloReporte = "Frecuencia de Uso de Equipos";
        } else {
            tituloReporte = "Alertas de Mantenimiento";
        }

        boolean exito = PDFExporter.generarPDF(vista.tablaReportes, tituloReporte);

        if (exito) {
            JOptionPane.showMessageDialog(vista, "✅ Reporte PDF generado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "❌ Error al generar el PDF", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
