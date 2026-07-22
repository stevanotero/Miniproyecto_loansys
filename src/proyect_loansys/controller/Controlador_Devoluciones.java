/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public Controlador_Devoluciones(Vista_Devoluciones vista) {
        this.vista = vista;
        this.dao = new DevolucionDao();
        this.vista = vista;
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
        modelo.setRowCount(0); // Limpia filas anteriores

        for (Devolucion dev : lista) {
            Object[] fila = new Object[6];
            fila[0] = dev.getIdDevolucion();
            fila[1] = dev.getIdPrestamo();
            fila[2] = dev.getNombreElemento();
            fila[3] = dev.getFechaInicioPrestamo();
            fila[4] = dev.getFechaDevolucion();
            fila[5] = dev.getObservaciones();

            modelo.addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //dar clic a cerrar sesion 
        if (e.getSource() == vista.botonCerrarSesion) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }

        if (e.getSource() == vista.botonInventario) {
            vista.dispose();
            Vista_Inventario vistaInventario = new Vista_Inventario();
            Controlador_inventario controladorIn = new Controlador_inventario(vistaInventario);
            vistaInventario.setVisible(true);
        }

        if (e.getSource() == vista.botonSolicitudes) {
            vista.dispose();
            Vista_Solicitudes vistaSolicitud = new Vista_Solicitudes();
            Controlador_Solicitudes controladorSol = new Controlador_Solicitudes(vistaSolicitud);
            vistaSolicitud.setVisible(true);
        }

        if (e.getSource() == vista.botonPrestamos) {
            vista.dispose();
            Vista_Prestamo vistap = new Vista_Prestamo();
            Controlador_Prestamos controlPrestamo = new Controlador_Prestamos(vistap);
            vistap.setVisible(true);
        }

        if (e.getSource() == vista.botonInicio) {
            vista.dispose();
            Vista_Inicio vistaIni = new Vista_Inicio();
            Controlador_inicio controlador = new Controlador_inicio(vistaIni);
            vistaIni.setVisible(true);
        }

        if (e.getSource() == vista.botonNotificaciones) {
            vista.dispose();
            Vista_Notificaciones vistaNo = new Vista_Notificaciones();
            Controlador_Notificaciones controlNo = new Controlador_Notificaciones(vistaNo);
            vistaNo.setVisible(true);
        }
        if (e.getSource() == vista.botonUsuarios) {
            vista.dispose();
            Vista_GestionUsuarios vistaUsers = new Vista_GestionUsuarios();
            Controlador_GestionUsuarios controlUsers = new Controlador_GestionUsuarios(vistaUsers);
            vistaUsers.setVisible(true);
        }
        if (e.getSource()== vista.botonReportes){
        vista.dispose();
        Vista_Reportes_Asesor vistaRep = new Vista_Reportes_Asesor();
        Controlador_Reportes_Asesor controlRep = new Controlador_Reportes_Asesor();
        vistaRep.setVisible(true);
        }

    }
}
