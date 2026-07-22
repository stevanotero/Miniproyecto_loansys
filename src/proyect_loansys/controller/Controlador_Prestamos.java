/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Prestamos;
import proyect_loansys.model.PrestamosActivosDao;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Prestamo;
import proyect_loansys.view.Vista_Solicitudes;
import proyect_loansys.controller.ControladorModal_prestamos;
import proyect_loansys.view.VentanaGestionPrestamo;
import proyect_loansys.view.Vista_Devoluciones;
import proyect_loansys.view.Vista_Reportes_Asesor;
import proyect_loansys.view.Vista_GestionUsuarios;
import proyect_loansys.view.Vista_Notificaciones;

/**
 *
 * @author Alexis
 */
public class Controlador_Prestamos implements ActionListener {

    private Vista_Prestamo vista;
    private PrestamosActivosDao modelo;
    private DefaultTableModel modeloTabla;
    private List<Prestamos> listaPrestamos;

    public Controlador_Prestamos(Vista_Prestamo vista) {
        this.vista = vista;
        this.modelo = new PrestamosActivosDao();

        // Escuchadores del menú principal heredados de Vista_Principal
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonPrestamos.addActionListener(this);
        this.vista.botonDevoluciones.addActionListener(this);
        this.vista.botonReportes.addActionListener(this);
        this.vista.botonNotificaciones.addActionListener(this);
        this.vista.botonUsuarios.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);

        listarPrestamosTabla();

        // Evento de doble clic para gestionar el préstamo
        this.vista.tablaDePrestamos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    abrirFormularioGestionar();
                }
            }
        });
    }

    public void listarPrestamosTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaDePrestamos.getModel();
        modeloTabla.setRowCount(0);

        listaPrestamos = modelo.listar();
        Object[] fila = new Object[8];

        for (Prestamos pres : listaPrestamos) {
            fila[0] = pres.getIdPrestamo();
            fila[1] = pres.getCodigoElemento();
            fila[2] = pres.getNombreElemento();
            fila[3] = pres.getNombreUsuario() + " " + pres.getApellidoUsuario();
            fila[4] = pres.getDocumentoUsuario();
            fila[5] = pres.getFechaInicioPrestamo();
            fila[6] = pres.getFechaFinDevolucion();
            fila[7] = pres.getNombreEstadoElemento();
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioGestionar() {
        int filaSeleccionada = vista.tablaDePrestamos.getSelectedRow();
        if (filaSeleccionada != -1) {

            Prestamos prestamoSeleccionado = listaPrestamos.get(filaSeleccionada);

            String idPrestamo = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 0).toString();
            String nombreElemento = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 2).toString();
            String usuario = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 3).toString();
            String fechaInicio = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 5).toString();
            String fechaDevolucion = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 6).toString();
            VentanaGestionPrestamo ventanaGestion = new VentanaGestionPrestamo(null);
            ControladorModal_prestamos gestionModal = new ControladorModal_prestamos(ventanaGestion, prestamoSeleccionado, this);

            // Rellenado de campos informativos solicitados
            ventanaGestion.textoIdPrestamo.setText(idPrestamo);
            ventanaGestion.textoNombreElemento.setText(nombreElemento);
            ventanaGestion.textoUsuario.setText(usuario);
            ventanaGestion.textoFechaInicio.setText(fechaInicio);
            ventanaGestion.textoFechaDevolucion.setText(fechaDevolucion);

            ventanaGestion.setLocationRelativeTo(vista);
            ventanaGestion.setVisible(true);
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

        if (e.getSource() == vista.botonInicio) {
            vista.dispose();
            Vista_Inicio vistaIni = new Vista_Inicio();
            Controlador_inicio controlador = new Controlador_inicio(vistaIni);
            vistaIni.setVisible(true);
        }

        if (e.getSource() == vista.botonSolicitudes) {
            vista.dispose();
            Vista_Solicitudes vistaSolicitud = new Vista_Solicitudes();
            Controlador_Solicitudes controladorSol = new Controlador_Solicitudes(vistaSolicitud);
            vistaSolicitud.setVisible(true);
        }

        if (e.getSource() == vista.botonNotificaciones) {
            vista.dispose();
            Vista_Notificaciones vistaNo = new Vista_Notificaciones();
            Controlador_Notificaciones controlNo = new Controlador_Notificaciones(vistaNo);
            vistaNo.setVisible(true);
        }

        if (e.getSource() == vista.botonDevoluciones) {
            vista.dispose();
            Vista_Devoluciones vistaDev = new Vista_Devoluciones();
            Controlador_Devoluciones controlDev = new Controlador_Devoluciones(vistaDev);
            vistaDev.setVisible(true);
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
        Controlador_Reportes_Asesor controlRep = new Controlador_Reportes_Asesor(vistaRep);
        vistaRep.setVisible(true);
        }
    }
}
