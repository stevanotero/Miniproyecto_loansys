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
import proyect_loansys.controller.Controlador_Login;
import proyect_loansys.controller.Controlador_Prestamos;
import proyect_loansys.controller.Controlador_Solicitudes;
import proyect_loansys.controller.Controlador_inicio;
import proyect_loansys.model.ReporteInventarioDao;
import proyect_loansys.view.Vista_Reportes_Asesor;
import proyect_loansys.model.PDFExporter;
import proyect_loansys.model.PDFExporter;
import proyect_loansys.model.ReporteInventarioDao;
import proyect_loansys.view.Vista_Devoluciones;
import proyect_loansys.view.Vista_GestionUsuarios;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Notificaciones;
import proyect_loansys.view.Vista_Prestamo;
import proyect_loansys.view.Vista_Reportes_Asesor;
import proyect_loansys.view.Vista_Solicitudes;

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
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonPrestamos.addActionListener(this);
        this.vista.botonDevoluciones.addActionListener(this);
        this.vista.botonNotificaciones.addActionListener(this);
        this.vista.botonUsuarios.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);
   

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

       
       // Cerrar sesión en el sistema y ir al login
        if (e.getSource() == vista.botonCerrarSesion) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }

        //Modulo del inventario
        if (e.getSource() == vista.botonInventario) {
            vista.dispose();
            Vista_Inventario vistaInventario = new Vista_Inventario();
            Controlador_inventario controladorIn = new Controlador_inventario(vistaInventario);
            vistaInventario.setVisible(true);
        }

        //Modulo de gestión de solicitudes
        if (e.getSource() == vista.botonSolicitudes) {
            vista.dispose();
            Vista_Solicitudes vistaSolicitud = new Vista_Solicitudes();
            Controlador_Solicitudes controladorSol = new Controlador_Solicitudes(vistaSolicitud);
            vistaSolicitud.setVisible(true);
        }

        //Modulo de Prestamo
        if (e.getSource() == vista.botonPrestamos) {
            vista.dispose();
            Vista_Prestamo vistap = new Vista_Prestamo();
            Controlador_Prestamos controlPrestamo = new Controlador_Prestamos(vistap);
            vistap.setVisible(true);
        }

        //Modulo de Notificaciones
        if (e.getSource() == vista.botonNotificaciones) {
            vista.dispose();
            Vista_Notificaciones vistaNo = new Vista_Notificaciones();
            Controlador_Notificaciones controlNo = new Controlador_Notificaciones(vistaNo);
            vistaNo.setVisible(true);
        }

        //Modulo de devoluciones
        if (e.getSource() == vista.botonDevoluciones) {
            vista.dispose();
            Vista_Devoluciones vistaDev = new Vista_Devoluciones();
            Controlador_Devoluciones controlDev = new Controlador_Devoluciones(vistaDev);
            vistaDev.setVisible(true);
        }

        // Modulo de gestión de usuarios
        if (e.getSource() == vista.botonUsuarios) {
            vista.dispose();
            Vista_GestionUsuarios vistaUsers = new Vista_GestionUsuarios();
            Controlador_GestionUsuarios controlUsers = new Controlador_GestionUsuarios(vistaUsers);
            vistaUsers.setVisible(true);
        }
    }

 
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


private void exportarReportePDF() {
    String tituloReporte = "Reporte de Inventario";
    String nombreBase = "Reporte";

    // Mejor detección basada en la tabla actual (más confiable)
    int columnas = vista.tablaReportes.getColumnCount();

    if (columnas == 4) {
        // Posible Estado General o Alertas
        String primeraColumna = vista.tablaReportes.getColumnName(0);
        if (primeraColumna != null && primeraColumna.contains("Código")) {
            if (vista.tablaReportes.getColumnName(2).contains("Categoría")) {
                tituloReporte = "Estado General del Inventario";
                nombreBase = "Estado_General";
            } else {
                tituloReporte = "Alertas de Mantenimiento";
                nombreBase = "Alertas_Mantenimiento";
            }
        }
    } 
    else if (columnas == 3) {
        tituloReporte = "Frecuencia de Uso de Equipos";
        nombreBase = "Frecuencia_Uso";
    }

    boolean exito = PDFExporter.generarPDFConSelector(
        vista.tablaReportes, 
        tituloReporte, 
        nombreBase, 
        vista
    );

    if (exito) {
        JOptionPane.showMessageDialog(vista, "Reporte PDF guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
}
