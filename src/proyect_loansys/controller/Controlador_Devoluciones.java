/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat; // <--- IMPORTACIÓN AGREGADA
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Devolucion;
import proyect_loansys.model.DevolucionDao;
import proyect_loansys.view.Vista_Devoluciones;
import proyect_loansys.view.Vista_GestionUsuarios;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Reportes_Asesor;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Notificaciones;
import proyect_loansys.view.Vista_Prestamo;
import proyect_loansys.view.Vista_Solicitudes;

/**
 * @author Alexis
 */
public class Controlador_Devoluciones implements ActionListener {

    private Vista_Devoluciones vista;
    private DevolucionDao dao;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // <--- DECLARACIÓN DE LA VARIABLE

    public Controlador_Devoluciones(Vista_Devoluciones vista) {
        this.vista = vista;
        this.dao = new DevolucionDao();
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonPrestamos.addActionListener(this);
        this.vista.botonDevoluciones.addActionListener(this);
        this.vista.botonReportes.addActionListener(this);
        this.vista.botonNotificaciones.addActionListener(this);
        this.vista.botonUsuarios.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);
        listarDevolucionesTabla();
    }

    public void listarDevolucionesTabla() {
        List<Devolucion> lista = dao.listarDevoluciones();
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaDevoluciones.getModel();
        modelo.setRowCount(0);
        for (Devolucion dev : lista) {
            Object[] fila = new Object[7];
            fila[0] = dev.getIdDevolucion();
            fila[1] = dev.getIdPrestamo();
            fila[2] = dev.getNombreElemento();
            fila[3] = dev.getEstadoEntrega();
            fila[4] = (dev.getFechaInicioPrestamo() != null) ? formatoFecha.format(dev.getFechaInicioPrestamo()) : "";
            fila[5] = (dev.getFechaDevolucion() != null) ? formatoFecha.format(dev.getFechaDevolucion()) : "";
            fila[6] = dev.getObservaciones();

            modelo.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

        //Modulo de prestamos
        if (e.getSource() == vista.botonPrestamos) {
            vista.dispose();
            Vista_Prestamo vistap = new Vista_Prestamo();
            Controlador_Prestamos controlPrestamo = new Controlador_Prestamos(vistap);
            vistap.setVisible(true);
        }

        //Modulo del inicio del sistema
        if (e.getSource() == vista.botonInicio) {
            vista.dispose();
            Vista_Inicio vistaIni = new Vista_Inicio();
            Controlador_inicio controlador = new Controlador_inicio(vistaIni);
            vistaIni.setVisible(true);
        }

        //Modulo de notificaciones
        if (e.getSource() == vista.botonNotificaciones) {
            vista.dispose();
            Vista_Notificaciones vistaNo = new Vista_Notificaciones();
            Controlador_Notificaciones controlNo = new Controlador_Notificaciones(vistaNo);
            vistaNo.setVisible(true);
        }

        //Modulo de gestion de usuarios
        if (e.getSource() == vista.botonUsuarios) {
            vista.dispose();
            Vista_GestionUsuarios vistaUsers = new Vista_GestionUsuarios();
            Controlador_GestionUsuarios controlUsers = new Controlador_GestionUsuarios(vistaUsers);
            vistaUsers.setVisible(true);
        }

        //Modulo de reportes
        if (e.getSource() == vista.botonReportes) {
            vista.dispose();
            Vista_Reportes_Asesor vistaRep = new Vista_Reportes_Asesor();
            Controlador_Reportes_Asesor controlRep = new Controlador_Reportes_Asesor(vistaRep);
            vistaRep.setVisible(true);
        }
    }
}
