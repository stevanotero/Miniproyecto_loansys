/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.UsuarioLogin;
import proyect_loansys.model.UsuarioLoginDao;
import proyect_loansys.view.VentanaEstadoUsuarioModal;
import proyect_loansys.view.Vista_Devoluciones;
import proyect_loansys.view.Vista_GestionUsuarios;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Reportes_Asesor;
import proyect_loansys.view.Vista_Notificaciones;
import proyect_loansys.view.Vista_Prestamo;
import proyect_loansys.view.Vista_Solicitudes;

/**
 * @author Alexis
 */
public class Controlador_GestionUsuarios implements ActionListener {

    private Vista_GestionUsuarios vista;
    private UsuarioLoginDao dao;
    private List<UsuarioLogin> listaUsuarios;

    public Controlador_GestionUsuarios(Vista_GestionUsuarios vista) {
        this.vista = vista;
        this.dao = new UsuarioLoginDao();
        this.listaUsuarios = new ArrayList<>();

        // Escuchadores de la barra lateral (Estructura estándar de navegación)
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonPrestamos.addActionListener(this);
        this.vista.botonDevoluciones.addActionListener(this);
        this.vista.botonReportes.addActionListener(this);
        this.vista.botonNotificaciones.addActionListener(this);
        this.vista.botonUsuarios.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);

        // Escuchador de doble clic en la tabla de usuarios
        this.vista.tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int filaSeleccionada = vista.tablaUsuarios.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        UsuarioLogin usuarioSeleccionado = listaUsuarios.get(filaSeleccionada);
                        abrirModalEstado(usuarioSeleccionado);
                    }
                }
            }
        });
        listarUsuariosTabla();
    }

    public void listarUsuariosTabla() {
        listaUsuarios = dao.listarUsuarios();
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaUsuarios.getModel();
        modelo.setRowCount(0);

        for (UsuarioLogin u : listaUsuarios) {
            Object[] fila = new Object[7];
            fila[0] = u.getIdLogin();
            fila[1] = u.getIdUsuario();
            fila[2] = u.getNombreUsuario();
            fila[3] = u.getCorreo();
            fila[4] = u.getFechaRegistro();
            fila[5] = u.getNombreEstado();
            fila[6] = u.getDescripcionMora();

            modelo.addRow(fila);
        }
    }

    private void abrirModalEstado(UsuarioLogin usuario) {
    VentanaEstadoUsuarioModal modal = new VentanaEstadoUsuarioModal(vista, usuario);
    modal.botonActivar.addActionListener(e -> {
        boolean ok = dao.cambiarEstadoMoraUsuario(usuario.getIdLogin(), 1);
        if (ok) {
            JOptionPane.showMessageDialog(modal, "El estado de mora del usuario ha cambiado a 'Al día'.");
            listarUsuariosTabla();
            modal.dispose();
        }
    });
    modal.botonDesactivar.addActionListener(e -> {
        boolean ok = dao.cambiarEstadoMoraUsuario(usuario.getIdLogin(), 2);
        if (ok) {
            JOptionPane.showMessageDialog(modal, "El estado del usuario se ha actualizado a 'En mora'.");
            listarUsuariosTabla();
            modal.dispose();
        }
    });
    modal.botonCancelar.addActionListener(e -> modal.dispose());

    modal.setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        // Redirecciones del menú lateral estándar
        if (e.getSource() == vista.botonInicio) {
            vista.dispose();
            Vista_Inicio vistaIni = new Vista_Inicio();
            Controlador_inicio controlador = new Controlador_inicio(vistaIni);
            vistaIni.setVisible(true);
        }

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

        if (e.getSource() == vista.botonDevoluciones) {
            vista.dispose();
            Vista_Devoluciones vistaDev = new Vista_Devoluciones();
            Controlador_Devoluciones controlDev = new Controlador_Devoluciones(vistaDev);
            vistaDev.setVisible(true);
        }

        if (e.getSource() == vista.botonNotificaciones) {
            vista.dispose();
            Vista_Notificaciones vistaNotif = new Vista_Notificaciones();
            Controlador_Notificaciones controlNotif = new Controlador_Notificaciones(vistaNotif);
            vistaNotif.setVisible(true);
        }
        if (e.getSource()== vista.botonReportes){
        vista.dispose();
        Vista_Reportes_Asesor vistaRep = new Vista_Reportes_Asesor();
        Controlador_Reportes_Asesor controlRep = new Controlador_Reportes_Asesor(vistaRep);
        vistaRep.setVisible(true);
        }
    }
}
